<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Sign in</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<%--TODO add front validation--%>

<div class="login_form">
    <form method="post" class="login" action="${pageContext.request.contextPath}/controller?command=login">
        <h2>Log in</h2>
        <div class="login-form">
            <input type="text" name="user_login" placeholder="login" required>
            <input type="password" name="user_password" placeholder="password" required>
            <%--            <p>${message}</p>--%>
            <button type="submit">Log in</button>
        </div>
        <a href="${pageContext.request.contextPath}/jsp/signup.jsp">Sign up now!</a>
    </form>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>
