<%-- 
    Document   : verPromocoes
    Created on : 17/04/2018, 21:35:44
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
        <title>Sistema de Reservas - Ver Promoções</title>
    </head>
    <body>       
        <jsp:include page="navbar.jsp" />
        <div class="content">
            <c:if test="${!empty requestScope.promocoes}">
                <table>
                    <tr>
                        <th>ID</th>
                            <c:if test="${sessionScope.role == 'site'}">
                            <th>CNPJ</th>
                            </c:if>
                            <c:if test="${sessionScope.role == 'hotel'}">
                            <th>URL</th>
                            </c:if>
                        <th>Início</th>
                        <th>Fim</th>
                        <th>Preço</th>
                    </tr>
                    <c:forEach items="${requestScope.promocoes}" var="promocao">
                        <tr>
                            <td>${promocao.id}</td>
                            <c:if test="${sessionScope.role == 'site'}">
                                <td>${promocao.cnpj}</td>
                            </c:if>
                            <c:if test="${sessionScope.role == 'hotel'}">
                                <td>${promocao.url}</td>
                            </c:if>
                            <td class="data_inicio">${promocao.data_inicio}</td>
                            <td class="data_fim">${promocao.data_fim}</td>
                            <td class="preco">${promocao.preco}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty requestScope.promocoes}">
                <div class="warning_message">
                    <h3>Não há promoções cadastradas para ${sessionScope.user}</h3>
                </div>
            </c:if>
        </div>
        <jsp:include page="footer.html"/>
    </body>
    <script>
        $(document).ready(function () {
            var datas_i = $('.data_inicio');
            for (var i = 0; i < datas_i.length; i++) {
                var aux = datas_i[i].textContent;
                var from = aux.split('-');
                datas_i[i].textContent = from[2] + '/' + from[1] + '/' + from[0];
            }
            ;
            var datas_f = $('.data_fim');
            for (var i = 0; i < datas_f.length; i++) {
                var aux = datas_f[i].textContent;
                var from = aux.split('-');
                datas_f[i].textContent = from[2] + '/' + from[1] + '/' + from[0];
            }
            ;
            var precos = $('.preco');
            for (var i = 0; i < precos.length; i++) {
                var aux = parseFloat(precos[i].textContent);
                aux = aux.toFixed(2);
                precos[i].textContent = 'R$' + aux;
            }
            ;
        });
    </script>
</html>
