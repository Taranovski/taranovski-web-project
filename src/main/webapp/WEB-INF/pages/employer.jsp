<%-- 
    Document   : employer
    Created on : Sep 19, 2014, 12:51:34 PM
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
        <title><fmt:message key="employer.title"/></title>
    </head>
    <body>
        <fmt:message key="employer.greetings"/>${user.login}<br/>
        
    </body>
</html>
