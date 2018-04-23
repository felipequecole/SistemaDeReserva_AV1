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
        <script src="jquery-3.3.1.min.js"></script>
        <title>Ops!</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="content">
        <h1>Um erro inesperado aconteceu!</h1>
        
        <c:if test="${!empty requestScope.mensagem}">
            <div class="error_message">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagem}" var="mensagem">
                    <li>${mensagem}</li>
                    </c:forEach>
            </ul>
            <hr>
         </div>
        </c:if>
         </div>
        <jsp:include page="footer.html"/>
    </body>
</html>
