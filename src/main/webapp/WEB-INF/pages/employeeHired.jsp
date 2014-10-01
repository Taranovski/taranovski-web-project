<%-- 
    Document   : employeeHired
    Created on : 01.10.2014, 15:22:00
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
        <title><fmt:message key="employee.title"/></title>
    </head>
    <body>
        <fmt:message key="employee.greetings"/>${user.login}<br/>
        <br/>
        <fmt:message key="employee.name"/>           ${user.name}          <br/>
        <fmt:message key="employee.surname"/>        ${user.surname}       <br/>
        <fmt:message key="employee.patronymic"/>     ${user.patronymic}    <br/>
        <fmt:message key="employee.qualification"/>  ${user.qualification} <br/>
        <fmt:message key="employee.occupation"/>     ${user.occupation}    <br/>

        <table border="1">
            <thead>
                <tr>
                    <th><fmt:message key="employee.hire.status"/></th>
                    <th><fmt:message key="employer.company.name"/></th>
                    <th><fmt:message key="vacancy.position"/></th>
                    <th><fmt:message key="vacancy.salary"/></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><fmt:message key="employee.hired"/></td>
                    <td>${checkDocument.vacancy.employer.companyName}</td>
                    <td>${checkDocument.vacancy.position}</td>
                    <td>${checkDocument.vacancy.salary}</td>
                </tr>
            </tbody>
        </table>
        <form method="post" action="toLoginPage.html">
            <fmt:message key="login.logout" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
    </body>
</html>
