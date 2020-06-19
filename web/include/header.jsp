<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text" var="locale"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
<%--    <title><fmt:message bundle="${locale}" key="admin.page"/></title>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">--%>
    <script src="${pageContext.request.contextPath}/js/admin.js"></script>
<%--    <title><fmt:message bundle="${locale}" key="profile"/></title>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user.css">
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">--%>
    <script src="${pageContext.request.contextPath}/js/user.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css">
    <meta charset="UTF-8">
</head>
<body>
<div class="topnav">
    <a class="active" href="${pageContext.request.contextPath}/index.jsp">COOL UNIVERSITY</a>
    <a href="${pageContext.request.contextPath}/jsp/about.jsp">
        <fmt:message bundle="${locale}" key="header.about"/></a>
    <a href="${pageContext.request.contextPath}/jsp/contacts.jsp">
        <fmt:message bundle="${locale}" key="header.contacts"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=SWITCH_LANG">
        <fmt:message bundle="${locale}" key="header.language"/>
    </a>
    <div class="log">
        <c:if test="${user!= null}">
            <a href="${pageContext.request.contextPath}/controller?command=profile"> ${user.login}</a>
            <a href="${pageContext.request.contextPath}/controller?command=log_out">
                <fmt:message bundle="${locale}" key="header.logout"/>
            </a>
        </c:if>
        <c:if test="${user== null}">
            <a href="${pageContext.request.contextPath}/controller?command=LOGIN">
                <fmt:message bundle="${locale}" key="header.login"/>
            </a>
        </c:if>
    </div>
</div>