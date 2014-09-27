<%-- 
    Document   : employeeEditInformation
    Created on : 23.09.2014, 16:57:56
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
        <title><fmt:message key="employee.edit.profile.title"/></title>
    </head>
    <body>
        <fmt:message key="employee.current.information"/>${user.login}
        <form method="post" action="saveEmployeePersonalInfo.html">
            <fmt:message key="employee.name"/><input type="text" name="name" value="${user.name}" /><br>
            <fmt:message key="employee.surname"/><input type="text" name="surname" value="${user.surname}" /><br>
            <fmt:message key="employee.patronymic"/><input type="text" name="patronymic" value="${user.patronymic}" /><br>
            <fmt:message key="employee.qualification"/><input type="text" name="qualification" value="${user.qualification}" /><br>
            <fmt:message key="employee.occupation"/><input type="text" name="occupation" value="${user.occupation}" /><br>

            <fmt:message key="employee.save.profile" var="saveInfo"/>
            <input type="submit" value="${saveInfo}" />
        </form>
        <form method="post" action="dontSaveEmployeePersonalInfo.html">
            <fmt:message key="employee.dont.save.profile" var="dontSaveInfo"/>
            <input type="submit" value="${dontSaveInfo}" />
        </form>
        
        
    </body>
</html>
