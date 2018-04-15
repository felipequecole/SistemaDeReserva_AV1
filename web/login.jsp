<%-- 
    Document   : login
    Created on : 11/04/2018, 20:46:07
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <c:if test="${sessionScope.role != null}">
        <c:redirect url="index.jsp"></c:redirect>
    </c:if>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <title>Sistema de Reservas - Login</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <% if (session.getAttribute("login_mensagem") != null) { %>
        <p class="errorMessage"> ${sessionScope.login_mensagem}</p>
        <% }%>
        <form action="loginServlet" method="post">
            <label for="username">Username: </label>
            <input type="text" name="username"/><br/>
            <label for="username">Senha: </label>
            <input type="password" name="senha"/><br/>
            <label for="tipo">Tipo de usuário</label>
            <select name="tipo">
                <option value="admin">Administrador</option>
                <option value="site">Site</option>
                <option value="hotel">Hotel</option>
            </select>
            <button type="submit">Enviar</button>
        </form>
    </body>
    <!--Remove da session depois de já exibido (caso precise de reload)-->
    <% session.removeAttribute("login_mensagem");%>
</html>
