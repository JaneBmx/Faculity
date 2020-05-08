<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <script src="${pageContext.request.contextPath}/js/user.js"></script>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="../../include/header.jsp"/>

<div class="main">
    <div class="left">
        <h2>${user.name}  ${user.surname}</h2>
        <div class="photo">
            <img class ="displayed" src="https://vk.com/images/camera_200.png?ava=1" alt="photo"></p>
        </div>
        <form>
            <button type="submit">Edit photo</button>
        </form>
    </div>

    <div class="right">
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Info')">Info</button>
            <button class="tablinks" onclick="openTab(event, 'EditInfo')">Edit info</button>
            <button class="tablinks" onclick="openTab(event, 'EditRequest')">Edit request</button>
        </div>

        <div id="Info" class="tabcontent">
            <div class="info_block">
                <h2>Main info</h2>
                <p>Name: ${user.name}</p>
                <p>Surname: ${user.surname}</p>
                <p>Email: ${user.email}</p>
                <p>Login: ${user.login}</p>
                <h2>Request info</h2>
                <p>Faculty:</p>
                <p>Privelege:</p>
                <p>Accepted status:</p>
            </div>

        </div>
        <div id="EditInfo" class="tabcontent">
            <div class="info_block">
                <h2>Edit user info</h2>
                <form class="login" method="POST" action="${pageContext.request.contextPath}/controller?command=edit_user">
                    <div class="login-form">
                        <input type="text" name="name" placeholder="Name" required>
                        <input type="text" name="surname" placeholder="Surname" required>
                        <input type="password" name = "old_password" placeholder="Old password" required>
                        <input type="password" name = "new_password" placeholder="New password" required>
                        <p>${message}</p>
                        <button type="submit">Submit changes</button>
                    </div>
                </form>
            </div>

        </div>
        <div id="EditRequest" class="tabcontent">
            <h2>EDIT REQUEST BLOCK</h2>
        </div>
    </div>
</div>


<jsp:include page="../../include/footer.jsp"/>
</body>
</html>