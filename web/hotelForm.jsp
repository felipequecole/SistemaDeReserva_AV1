<%-- 
    Document   : hotelForm
    Created on : 16/04/2018, 18:47:53
    Author     : frankson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.role != 'admin'}">
    <!--Mexer depois para ter o erro correto-->
    <c:redirect url="login.jsp"></c:redirect>
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Reservas</title>
        <link rel="stylesheet" type="text/css" href="estilo.css" />
        <script src="jquery-3.3.1.min.js"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <br/>
        <div class="content">
            <h1>Cadastra Hotel</h1>
            <hr>
            <form action="CadastraHotelServlet" method="post">
                Digite seus dados:<br/>
                CNPJ: <input name="cnpj" type="text" value="${sessionScope.novoHotel.cnpj}" /><br/>
                Nome: <input name="nome" type="text" value="${sessionScope.novoHotel.nome}" /><br/>
                Cidade: <input name="cidade" type="text" value="${sessionScope.novoHotel.cidade}" /><br/>
                Senha: <input name="senha" type="password" value="${sessionScope.novoHotel.senha}" /><br/>
                <input type="submit" value="Enviar"/>
            </form>

            <c:if test="${!empty requestScope.mensagens}">
                <ul class="erro">
                    <c:forEach items="${requestScope.mensagens}" var="mensagem">
                        <li>${mensagem}</li>
                        </c:forEach>
                </ul>
                <hr>
            </c:if>
        </div>
        <jsp:include page="footer.html"/>
    </body>
</html>
