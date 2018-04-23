<%-- 
    Document   : cadastraSite
    Created on : 11/04/2018, 20:49:06
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <c:if test="${sessionScope.role != 'admin'}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css" />        
        <script src="jquery-3.3.1.min.js"></script>
        <title>Cadastro de Sites</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <br/>
        <div class="content">
            <h1>Cadastro de Sites</h1>
            <form action="cadastraSiteServlet" method="post">
                <label for="url">Endereço do site: </label>
                <input type="text" name="url" value="${sessionScope.form.url}"/><br/>
                <label for="nome">Nome do site: </label>
                <input type="text" name="nome" value="${sessionScope.form.nome}"/><br/>
                <label for="telefone">Telefone: </label>
                <input type="text" name="telefone" value="${sessionScope.form.telefone}"/><br/>
                <label for="senha">Senha: </label>
                <input type="password" name="senha"/><br/>
                <button type="submit">Enviar</button>    
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
