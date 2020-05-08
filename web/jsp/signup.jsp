<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="../include/header.jsp"/>

<div class="login_form">
    <form class="login" method="POST" action="${pageContext.request.contextPath}/controller">
        <h2>Sign up</h2>
        <div class="login-form">
            <input type="text" placeholder="Name" required>
            <input type="text" placeholder="Surname" required>
            <input type="email" placeholder="Email" required>
            <input type="text" placeholder="Login" required>
            <input type="password" placeholder="Password" required>
            <input type="password" placeholder="Confirm password" required>
            <button type="submit">Sign up</button>
        </div>
        <a href="signin.jsp">Log in now!</a>
    </form>
</div>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>
