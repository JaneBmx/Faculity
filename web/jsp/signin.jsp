<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text" var="locale"/>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="login.in"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="login_form">
    <form method="post" class="login" action="${pageContext.request.contextPath}/controller?command=login">
        <h2><fmt:message bundle="${locale}" key="login.in"/></h2>
        <div class="login-form">
            <input type="text" name="user_login" placeholder=
            <fmt:message bundle="${locale}" key="user.login"/> required>
            <input type="password" name="user_password" placeholder=
            <fmt:message bundle="${locale}" key="user.password"/> required>
            <p style="color: white">${message}</p>
            <button type="submit"><fmt:message bundle="${locale}" key="login.log"/></button>
        </div>
        <a href="${pageContext.request.contextPath}/jsp/signup.jsp">
            <fmt:message bundle="${locale}" key="login.registrate"/></a>
    </form>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>