package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ma.app.model.Produit;
import ma.app.model.ProduitDAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {
    private ProduitDAO dao;

    @Override
    public void init() {
        dao = (ProduitDAO) getServletContext().getAttribute("produitDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Lister les produits
            req.setAttribute("produits", dao.findAll());
            req.getRequestDispatcher("/WEB-INF/views/produits/list.jsp").forward(req, resp);
        } else if ("new".equals(action)) {
            // Formulaire vide
            req.setAttribute("produit", new Produit());
            req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            // Formulaire pré-rempli
            try {
                Long id = Long.parseLong(req.getParameter("id"));
                Produit p = dao.findById(id);
                if (p == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Produit introuvable");
                    return;
                }
                req.setAttribute("produit", p);
                req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID invalide");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            try {
                Long id = Long.parseLong(req.getParameter("id"));
                dao.delete(id);
                req.getSession().setAttribute("flash", "Produit supprimé avec succès.");
            } catch (NumberFormatException ignored) {}
            resp.sendRedirect(req.getContextPath() + "/produits");
            return;
        }

        // Création ou modification
        String idStr = req.getParameter("id");
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String prixStr = req.getParameter("prix");
        String stockStr = req.getParameter("stock");

        Map<String, String> erreurs = new HashMap<>();

        // Validation du nom (2 à 50 caractères)
        if (nom == null || nom.trim().length() < 2 || nom.trim().length() > 50) {
            erreurs.put("nom", "Le nom doit contenir entre 2 et 50 caractères.");
        }

        // Validation du prix (strictement positif)
        BigDecimal prix = null;
        try {
            prix = new BigDecimal(prixStr);
            if (prix.compareTo(BigDecimal.ZERO) <= 0) {
                erreurs.put("prix", "Le prix doit être strictement positif.");
            }
        } catch (Exception e) {
            erreurs.put("prix", "Prix invalide.");
        }

        // Validation du stock (entier >= 0)
        int stock = 0;
        try {
            if (stockStr != null && !stockStr.isEmpty()) {
                stock = Integer.parseInt(stockStr);
                if (stock < 0) erreurs.put("stock", "Le stock doit être supérieur ou égal à 0.");
            }
        } catch (NumberFormatException e) {
            erreurs.put("stock", "Stock invalide.");
        }

        // Description optionnelle (max 255 caractères)
        if (description != null && description.length() > 255) {
            erreurs.put("description", "La description ne doit pas dépasser 255 caractères.");
        }

        Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;
        Produit produit = new Produit(id, nom, description, prix, stock);

        if (!erreurs.isEmpty()) {
            req.setAttribute("erreurs", erreurs);
            req.setAttribute("produit", produit);
            req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp").forward(req, resp);
        } else {
            dao.save(produit);
            req.getSession().setAttribute("flash", "Produit \"" + nom + "\" enregistré avec succès.");
            resp.sendRedirect(req.getContextPath() + "/produits");
        }
    }
}