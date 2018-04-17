<%-- 
    Document   : cadastraPromocao
    Created on : 16/04/2018, 15:11:01
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <c:if test="${sessionScope.role != 'hotel'}">
        <!--Mexer depois para ter o erro correto-->
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <title>Cadastrar promoção</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <h1>Cadastrar nova promoção</h1>
        <form action="cadastraPromocaoServlet" method="post">
            <!--Mudar para o login ser de hotel e a lista ser de sites-->
            <label for="url">Site:</label>
            <input type="text" name="url"/> <br/>
            <label for="data_inicio">Data de início: </label>
            <input type="text" name="data_inicio" placeholder="dd/mm/yyyy"/> <br/>
            <label for="data_fim">Data de fim: </label>
            <input type="text" name="data_fim" placeholder="dd/mm/yyyy"/> <br/>
            <label for="preco">Preço:</label>
            <input type="number" step="0.01"/><br/>
            <button type="submit">Enviar</button>
            
        </form>
    </body>
</html>
