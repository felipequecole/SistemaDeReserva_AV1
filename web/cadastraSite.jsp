<%-- 
    Document   : cadastraSite
    Created on : 11/04/2018, 20:49:06
    Author     : felipequecole
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <c:if test="${sessionScope.role != 'admin'}">
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
</html>
