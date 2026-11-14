package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/succes")
public class SuccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String utilisateur = (String) session.getAttribute("utilisateur");

        if (utilisateur == null) {
            resp.sendRedirect("formulaire.html");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1>Bienvenue  " + utilisateur + " !</h1>");
    }
}