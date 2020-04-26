<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 24.04.2020
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" type="text/css" href="../css/registration.css">
</head>
<body>
<!-- барчик -->
<div class="navWrapper" id="home">
    <div class=" clearfix">
        <h2 class="companyName">cool university</h2>
        <nav class="mainNav clearfix">
            <ul>
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="signin.jsp" class="smoothScroll">Log in</a></li>
                <li><a href="about.jsp" class="smoothScroll">About</a></li>
                <li><a href="contacts.jsp" class="smoothScroll">Contact</a></li>
            </ul>
        </nav>
    </div>
</div>
<div class="main-w3layouts wrapper">
    <h1>Create an Account</h1>
    <div class="main-agileinfo">
        <div class="agileits-top">
            <form action="${pageContext.request.contextPath}/controller?post=Registration" method="post">
                <input class="text" type="text" name="user_name" placeholder="Name" required>
                <input class="text_" type="text" name="user_surname" placeholder="Surname" required>
                <input class="text email" type="email" name="user_email" placeholder="Email" required>
                <input class="text_" type="text" name="user_login" placeholder="Login" required>
                <input class="text" type="password" name="user_password" placeholder="Password" required>
                <input class="text w3lpass" type="password" name="user_password" placeholder="Confirm Password"
                       required>
                <div class="wthree-text">
                </div>
                <input type="submit" name="command" value="registration">
            </form>
            <p>Have an Account? <a href="signin.jsp"> Login Now!</a></p>
        </div>
    </div>
</div>
</body>
</html>
