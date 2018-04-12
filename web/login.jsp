<%-- 
    Document   : login
    Created on : 11/04/2018, 20:46:07
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action="loginServlet" method="post">
        <label for="username">Username: </label>
        <input type="text" name="email"/><br/>
        <label for="username">Senha: </label>
        <input type="password" name="senha"/><br/>
        <label for="tipo">Tipo de usuário</label>
        <select name="tipo">
            <option value="site">Site</option>
            <option value="hotel">Hotel</option>
        </select>
        <button type="submit">Enviar</button>
    </form>
</html>
