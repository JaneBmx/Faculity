<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Administrate</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
    <script src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<jsp:include page="../../include/header.jsp"/>

<div class="admin_block">
    <h2>Admin page</h2>
    <div class="tab">
        <button class="tablinks" onclick="openCity(event, 'Faculty_tab')">Faculties</button>
        <button class="tablinks" onclick="openCity(event, 'Paris')">Users</button>
        <button class="tablinks" onclick="openCity(event, 'Tokyo')">Gradereports</button>
    </div>

    <!-- Faculties -->
    <div id="Faculty_tab" class="tabcontent">
        <div class="tab1">
            <button class="tablinks1" onclick="openCity1(event, 'Edit faculties')">All</button>
            <button class="tablinks1" onclick="openCity1(event, 'Add faculties')">Add new</button>
        </div>

        <%-- Facultues: Add new  --%>
        <div id="Add faculties" class="tabcontent1">
            <div class="add_faculty">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=add_faculty">
                    <table width="100%">
                        <tr>
                            <td>Name of faculty:</td>
                            <td><input type="text" placeholder="Name of faculty" name="faculty_name" required></td>
                        </tr>
                        <tr>
                            <td>Free accept plan:</td>
                            <td><input type="number" placeholder="Free accept plan" min="0" max="100" name="free"
                                       required></td>
                        </tr>
                        <tr>
                            <td>Paid accept plan:</td>
                            <td><input type="number" placeholder="Paid accept plan" min="0" max="100" name="paid"
                                       required></td>
                        </tr>
                        <tr>
                            <td>Subject 1:</td>
                            <td>
                                <select name="sub_1_id">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Subject 2:</td>
                            <td>
                                <select name="sub_2_id">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Subject 3:</td>
                            <td>
                                <select name="sub_3_id">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <button type="submit">Add faculty</button>
                </form>
            </div>
        </div>

        <div id="Edit faculties" class="tabcontent1">
            <div class="inner_edit">
                <div id="experemental">
                </div>
            </div>
        </div>
    </div>

    <!-- Users -->
    <div id="Paris" class="tabcontent">
        <div class="tab2">
            <button class="tablinks2" onclick="openCity2(event, 'All_users')">All</button>
            <button class="tablinks2" onclick="openCity2(event, 'Edit_user')">Add admin</button>
        </div>

        <div id="Edit_user" class="tabcontent2">
            <form method="post" action="${pageContext.request.contextPath}/controller?command=Add_user">
                <table>
                    <tr>
                        <td>Role:</td>
                        <td>
                            <p><input name="user_role" type="radio" value="admin">Admin</p>
                            <p><input name="user_role" type="radio" value="user" checked>User</p>
                        </td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" placeholder="Name" name="user_name" required
                                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                   title="2-40 symbols. First in uppercase"></td>
                    </tr>
                    <tr>
                        <td>Surname:</td>
                        <td><input type="text" placeholder="Surname" name="user_surname" required
                                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                   title="2-40 symbols. First in uppercase"></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" placeholder="Email" name="user_email" required
                                   pattern="^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"
                                   title="someletters@letters.letters"></td>
                    </tr>
                    <tr>
                        <td>Login:</td>
                        <td><input type="text" placeholder="Login" name="user_login" required
                                   pattern="([a-zA-Z\\d]{4,40})$"
                                   title="4-40 latin symbols & digits"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" placeholder="Password" name="user_password" required
                                   pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                   title="6-40 symbols"></td>
                    </tr>
                </table>
                <%--                <p style="color:white;"> ${message}</p>--%>
                <p>
                    <button type="submit">Create</button>
                </p>
            </form>
        </div>
        <div id="All_users" class="tabcontent2">
            <div class="inner_edit" id="Search_users">
                <%--Here js will load list of users--%>
            </div>
        </div>
    </div>

    <!-- Gradereports -->
    <div id="Tokyo" class="tabcontent">
        <div class="tab3">
            <button class="tablinks3" onclick="openCity3(event, 'allGr')">All</button>
            <button class="tablinks3" onclick="openCity3(event, 'acceptGr')">Accept</button>
        </div>
        <div id="allGr" class="tabcontent3">
            <div id="all_grades" class="inner_edit">
                <%--Here js will load list of grade reports--%>
            </div>
        </div>

        <%-- ACCEPT TAB. TESTED  --%>
        <div id="acceptGr" class="tabcontent3">
            <table>
                <tr>
                    <td colspan="2">
                        <img class="displayed" src="${pageContext.request.contextPath}/img/admin/accept.jpg"
                             alt="photo">
                    </td>
                </tr>
                <tr>
                    <td><h3 class="red">RED BUTTON</h3></td>
                    <td><h3 class="blue">BLUE BUTTON</h3></td>
                </tr>
                <tr>
                    <td><p>The red button represents an uncertain future — it would free from the enslaving control of
                        the
                        machine - generated dream world and allow to escape into the real world, but living the "truth
                        of reality" is harsher and more difficult</p></td>
                    <td><p>The blue button represents a beautiful prison — it would lead back to ignorance, live in
                        confined
                        comfort without want or fear within the simulated reality of the Matrix</p></td>
                </tr>
                <tr>
                    <td><p><strike>This button will accept all suitable grade reports according faculty acception
                        plan</strike></p></td>
                    <td><p><strike>This button will reset accept and free paid status all of the grade reports</strike>
                    </p></td>
                </tr>
                <tr>
                    <td><input class="rb" type="button" onclick="magicButton()" value="Enroll!"></td>
                    <td><input class="bb" type="button" onclick="antiMagicButton()" value="Nullify enroll statuses">
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

</body>
</html>