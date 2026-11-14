package ma.app.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Récupération des informations demandées pour l'Exercice 1
        String hostName = InetAddress.getLocalHost().getHostName();
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String userAgent = req.getHeader("User-Agent");

        out.println("<html><body>");
        out.println("<h1>Exercice 1 : Informations de la Servlet</h1>");
        out.println("<ul>");
        out.println("  <li><b>Nom de la machine :</b> " + hostName + "</li>");
        out.println("  <li><b>Date et heure actuelles :</b> " + currentDate + "</li>");
        out.println("  <li><b>En-tête User-Agent :</b> " + userAgent + "</li>");
        out.println("</ul>");
        out.println("</body></html>");
    }
}