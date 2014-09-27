<%-- 
    Document   : employer
    Created on : Sep 19, 2014, 12:51:34 PM
    Author     : Oleksandr_Taranovsky
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
        <title><fmt:message key="employer.title"/></title>
    </head>
    <body>
        <fmt:message key="employer.greetings"/>${user.login}<br/>

        <br/>
        <fmt:message key="employer.company.name"/>${user.companyName}<br/>
        <fmt:message key="employer.field"/>${user.field}<br/>
        <fmt:message key="employer.adress"/>${user.adress}<br/>
        <fmt:message key="employer.telephone.number"/>${user.telephoneNumber}<br/>

        <form method="post" action="editEmployerInfo.html">
            <fmt:message key="employer.edit.profile" var="editInfo"/>
            <input type="submit" value="${editInfo}" />
        </form>
        <br/>
        <fmt:message key="employer.vacancies"/><br>

        <c:if test="${not empty vacancies}">
            <table border="1">
                <thead>
                    <tr>
                        <th><fmt:message key="vacancy.position"/></th>
                        <th><fmt:message key="vacancy.description"/></th>
                        <th><fmt:message key="vacancy.salary"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vacancy" items="${vacancies}">
                        <tr>
                            <td>${vacancy.position}</td>
                            <td>${vacancy.description}</td>
                            <td>${vacancy.salary}</td>
                            <td>
                                <form method="post" action="editEmployerVacancy.html">
                                    <fmt:message key="employer.edit.vacancy" var="editSkills"/>
                                    <input type="submit" value="${editSkills}" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty vacancies}">
            <fmt:message key="employer.no.vacancies"/>
        </c:if>

        <form method="post" action="addEmployerVacancy.html">
            <fmt:message key="employer.add.vacancy" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>

        <form method="post" action="toLoginPage.html">
            <fmt:message key="login.logout" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
    </body>
</html>
