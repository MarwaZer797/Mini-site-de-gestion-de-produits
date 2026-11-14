<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>Connexion</title></head>
<body>
    <h1>Connexion</h1>
    <c:if test="${not empty erreur}">
        <p style="color: red;">${erreur}</p>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <input type="text" 
               name="username" 
               value="${usernameSaisi}" 
               placeholder="Nom d'utilisateur" required><br><br>
               
        <input type="password" 
               name="password" 
               placeholder="Mot de passe" required><br><br>
               
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>