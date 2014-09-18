<%-- 
    Document   : login
    Created on : Sep 17, 2014, 11:18:12 AM
    Author     : Oleksandr_Taranovsky
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
        <title><fmt:message key="login.title"/></title>
    </head>
    <body>

        <form method="post" action="loginSystem.html">
            <label for="username"><fmt:message key="login.label.username" />:</label>
            <input type="text" id="username" name="username">
            <br>
            <label for="password"><fmt:message key="login.label.password" />:</label>
            <input type="password" id="password" name="password">
            <br>
            <fmt:message key="login.button.submit" var="buttonValue" />
            <input type="submit" name="submit" value="${buttonValue}">
        </form>

        <form method="post" action="toRegister.html">
            <fmt:message key="login.register" var="register"/>
            <input type="submit" value="${register}" />
        </form>

        <br>
        <c:if test = "${not empty login_error}">
            <fmt:message key="login.error"/>
            <c:set var="login_error" value="0"/>
        </c:if>

        <br>

    </body>
</html>
