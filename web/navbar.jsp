<%-- 
    Document   : navbar
    Created on : 15/04/2018, 14:14:04
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<div class="topnav">
    <a class="active normal" href="index.jsp">Página inicial</a>
    <div class="caixa_busca">
     <form action="ListaHotelServlet" method="GET">
         <label for="cidade">Pesquise por cidade:</label>
                <input type="text" name="cidade" placeholder="Buscar por cidade..."/>
                <button type="submit">Buscar hotel</button>
                </form> 
     </div>
    <a class="normal" href="ListaHotelServlet">Listar hotéis</a>
    <% if (session.getAttribute("user") == null) { %>
        <a class="navright" href="login.jsp">Login</a>
    <% } else { %>
        <a class="navright" href="logout"> Logout </a>
        <a class="navright nonclick" href="."> ${sessionScope.user} (${sessionScope.role}) </a>
    <% }%>
</div>
