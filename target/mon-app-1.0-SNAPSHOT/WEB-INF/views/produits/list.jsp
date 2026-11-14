<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head><title>Catalogue de produits</title></head>
<body>
    <h1>Catalogue de produits</h1>

    <!-- Message Flash -->
    <c:if test="${not empty sessionScope.flash}">
        <p style="color: green; font-weight: bold;">${sessionScope.flash}</p>
        <c:remove var="flash" scope="session"/>
    </c:if>

    <p><a href="${ctx}/produits?action=new">Ajouter un produit</a></p>

    <c:if test="${empty produits}">
        <p>Aucun produit enregistré.</p>
    </c:if>

    <c:if test="${not empty produits}">
        <table border="1" cellpadding="5">
            <tr>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="p" items="${produits}">
                <tr>
                    <td><c:out value="${p.nom}"/></td>
                    <td><c:out value="${p.description}"/></td>
                    <td><fmt:formatNumber value="${p.prix}" type="number" minFractionDigits="2" maxFractionDigits="2"/> DH</td>
                    <td style="color: ${p.stock == 0 ? 'red' : 'black'};">${p.stock}</td>
                    <td>
                        <a href="${ctx}/produits?action=edit&id=${p.id}">Éditer</a>
                        <form method="post" action="${ctx}/produits" style="display:inline;" onsubmit="return confirm('Voulez-vous vraiment supprimer ce produit ?');">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${p.id}">
                            <button type="submit" style="background:none;border:none;color:red;cursor:pointer;padding:0;">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <p><a href="${ctx}/dashboard">Retour au tableau de bord</a></p>
</body>
</html>