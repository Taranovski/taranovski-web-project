<%-- 
    Document   : vacancyEditSkills
    Created on : 29.09.2014, 14:54:07
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
        <title><fmt:message key="vacancy.edit.skills.title"/></title>
    </head>
    <body>
        <fmt:message key="vacancy.current.skills"/>${user.login}

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
                    <form method="post" action="vacancyUpdateExperience.html">
                        <fmt:message key="vacancy.update.skill" var="dontSaveInfo"/>
                        <input type="hidden" name="skillId" value="${skill.skillId}"/> 
                        <td>${skill.skill.skillName}</td>
                        <td>${skill.skill.description}</td>
                        <td><input type="text" name="experience" value="${skill.experience}" maxlength="38"/></td>
                        <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
                        <td><input type="submit" value="${dontSaveInfo}" /></td>
                    </form>
                    <td>
                        <form method="post" action="vacancyDeleteSkill.html">
                            <input type="hidden" name="skillId" value="${skill.skillId}"/>
                            <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
                            <fmt:message key="vacancy.delete.skill" var="dontSaveInfo"/>
                            <input type="submit" value="${dontSaveInfo}" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty skills}">
    <br><fmt:message key="vacancy.no.skills"/>
</c:if>

<c:if test="${not empty update_error}">
    <fmt:message key="vacancy.skills.update.error"/>
</c:if>

<c:if test="${not empty skillsToAdd}">
    <form action="vacancyAddSkill.html" method="POST">
        <table border="0">
            <tbody>
                <tr>
                    <td><fmt:message key="vacancy.skills.add.message"/></td>
                    <td>
                        <select name="skillsToAddList">
                            <c:forEach var="basicSkill" items="${skillsToAdd}">
                                <option value="${basicSkill.skillId}">${basicSkill.skillName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="experience" value="0" /></td>
                    <td>
                        <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
                        <fmt:message key="vacancy.skills.add.button" var="dontSaveInfo"/>
                        <input type="submit" value="${dontSaveInfo}" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</c:if>

<c:if test="${empty skillsToAdd}">
    <fmt:message key="vacancy.no.skills.left.to.add.message"/>
</c:if>

<c:if test="${not empty add_error}">
    <fmt:message key="vacancy.skills.add.error"/>
</c:if>

<form method="post" action="vacancyDeleteAllSkills.html">
    <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
    <fmt:message key="vacancy.delete.all.skills" var="dontSaveInfo"/>
    <input type="submit" value="${dontSaveInfo}" />
</form>

<form method="post" action="editEmployerVacancy.html">
    <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
    <fmt:message key="vacancy.dont.save.information" var="dontSaveInfo"/>
    <input type="submit" value="${dontSaveInfo}" />
</form>


</body>
</html>
