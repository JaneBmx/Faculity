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
<%--TODO add checkin pass--%>
<div class="login_form">
    <form method="post" class="login" action="${pageContext.request.contextPath}/controller?command=sign_up">
        <h2>Sign up</h2>
        <div class="login-form">
            <input type="text" placeholder="Name" name="user_name" required>
            <input type="text" placeholder="Surname" name="user_surname" required>
            <input type="email" placeholder="Email" name="user_email" required>
            <input type="text" placeholder="Login" name="user_login" required>
            <input type="password" placeholder="Password" name="user_password" required>
            <input type="password" placeholder="Confirm password" name="new_password" required>
            <input type="hidden" name="user_role" value="user">
            <button type="submit">Sign up</button>
        </div>
        <a href="${pageContext.request.contextPath}/controller?command=login_page">Log in now!</a>
    </form>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>
