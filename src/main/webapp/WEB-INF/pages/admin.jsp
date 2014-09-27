<%-- 
    Document   : admin
    Created on : 14.09.2014, 21:46:15
    Author     : Alyx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty language}">
    <jsp:forward page="/index.jsp" />
</c:if>
<c:if test="${empty user}">
    <jsp:forward page="/index.jsp" />
</c:if>
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
        ${user.adminName}<br/>
        <fmt:message key="admin.employee.list"/><br/>
        
        <fmt:message key="admin.employer.list"/><br/>
        
        <fmt:message key="admin.vacancy.list"/><br/>
        
        <fmt:message key="admin.checkdocument.list"/><br/>
        
        <form method="post" action="admin.html">
            <fmt:message key="index.to.login.page" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
        
        <form method="post" action="toLoginPage.html">
            <fmt:message key="login.logout" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
        
    </body>
</html>
