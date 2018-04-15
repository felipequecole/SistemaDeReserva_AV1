<%-- 
    Document   : index
    Created on : 10/04/2018, 23:36:06
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <title>Sistema de Reservas</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <br/>
        <h1>Sistema de reserva</h1>
        <ul>
            <li><a href="cadastraSite.jsp">Cadastrar site</a></li>
            <li><a href="#">Cadastrar hotel</a></li>
            <li><a href="#">Listar hotéis</a></li>
            <li><a href="#">Listar hotéis por cidade</a></li>
            <li><a href="#">Criar promoção</a></li>
            <li><a href="#">Listar promoções por hotel</a></li>
        </ul>
    </body>
</html>
