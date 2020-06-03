<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>
<div class="login_form">
    <form method="post" class="login" action="${pageContext.request.contextPath}/controller?command=sign_up">
        <h2>Sign up</h2>
        <div class="login-form">
            <input type="text" placeholder="Name" name="user_name" required
                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$" title="2-40 symbols. First in uppercase">
            <input type="text" placeholder="Surname" name="user_surname" required
                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$" title="2-40 symbols. First in uppercase">
            <input type="email" placeholder="Email" name="user_email" required
                   pattern="^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"
                   title="someletters@letters.letters">
            <input type="text" placeholder="Login" name="user_login" required
                   pattern="([a-zA-Z\\d]{4,40})$"
                   title="4-40 latin symbols & digits">
            <input type="password" placeholder="Password" name="user_password" required
                   pattern="((?=.*\d)(?=.*[a-z]).{6,40})$" title="6-40 symbols">
            <input type="hidden" name="user_role" value="user">
            <p style="color: white">${message_signup}</p>
            <button type="submit">Sign up</button>
        </div>
        <a href="${pageContext.request.contextPath}/jsp/signin.jsp">Log in now!</a>
    </form>
</div>
<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>