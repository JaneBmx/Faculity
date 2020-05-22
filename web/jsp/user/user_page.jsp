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

        <%--TAB WITH COMMON INFO--%>
        <div id="Info" class="tabcontent">
            <div class="info_block">
                <h2>Main info</h2>
                <p>Name: ${user.name}</p>
                <p>Surname: ${user.surname}</p>
                <p>Email: ${user.email}</p>
                <p>Login: ${user.login}</p>
                <h2>Request info</h2>
                <p>Faculty:</p>
                <p>Privilege:</p>
                <p>Accepted status:</p>
            </div>
        </div>

        <%--TAB WITH USER INFO EDIT--%>
        <div id="EditInfo" class="tabcontent">
            <div class="info_block">
                <h2>Edit user info</h2>
                <form class="login" method="POST" action="${pageContext.request.contextPath}/controller?command=edit_user">
                    <div class="login-form">
                        <input type="text" name="user_name" placeholder="Name" required>
                        <input type="text" name="user_surname" placeholder="Surname" required>
                        <input type="password" name = "user_password" placeholder="Old password" required>
                        <input type="password" name = "new_password" placeholder="New password" required>
                        <p>${message}</p>
                        <button type="submit">Submit changes</button>
                    </div>
                </form>
            </div>
        </div>

        <%--TAB WITH GRADE REPORT EDIT--%>
        <div id="EditRequest" class="tabcontent">
            <h2>Edit request</h2>
            <form class="login" method="POST" action="${pageContext.request.contextPath}/controller?command=edit_request">
                <div class="login-form">
                    <div class="row">
                        <div>
                            <select name="faculty_id">
                                <c:forEach items="${faculties}" var="fac">
                                    <option value="${fac.id}"> ${fac.name}</option>
                                </c:forEach>
                            </select>
<%--                            <label class="required">Faculty: </label>--%>
<%--                            <input list="faculties" name= "faculty_name"/>--%>
<%--                            <datalist id="faculties">--%>
<%--                                <option value="KSIS">--%>
<%--                                <option value="FITU">--%>
<%--                                <option value="FRE">--%>
<%--                            </datalist>--%>
<%--                        </div>--%>
                    </div>

                    <!-- privelege & middle mark(double!) -->
                    <div class="row">
                        <div>
                            <select name ="privilege">
                                <c:forEach items="${privileges}" var = "priv">
                                    <option value="${priv.id}">${priv.name} </option>
                                </c:forEach>
                            </select>
<%--                            <label class="required">Privilege: </label>--%>
<%--                            <input list="privileges" name= "privilege"/>--%>
<%--                            <datalist id="privileges">--%>
<%--                                <option value="sirotka">--%>
<%--                                <option value="olimpiada">--%>
<%--                                <option value="none">--%>
<%--                            </datalist>--%>
                        </div>
                        <div>
                            <label class="required">Average school mark:  </label>
                            <input type="number" step="0.1" min="1" max="10" placeholder="0,0" name = "attestat_mark">
                        </div>
                    </div>
                    <!-- 3 marks(int) -->
                    <div class="row2">
                        <div>
                            <label class="required">mark 1: </label>
                            <input type="number" min="1" max = "10" value="1" name = "mark_1">
                        </div>
                        <div>
                            <label class="required">mark 2: </label>
                            <input type="number" min="1" max = "10" value="1" name = "mark_2">
                        </div>
                        <div>
                            <label class="required">mark 3: </label>
                            <input type="number" min="1" max = "10" value="1" name = "mark_3">
                        </div>
                        </div>
                        <button type="submit">Submit changes</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
</body>
</html>