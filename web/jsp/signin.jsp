<%--
  Created by IntelliJ IDEA.
  User: Yana
  Date: 24.04.2020
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
<div class="navWrapper" id="home">
    <div class=" clearfix">
        <h2 class="companyName">cool university</h2>
        <nav class="mainNav clearfix">
            <ul>
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="signup.jsp" class="smoothScroll">Create account</a></li>
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
            <form method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="">
                Login: <br/>
                <input class="text_" type="text" name="user_login" required>
                <br/>Password: <br/>
                <input class="text" type="password" name="user_password" required>
                <br/>
                ${errorLoginPassMessage}
                <br/>
                ${wrongAction}
                <br/>
                ${nullPage}
                <br/>
                <input type="submit" value="log in">
            </form>
            <p>Have no Account yet? <a href="signup.jsp"> Registrate now!</a></p>
        </div>
    </div>
</div>
</body>
</html>
