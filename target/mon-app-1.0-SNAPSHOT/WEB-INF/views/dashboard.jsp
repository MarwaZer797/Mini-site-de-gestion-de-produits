<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Tableau de bord</title></head>
<body>
    <h1>Bienvenue ${user} !</h1>
    <p><a href="${pageContext.request.contextPath}/produits">Gérer les produits</a></p>
    <p><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></p>
</body>
</html>