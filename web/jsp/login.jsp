<%--
  Created by IntelliJ IDEA.
  User: Bubaleh
  Date: 10.04.2020
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LogIn</title>
    <link rel="stylesheet" type="text/css" href="../css/login.css">
</head>
<body>
<!-- барчик -->
<div class="navWrapper" id="home">
    <div class=" clearfix">
        <h2 class="companyName">cool university</h2>
        <nav class="mainNav clearfix">
            <ul>
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="registration.jsp" class="smoothScroll">Create account</a></li>
                <li><a href="#about" class="smoothScroll">About</a></li>
                <li><a href="#contact" class="smoothScroll">Contact</a></li>
            </ul>
        </nav>
    </div>
</div>
<!-- форма для полей -->
<div class="main-w3layouts wrapper">
    <h1>Create an Account</h1>
    <div class="main-agileinfo">
        <div class="agileits-top">
            <%--            enctype="multipart/form-data"--%>
            <form action="controller" method="post">
                <input class="text_" type="text" name="user_login" placeholder="Login" required>
                <input class="text" type="password" name="user_password" placeholder="Password" required=>
<%--                <div class="wthree-text">--%>
<%--                </div>--%>
                <%--                <input type="hidden" name="command" value="log_in"/>--%>
                <input type="submit" value="login">
            </form>
            <p>Have no Account yet? <a href="registration.jsp"> Registrate now!</a></p>
        </div>
    </div>
</div>
</body>
</html>
