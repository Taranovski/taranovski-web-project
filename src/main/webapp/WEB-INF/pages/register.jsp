<%-- 
    Document   : register
    Created on : Sep 17, 2014, 11:59:57 AM
    Author     : Oleksandr_Taranovsky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty language}">
    <jsp:forward page="/index.jsp" />
</c:if>
<fmt:setLocale value="${sessionScope['language']}" />
<fmt:setBundle basename="${sessionScope['bundle']}" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="register.title"/></title>
    </head>
    <body>
        <form method="post" action="register.html">
            <label for="username"><fmt:message key="register.label.username" />:</label>
            <input type="text" id="username" name="username">
            <br>
            <label for="password"><fmt:message key="register.label.password" />:</label>
            <input type="password" id="password" name="password">
            <br>
            
            <input type="radio" name="usertype" value="employee" checked="true"/><fmt:message key="register.label.employee" /><br>
            <input type="radio" name="usertype" value="employer" /><fmt:message key="register.label.employer" /><br>
            <fmt:message key="register.button.submit" var="buttonValue" />
            <input type="submit" name="submit" value="${buttonValue}">
        </form>

        <form method="post" action="toLoginPage.html">
            <fmt:message key="index.to.login.page" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>

        <c:if test = "${not empty name_error}">
            <fmt:message key="register.name.error"/><br>
            <c:set var="name_error" value="0"/>
        </c:if>
        <c:if test = "${not empty password_error}">
            <fmt:message key="register.password.error"/><br>
            <c:set var="password_error" value="0"/>
        </c:if>
        <c:if test = "${not empty register_error}">
            <fmt:message key="register.error"/><br>
            <c:set var="register_error" value="0"/>
        </c:if>
        <c:if test = "${not empty register_success}">
            <fmt:message key="register.success"/><br>
            <c:set var="register_success" value="0"/>
        </c:if>

    </body>
</html>
