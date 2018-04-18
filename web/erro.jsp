<%-- 
    Document   : erro
    Created on : 17/04/2018, 20:34:32
    Author     : eduardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <title>Ops!</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <h1>Um erro inesperado aconteceu!</h1>
          <c:if test="${!empty requestScope.mensagem}">
            <ul class="erro">
            <c:forEach items="${requestScope.mensagem}" var="mensagem">
                <li>${mensagem}</li>
            </c:forEach>
            </ul>
            <hr>
        </c:if>

    </body>
</html>
