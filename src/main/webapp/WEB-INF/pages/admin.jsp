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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! admin</h1>
        ${user}
        <form method="post" action="newjsp.html">
            <input type="submit" value="getAdminById" />
        </form>
    </body>
</html>
