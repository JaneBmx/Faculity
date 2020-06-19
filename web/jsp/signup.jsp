<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="reg.reg"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>
<div class="login_form">
    <form method="post" class="login" action="${pageContext.request.contextPath}/controller?command=sign_up">
        <h2><fmt:message bundle="${locale}" key="reg.reg"/></h2>
        <div class="login-form">
            <input type="text" placeholder=
            <fmt:message bundle="${locale}" key="user.name"/> name="user_name" required
                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                   title=<fmt:message bundle="${locale}" key="tip.name"/>>
            <input type="text" placeholder=
            <fmt:message bundle="${locale}" key="user.surname"/> name="user_surname" required
                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                   title=<fmt:message bundle="${locale}" key="tip.surname"/>>
            <input type="email" placeholder=
            <fmt:message bundle="${locale}" key="user.email"/> name="user_email" required
                   pattern="^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"
                   title=<fmt:message bundle="${locale}" key="tip.email"/>>
            <input type="text" placeholder=
            <fmt:message bundle="${locale}" key="user.login"/> name="user_login" required
                   pattern="([a-zA-Z\\d]{4,40})$"
                   title=<fmt:message bundle="${locale}" key="tip.login"/>>
            <input type="password" placeholder=
            <fmt:message bundle="${locale}" key="user.password"/> name="user_password" required
                   pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                   title=<fmt:message bundle="${locale}" key="tip.password"/>>
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