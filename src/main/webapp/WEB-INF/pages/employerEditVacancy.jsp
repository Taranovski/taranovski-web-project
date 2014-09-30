<%-- 
    Document   : employerEditVacancy
    Created on : 27.09.2014, 19:33:36
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
        <title><fmt:message key="employer.view.vacancy"/></title>
    </head>
    <body>
        <fmt:message key="employer.current.vacancy"/>${user.login}<br><br>

        <fmt:message key="vacancy.edit.position"/>${vacancy.position}<br/>
        <fmt:message key="vacancy.edit.description"/>${vacancy.description}<br/>
        <fmt:message key="vacancy.edit.salary"/>${vacancy.salary}<br/>

        <form method="post" action="editEmployerVacancyInfo.html">
            <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
            <fmt:message key="employer.edit.vacancy.information" var="editInfo"/>
            <input type="submit" value="${editInfo}" />
        </form>
        <br/>
        <fmt:message key="employer.vacancy.skills"/><br>

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
            <fmt:message key="employer.no.skills"/>
        </c:if>

        <form method="post" action="editEmployerVacancySkills.html">
            <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/>
            <fmt:message key="employer.vacancy.edit.skills" var="editSkills"/>
            <input type="submit" value="${editSkills}" />
        </form>

        <c:if test="${not empty employees}">
            <br><fmt:message key="employer.employees.for.vacancy"/>
            <table border="1">
                <thead>
                    <tr>
                        <th><fmt:message key="employee.name"/></th>
                        <th><fmt:message key="employee.surname"/></th>
                        <th><fmt:message key="employee.patronymic"/></th>
                        <th><fmt:message key="employee.qualification"/></th>
                        <th><fmt:message key="employee.occupation"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="employee" items="${employees}">
                        <tr>
                            <td>${employee.name}         </td>
                            <td>${employee.surname}      </td>
                            <td>${employee.patronymic}   </td>
                            <td>${employee.qualification}</td>
                            <td>${employee.occupation}   </td>
                            <td>
                                <form method="post" action="employerOfferVacancy.html">
                                    <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/> 
                                    <fmt:message key="employer.offer.vacancy" var="editSkills"/>
                                    <input type="submit" value="${editSkills}" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>

        <c:if test="${empty employees}">
            <fmt:message key="employer.no.employees"/>
        </c:if>

        <form method="post" action="dontSaveEmployerInfo.html">
            <fmt:message key="employer.dont.save.profile" var="dontSaveInfo"/>
            <input type="submit" value="${dontSaveInfo}" />
        </form>
    </body>
</html>
