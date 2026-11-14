package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<!-- Formulaire GET -->");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String nom = req.getParameter("nom");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        if (nom == null || nom.isEmpty()) {
            resp.sendRedirect("formulaire.html?erreur=nom_vide");
            return;
        }

        if (email == null || !email.contains("@")) {
            resp.sendRedirect("formulaire.html?erreur=email_invalide");
            return;
        }

        int age = 0;
        try {
            age = Integer.parseInt(ageStr);
            if (age < 18) {
                resp.sendRedirect("formulaire.html?erreur=age_min");
                return;
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("formulaire.html?erreur=age_invalide");
            return;
        }
        
        HttpSession session = req.getSession();
        session.setAttribute("utilisateur", nom);

        resp.sendRedirect("succes");
    }
}