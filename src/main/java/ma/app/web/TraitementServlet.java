package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/traitement")
public class TraitementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Récupération des données du formulaire
        String nom = req.getParameter("nom");
        String email = req.getParameter("email");

        try {
            // Connexion à la base de données via le DataSource JNDI
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/mydb");
            
            try (Connection conn = ds.getConnection()) {
                String sql = "INSERT INTO utilisateurs (nom, email) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, nom);
                    pstmt.setString(2, email);
                    pstmt.executeUpdate();
                }
            }

            out.println("<html><body>");
            out.println("<h2>Succès !</h2>");
            out.println("<p>Les informations de <b>" + nom + "</b> ont bien été enregistrées dans la base de données.</p>");
            out.println("<a href='form.jsp'>Saisir un autre utilisateur</a>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h2 style='color:red;'>Erreur lors de l'enregistrement :</h2>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            out.println("<a href='form.jsp'>Retour au formulaire</a>");
            out.println("</body></html>");
        }
    }
}