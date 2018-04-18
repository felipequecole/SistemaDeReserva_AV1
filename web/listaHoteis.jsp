<%-- 
    Document   : listaHoteis
    Created on : 17/04/2018, 21:43:49
    Author     : frankson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Reservas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <h1>Lista Hoteis</h1>
        <hr>
        <c:if test="${empty requestScope.listaHoteis}">
            Não há hoteis cadastrados!
        </c:if>
        <c:if test="${!empty requestScope.listaHoteis}">
            <table>
                <tr>
                    <th>CNPJ</th>
                    <th>Nome</th>
                    <th>Cidade</th>
                </tr>
                <c:forEach items="${requestScope.listaHoteis}" var="hotel">
                    <tr>
                        <td>${hotel.cnpj}</td>
                        <td>${hotel.nome}</td>
                        <td>${hotel.cidade}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
