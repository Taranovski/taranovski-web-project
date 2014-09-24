<%-- 
    Document   : employee
    Created on : Sep 19, 2014, 12:51:23 PM
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
        <title><fmt:message key="employee.title"/></title>
    </head>
    <body>
        <fmt:message key="employee.greetings"/>${user.login}<br/>
        <br/>
        <fmt:message key="employee.name"/>${user.name}<br/>
        <fmt:message key="employee.surname"/>${user.surname}<br/>
        <fmt:message key="employee.patronymic"/>${user.patronymic}<br/>
        <fmt:message key="employee.qualification"/>${user.qualification}<br/>
        <fmt:message key="employee.occupation"/>${user.occupation}<br/>
        
        <form method="post" action="editEmployeePersonalInfo.html">
            <fmt:message key="employee.edit.profile" var="editInfo"/>
            <input type="submit" value="${editInfo}" />
        </form>
        <br/>
        <fmt:message key="employee.skills"/><br>

        <c:if test="${not empty skills}">
            <table border="1">
                <thead>
                    <tr>
                        <th><fmt:message key="skill.name"/></th>
                        <th><fmt:message key="skill.description"/></th>
                        <th><fmt:message key="skill.experience"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="skill" items="${skills}">
                        <tr>
                            <td>${skill.skill.skillName}</td>
                            <td>${skill.skill.description}</td>
                            <td>${skill.experience}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <c:if test="${empty skills}">
            <fmt:message key="employee.no.skills"/>
        </c:if>

        <form method="post" action="editEmployeeSkills.html">
            <fmt:message key="employee.edit.skills" var="editSkills"/>
            <input type="submit" value="${editSkills}" />
        </form>
        
        <form method="post" action="toLoginPage.html">
            <fmt:message key="login.logout" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>

    </body>
</html>
