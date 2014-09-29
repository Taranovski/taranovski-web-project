<%-- 
    Document   : employerAddVacancy
    Created on : 27.09.2014, 19:32:32
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
        <title><fmt:message key="employer.add.vacancy"/></title>
    </head>
    <body>
        <fmt:message key="employer.new.vacancy"/>${user.login}<br>

        <form method="post" action="addEmployerVacancy.html">

            <fmt:message key="vacancy.edit.position"/><input type="text" name="position" maxlength="50"/><br/>
            <fmt:message key="vacancy.edit.description"/><input type="text" name="description" maxlength="100"/><br/>
            <fmt:message key="vacancy.edit.salary"/><input type="text" name="salary" maxlength="16"/><br/>

            <fmt:message key="vacancy.save.information" var="saveInfo"/>
            <input type="submit" value="${saveInfo}" />
        </form>
        
        <c:if test="${not empty salary_error}">
            <fmt:message key="vacancy.salary.error"/><br>
        </c:if>

        <form method="post" action="dontSaveEmployerInfo.html">
            <fmt:message key="vacancy.dont.save.information" var="dontSaveInfo"/>
            <input type="submit" value="${dontSaveInfo}" />
        </form>
    </body>
</html>
