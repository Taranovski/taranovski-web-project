<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="index.title"/></title>
    </head>
    <body>
        
        <spring:message code="index.language"/><a href="?language=en">English</a>|<a href="?language=ru">Русский</a>
        
        
        <form method="post" action="./index.jsp">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
        </form>
        <fmt:message key="index.hello"/><br>
        
        <form method="post" action="toLogin.html">
            <spring:message code="index.to.login.page" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
    </body>
</html>
