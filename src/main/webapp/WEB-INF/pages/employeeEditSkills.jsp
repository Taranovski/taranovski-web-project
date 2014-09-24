<%-- 
    Document   : employeeEditSkills
    Created on : 24.09.2014, 11:37:38
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
        <title><fmt:message key="employee.edit.skills.title"/></title>
    </head>
    <body>
        <fmt:message key="employee.current.skills"/>${user.login}

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
                    <form method="post" action="updateExperience.html">
                        <fmt:message key="employee.update.skill" var="dontSaveInfo"/>
                        <input type="hidden" name="skillId" value="${skill.skillId}"/> 
                        <td>${skill.skill.skillName}</td>
                        <td>${skill.skill.description}</td>
                        <td><input type="text" name="experience" value="${skill.experience}" /></td>
                        <td><input type="submit" value="${dontSaveInfo}" /></td>
                    </form>
                    <td>
                        <form method="post" action="deleteSkill.html">
                            <input type="hidden" name="skillId" value="${skill.skillId}"/>
                            <fmt:message key="employee.delete.skill" var="dontSaveInfo"/>
                            <input type="submit" value="${dontSaveInfo}" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty skills}">
    <br><fmt:message key="employee.no.skills"/>
</c:if>

<c:if test="${not empty update_error}">
    <fmt:message key="employee.skills.update.error"/>
</c:if>

<form action="addSkill.html" method="POST">
    <table border="0">
        <tbody>
            <tr>
                <td><fmt:message key="employee.skills.add.message"/></td>
                <td>
                    <select name="skillsToAddList">
                        <c:forEach var="basicSkill" items="${skillsToAdd}">
                            <option value="${basicSkill.skillId}">${basicSkill.skillName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="text" name="experience" value="0" /></td>
                <td>
                    <fmt:message key="employee.skills.add.button" var="dontSaveInfo"/>
                    <input type="submit" value="${dontSaveInfo}" />
                </td>
            </tr>
        </tbody>
    </table>




</form>

<c:if test="${not empty add_error}">
    <fmt:message key="employee.skills.add.error"/>
</c:if>

<form method="post" action="deleteAllSkills.html">
    <fmt:message key="employee.delete.all.skills" var="dontSaveInfo"/>
    <input type="submit" value="${dontSaveInfo}" />
</form>

<form method="post" action="dontSaveEmployeePersonalInfo.html">
    <fmt:message key="employee.dont.save.profile" var="dontSaveInfo"/>
    <input type="submit" value="${dontSaveInfo}" />
</form>
</body>
</html>
