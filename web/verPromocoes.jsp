<%-- 
    Document   : verPromocoes
    Created on : 17/04/2018, 21:35:44
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <title>Sistema de Reservas - Ver Promoções</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <c:if test="${!empty requestScope.promocoes}">
            <table>
                <tr>
                    <th>ID</th>
                        <c:if test="${sessionScope.role == 'site'}">
                        <th>CNPJ</th>
                        </c:if>
                        <c:if test="${sessionScope.role == 'hotel'}">
                        <th>URL</th>
                        </c:if>
                        <th>Início</th>
                        <th>Fim</th>
                        <th>Preço</th>
                </tr>
                <c:forEach items="${requestScope.promocoes}" var="promocao">
                    <tr>
                        <td>${promocao.id}</td>
                        <c:if test="${sessionScope.role == 'site'}">
                        <td>${promocao.cnpj}</td>
                        </c:if>
                        <c:if test="${sessionScope.role == 'hotel'}">
                        <td>${promocao.url}</td>
                        </c:if>
                         <td>${promocao.data_inicio}</td>
                         <td>${promocao.data_fim}</td>
                         <td>${promocao.preco}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty requestScope.promocoes}">
            <h3>Não há promoções cadastradas para ${sessionScope.url}</h3>
        </c:if>
    </body>
</html>
