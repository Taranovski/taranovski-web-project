<%-- 
    Document   : employerEditInformation
    Created on : 26.09.2014, 13:47:09
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
        <title><fmt:message key="employer.edit.profile.title"/></title>
    </head>
    <body>
        <fmt:message key="employer.current.information"/>${user.login}
        <form method="post" action="saveEmployerInfo.html">
            <fmt:message key="employer.company.name"/><input type="text" name="companyName" value="${user.companyName}" maxlength="50"/><br/>
            <fmt:message key="employer.field"/><input type="text" name="field" value="${user.field}" maxlength="50"/><br/>
            <fmt:message key="employer.adress"/><input type="text" name="adress" value="${user.adress}" maxlength="256"/><br/>
            <fmt:message key="employer.telephone.number"/><input type="text" name="telephoneNumber" value="${user.telephoneNumber}" maxlength="12"/><br/>

            <fmt:message key="employer.save.profile" var="saveInfo"/>
            <input type="submit" value="${saveInfo}" />
        </form>
            <c:if test="${not empty tel_error}">
                <fmt:message key="employer.telephone.number.error"/><br>
            </c:if>
        
        <form method="post" action="dontSaveEmployerInfo.html">
            <fmt:message key="employer.dont.save.profile" var="dontSaveInfo"/>
            <input type="submit" value="${dontSaveInfo}" />
        </form>


    </body>
</html>