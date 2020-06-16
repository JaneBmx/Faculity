<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text" var="locale"/>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="reg.reg"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>
<div class="login_form">
    <form method="post" class="login" action="${pageContext.request.contextPath}/controller?command=sign_up">
        <h2><fmt:message bundle="${locale}" key="reg.reg"/></h2>
        <div class="login-form">
            <input type="text" placeholder=
            <fmt:message bundle="${locale}" key="reg.name"/> name="user_name" required
                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$" title="2-40 symbols. First in uppercase">
            <input type="text" placeholder=
            <fmt:message bundle="${locale}" key="reg.surname"/> name="user_surname" required
                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$" title="2-40 symbols. First in uppercase">
            <input type="email" placeholder=
            <fmt:message bundle="${locale}" key="reg.email"/> name="user_email" required
                   pattern="^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"
                   title="someletters@letters.letters">
            <input type="text" placeholder=
            <fmt:message bundle="${locale}" key="reg.login"/> name="user_login" required
                   pattern="([a-zA-Z\\d]{4,40})$"
                   title="4-40 latin symbols & digits">
            <input type="password" placeholder=
            <fmt:message bundle="${locale}" key="reg.password"/> name="user_password" required
                   pattern="((?=.*\d)(?=.*[a-z]).{6,40})$" title="6-40 symbols">
            <input type="hidden" name="user_role" value="user">
            <p style="color: white">${message_signup}</p>
            <button type="submit"><fmt:message bundle="${locale}" key="reg.go"/></button>
        </div>
        <a href="${pageContext.request.contextPath}/jsp/signin.jsp"><fmt:message bundle="${locale}" key="reg.in"/></a>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>