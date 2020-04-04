<%--
  Created by IntelliJ IDEA.
  User: Bubaleh
  Date: 16.03.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="WEB-INF/js/index.js"></script>
    <link rel="stylesheet" type="text/css" href="WEB-INF/css/index.css">

</head>
<body>
<div class='container'>

    <div class='button-reg'>
        <div class='login'>SingUp</div>
        <div class='singin'>LogIn</div>
    </div>

    <form name='enter'>
        <input type='text' name='login' class='login-enter' placeholder='Enter yor Login' required></input>
        <input type='text' name='mail' class='mail-enter' placeholder='Enter yor e-mail address' required></input>
        <input type='password' name='password' class='password-enter' placeholder='Enter yor password' required></input>
        <input type='password' name='rapiat-password' class='second-password' placeholder='Repeat yor password'
               required></input>
        <input type='submit' name='submit-reg' class='submit-reg' value='Sing Up'></input>
        <input type='checkbox' name='conditions' class='check-conditions' required></input>
        <a href='#' class='agree'>I have read and agree to </br> the terms of registration</a>
    </form>

    <input type='submit' name='submit-enter' class='submit-enter' value='Log in' required></input>
</div>
</body>
</html>
