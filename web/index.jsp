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
        <% if (session.getAttribute("mensagem") != null) { %>
        <div class="errorMessage">
            <ul class="erro">
                <c:forEach items="${requestScope.mensagens}" var="mensagem">
                    <li>${mensagem}</li>
                </c:forEach>
            </ul>
        </div>
        <% }%>
        <br/>
        <h1>Sistema de reserva</h1> <br/>
        <form action="ListaHotelServlet" method="GET">
            <div>            
                <input type="text" name="cidade" placeholder="Buscar por cidade..."/>
                <button type="submit">Buscar</button>            
            </div>
        </form>
        <ul>
            <li><a href="cadastraSite.jsp">Cadastrar site</a></li>
            <li><a href="hotelForm.jsp">Cadastrar hotel</a></li>
            <li><a href="#">Listar hotéis</a></li>
            <li><a href="#">Listar hotéis por cidade</a></li>
            <li><a href="cadastraPromocao.jsp">Criar promoção</a></li>
            <li><a href="VerPromocoesServlet">Listar promoções por hotel</a></li>
            <li><a href="VerPromocoesServlet">Listar promoções por site</a></li>
        </ul>
    </body>
    <% session.removeAttribute("mensagem");%>
</html>
