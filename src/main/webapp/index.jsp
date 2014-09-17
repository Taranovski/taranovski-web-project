<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<c:set var="bundle" value="localisation.text" scope="session"/>
<fmt:setLocale value="${sessionScope['language']}" />
<fmt:setBundle basename="${sessionScope['bundle']}" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="index.title"/></title>
    </head>
    <body>
        
        <form method="post" action="./index.jsp">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
        </form>
        <fmt:message key="index.hello"/><br>
        
        <form method="post" action="toLogin.html">
            <fmt:message key="index.to.login.page" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
    </body>
</html>
