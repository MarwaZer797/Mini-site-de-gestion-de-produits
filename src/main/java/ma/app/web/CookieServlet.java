package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("set".equals(action)) {
            Cookie cookie = new Cookie("theme", "dark");
            cookie.setMaxAge(30 * 24 * 60 * 60); // 30 jours[cite: 18]
            cookie.setPath("/");
            resp.addCookie(cookie);
        }

        // Afficher tous les cookies
        resp.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = req.getCookies();
        resp.getWriter().println("<h2>Liste des Cookies :</h2>");
        if (cookies != null) {
            for (Cookie c : cookies) {
                resp.getWriter().println("<p>" + c.getName() + " : " + c.getValue() + "</p>");
            }
        } else {
            resp.getWriter().println("<p>Aucun cookie trouvé. Teste avec ?action=set</p>");
        }
    }
}