package ma.app.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        
        // Sauvegarde dans un dossier temporaire ou le serveur
        String uploadPath = req.getServletContext().getRealPath("") + "uploads";
        java.io.File uploadDir = new java.io.File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        
        filePart.write(uploadPath + java.io.File.separator + fileName);

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>Fichier uploadé avec succès : " + fileName + "</h3>");
    }
}