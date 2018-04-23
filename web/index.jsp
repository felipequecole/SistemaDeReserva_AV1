<%-- 
    Document   : index
    Created on : 10/04/2018, 23:36:06
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <script src="jquery-3.3.1.min.js"></script>
        <title>Sistema de Reservas</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
         <div class="content">
        <% if (session.getAttribute("mensagem") != null) { %>
        <div class="success_message">
            <ul class="erro">
                <c:forEach items="${sessionScope.mensagem}" var="mensagem">
                    <li>${mensagem}</li>
                    </c:forEach>
            </ul>
        </div>
        <% }%>       
            <h1>Sistema de reserva</h1> <br/>
            <c:if test="${sessionScope.user == null}">
                <p>Bem-vindo ao sistema de reserva!</p>
                <p>Para ter acesso a mais funcionalidades do sistema, identifique-se</p>
            </c:if>

            <c:if test="${sessionScope.role == 'admin'}"> 
                <a href="cadastraSite.jsp"><button class="home_btn">Cadastrar site</button></a>
                <a href="hotelForm.jsp"><button class="home_btn">Cadastrar hotel</button></a>
                </c:if>
                <c:if test="${sessionScope.role == 'hotel'}">
                    <a href="FormPromocaoServlet"><button class="home_btn">Criar promoção</button></a>
                </c:if>
                <c:if test="${sessionScope.role == 'hotel' || sessionScope.role == 'site'}">
                    <a href="VerPromocoesServlet"><button class="home_btn">Ver minhas promoções</button></a>
                </c:if>
        </div>
        <jsp:include page="footer.html"/>
    </body>
    <% session.removeAttribute("mensagem");%>
    <% session.removeAttribute("form");%>
</html>
