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
                        <th><fmt:message key="vacancy.status"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vacancy" items="${vacancies}">
                        <tr>
                            <td>${vacancy.position}</td>
                            <td>${vacancy.description}</td>
                            <td>${vacancy.salary}</td>
                            <td>${vacancy.status}</td>
                            <td>
                                <form method="post" action="editEmployerVacancy.html">
                                    <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/> 
                                    <fmt:message key="employer.view.vacancy" var="editSkills"/>
                                    <input type="submit" value="${editSkills}" />
                                </form>
                            </td>
                            <td>
                                <form method="post" action="deleteEmployerVacancy.html">
                                    <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}"/> 
                                    <fmt:message key="employer.delete.vacancy" var="editSkills"/>
                                    <input type="submit" value="${editSkills}" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty vacancies}">
            <br/><fmt:message key="employer.no.vacancies"/>
        </c:if>

        <c:if test="${not empty vacancy_delete_error}">
            <br/><fmt:message key="employer.vacancy.delete.error"/>
        </c:if>

        <form method="post" action="toAddEmployerVacancy.html">
            <fmt:message key="employer.add.vacancy" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>

        <c:if test="${not empty checkDocuments}">
            <br/><fmt:message key="employer.checkdocuments.list"/>

            <table border="1">
                <thead>
                    <tr>
                        <th><fmt:message key="employee.name"/> </th>
                        <th><fmt:message key="employee.surname"/></th>
                        <th><fmt:message key="employee.occupation"/></th>
                        <th><fmt:message key="employee.hire.status"/></th>
                        <th><fmt:message key="employer.company.name"/></th>
                        <th><fmt:message key="employer.adress"/></th>
                        <th><fmt:message key="employer.telephone.number"/></th>
                        <th><fmt:message key="vacancy.position"/></th>
                        <th><fmt:message key="vacancy.salary"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="checkDocument" items="${checkDocuments}">
                        <tr>
                            <td>${checkDocument.employee.name}</td>
                            <td>${checkDocument.employee.surname}</td>
                            <td>${checkDocument.employee.occupation}</td>
                            <td><fmt:message key="employee.hired"/></td>
                            <td>${checkDocument.vacancy.employer.companyName}</td>
                            <td>${checkDocument.vacancy.employer.adress}</td>
                            <td>${checkDocument.vacancy.employer.telephoneNumber}</td>
                            <td>${checkDocument.vacancy.position}</td>
                            <td>${checkDocument.vacancy.salary}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty checkDocuments}">
            <br/><fmt:message key="employer.no.checkdocuments"/>
        </c:if>

        <form method="post" action="dontSaveEmployerInfo.html">
            <fmt:message key="employer.page.reflesh" var="dontSaveInfo"/>
            <input type="submit" value="${dontSaveInfo}" />
        </form>

        <form method="post" action="toLoginPage.html">
            <fmt:message key="login.logout" var="toLoginPage"/>
            <input type="submit" value="${toLoginPage}" />
        </form>
    </body>
</html>
