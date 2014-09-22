<%-- 
    Document   : admin
    Created on : 14.09.2014, 21:46:15
    Author     : Alyx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope['language']}" />
<fmt:setBundle basename="${sessionScope['bundle']}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="admin.title"/></title>
    </head>
    <body>
        <fmt:message key="admin.greetings"/>${user.login}<br/>
        <fmt:message key="admin.employee.list"/><br/>
        
        <fmt:message key="admin.employer.list"/><br/>
        
        <fmt:message key="admin.vacancy.list"/><br/>
        
        <fmt:message key="admin.checkdocument.list"/><br/>
        
    </body>
</html>
