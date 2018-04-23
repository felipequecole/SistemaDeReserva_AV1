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
        <script src="jquery-3.3.1.min.js"></script>
        <title>Cadastrar promoção</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <h1>Cadastrar nova promoção</h1>
            <form action="cadastraPromocaoServlet" method="post">
                <!--Mudar para o login ser de hotel e a lista ser de sites-->
                <label for="url">Site:</label>
                <select  name="url">
                    <c:forEach items="${requestScope.sites}" var="site">
                        <option value="${site.url}">${site.nome}</option>
                    </c:forEach>
                </select> <br/>
                <label for="data_inicio">Data de início: </label>
                <input type="text" name="data_inicio" placeholder="dd/mm/yyyy" value="${sessionScope.form.data_inicio}"/> <br/>
                <label for="data_fim">Data de fim: </label>
                <input type="text" name="data_fim" placeholder="dd/mm/yyyy" value="${sessionScope.form.data_fim}"/> <br/>
                <label for="preco">Preço:</label>
                <input type="text" name="preco" value="${sessionScope.form.preco}"/><br/>
                <button type="submit">Enviar</button>

            </form>

            <c:if test="${!empty requestScope.mensagem}">
                <div class="error_message">
                    <ul>
                        <c:forEach items="${requestScope.mensagem}" var="mensagem">
                            <li>${mensagem}</li>
                            </c:forEach>
                    </ul>
                </div>
                <hr>
            </c:if>
        </div>
        <jsp:include page="footer.html"/>
        <%--<c:remove scope="session" var="form"/>--%>
    </body>
</html>
