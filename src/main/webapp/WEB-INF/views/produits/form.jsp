<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>${empty produit.id ? 'Nouveau produit' : 'Modifier le produit'}</title>
</head>
<body>
    <h1>${empty produit.id ? 'Nouveau produit' : 'Modifier le produit'}</h1>

    <form method="post" action="${ctx}/produits">
        <c:if test="${not empty produit.id}">
            <input type="hidden" name="id" value="${produit.id}">
        </c:if>

        <div>
            <label>Nom :</label><br>
            <input type="text" name="nom" value="<c:out value='${produit.nom}'/>">
            <span style="color:red;">${erreurs.nom}</span>
        </div>
        <br>

        <div>
            <label>Description :</label><br>
            <textarea name="description"><c:out value='${produit.description}'/></textarea>
            <span style="color:red;">${erreurs.description}</span>
        </div>
        <br>

        <div>
            <label>Prix :</label><br>
            <input type="text" name="prix" value="${produit.prix}">
            <span style="color:red;">${erreurs.prix}</span>
        </div>
        <br>

        <div>
            <label>Stock :</label><br>
            <input type="number" name="stock" value="${produit.stock}">
            <span style="color:red;">${erreurs.stock}</span>
        </div>
        <br>

        <button type="submit">Enregistrer</button>
    </form>

    <p><a href="${ctx}/produits">Annuler</a></p>
</body>
</html>