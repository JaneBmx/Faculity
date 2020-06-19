<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<fmt:setLocale value="${lang}"/>--%>
<%--<fmt:setBundle basename="text" var="locale"/>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title><fmt:message bundle="${locale}" key="admin.page"/></title>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">--%>
<%--    <script src="${pageContext.request.contextPath}/js/admin.js"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<jsp:include page="../../include/header.jsp"/>--%>

<div class="admin_block">
    <h2><fmt:message bundle="${locale}" key="admin.admin"/></h2>
    <div class="tab">
        <button class="tablinks" onclick="openCity(event, 'Faculty_tab')">
            <fmt:message bundle="${locale}" key="faculties"/></button>
        <button class="tablinks" onclick="openCity(event, 'Paris')">
            <fmt:message bundle="${locale}" key="users"/></button>
        <button class="tablinks" onclick="openCity(event, 'Tokyo')">
            <fmt:message bundle="${locale}" key="grades"/></button>
    </div>

    <!-- Faculties -->
    <div id="Faculty_tab" class="tabcontent" style="display: block">
        <div class="tab1">
            <button class="tablinks1" onclick="openCity1(event, 'Edit faculties')">
                <fmt:message bundle="${locale}" key="all"/></button>
            <button class="tablinks1" onclick="openCity1(event, 'Add faculties')">
                <fmt:message bundle="${locale}" key="add"/></button>
        </div>

        <%-- All faculties  --%>
        <div id="Edit faculties" class="tabcontent1" style="display: block">
            <div class="inner_edit">
                <div id="experemental">
                </div>
            </div>
        </div>

        <%-- Facultues: Add new  --%>
        <div id="Add faculties" class="tabcontent1">
            <div class="add_faculty">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=add_faculty">
                    <table width="100%">
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.name"/></td>
                            <td><input type="text" placeholder="Name of faculty" name="faculty_name" required></td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.plan.free"/></td>
                            <td><input type="number" placeholder="Free accept plan" min="0" max="100" name="free"
                                       required></td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.plan.paid"/></td>
                            <td><input type="number" placeholder="Paid accept plan" min="0" max="100" name="paid"
                                       required></td>
                        </tr>
                        <tr>
                            <td colspan="2"><fmt:message bundle="${locale}" key="faculty.choose"/></td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.sub"/> 1:</td>
                            <td>
                                <select name="sub_1_id">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.sub"/> 2:</td>
                            <td>
                                <select name="sub_2_id">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.sub"/> 3:</td>
                            <td>
                                <select name="sub_3_id">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <p style="color: red">${message_add_faculty}</p>
                    <button type="submit"><fmt:message bundle="${locale}" key="faculty.add"/></button>
                </form>
            </div>
        </div>
    </div>

    <!-- Users -->
    <div id="Paris" class="tabcontent">
        <div class="tab2">
            <button class="tablinks2" onclick="openCity2(event, 'All_users')">
                <fmt:message bundle="${locale}" key="all"/></button>
            <button class="tablinks2" onclick="openCity2(event, 'Edit_user')">
                <fmt:message bundle="${locale}" key="add"/></button>
        </div>

        <div id="Edit_user" class="tabcontent2">
            <form method="post" action="${pageContext.request.contextPath}/controller?command=Add_user">
                <table>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.role"/></td>
                        <td>
                            <p><input name="user_role" type="radio" value="admin">
                                <fmt:message bundle="${locale}" key="role.admin"/></p>
                            <p><input name="user_role" type="radio" value="user" checked>
                                <fmt:message bundle="${locale}" key="role.user"/>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.name"/></td>
                        <td><input type="text" placeholder="Name" name="user_name" required
                                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                   title="2-40 symbols. First in uppercase"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.surname"/></td>
                        <td><input type="text" placeholder="Surname" name="user_surname" required
                                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                   title="2-40 symbols. First in uppercase"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.email"/></td>
                        <td><input type="email" placeholder="Email" name="user_email" required
                                   pattern="^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"
                                   title="someletters@letters.letters"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.login"/></td>
                        <td><input type="text" placeholder="Login" name="user_login" required
                                   pattern="([a-zA-Z\\d]{4,40})$"
                                   title="4-40 latin symbols & digits"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.password"/></td>
                        <td><input type="password" placeholder="Password" name="user_password" required
                                   pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                   title="6-40 symbols"></td>
                    </tr>
                </table>
                <p>
                    <button type="submit"><fmt:message bundle="${locale}" key="user.add"/></button>
                </p>
            </form>
        </div>
        <div id="All_users" class="tabcontent2" style="display: block">
            <div class="inner_edit" id="Search_users">
                <%--Here js will load list of users--%>
            </div>
        </div>
    </div>

    <!-- Gradereports -->
    <div id="Tokyo" class="tabcontent">
        <div class="tab3">
            <button class="tablinks3" onclick="openCity3(event, 'allGr')">
                <fmt:message bundle="${locale}" key="all"/></button>
            <button class="tablinks3" onclick="openCity3(event, 'acceptGr')">
                <fmt:message bundle="${locale}" key="add"/></button>
        </div>

        <div id="allGr" class="tabcontent3" style="display: block">
            <div id="all_grades" class="inner_edit">
                <%--Here js will load list of grade reports--%>
            </div>
        </div>

        <%-- ACCEPT TAB. TESTED  --%>
        <div id="acceptGr" class="tabcontent3">
            <table>
                <tr>
                    <td colspan="2">
                        <img class="displayed"
                             src="${pageContext.request.contextPath}/img/admin/accept.jpg" alt="photo">
                    </td>
                </tr>
                <tr>
                    <td><h3 class="red" style="background-color: rgba(0,0,0,0.7)">
                        <fmt:message bundle="${locale}" key="grade.accept.title"/></h3>
                    </td>
                    <td><h3 class="blue" style="background-color: rgba(0,0,0,0.7)">
                        <fmt:message bundle="${locale}" key="grade.nullify.title"/></h3>
                    </td>
                </tr>
                <tr>
                    <td><p><fmt:message bundle="${locale}" key="grade.accept.desc"/></p></td>
                    <td><p><fmt:message bundle="${locale}" key="grade.nullify.desc"/></p></td>
                </tr>
                <tr>
                    <td><p><strike><fmt:message bundle="${locale}" key="grade.accept.desc.mtx"/></strike></p></td>
                    <td><p><strike><fmt:message bundle="${locale}" key="grade.nullify.desc.mtx"/></strike>
                    </p></td>
                </tr>
                <tr>
                    <td><input class="rb" type="button" onclick="magicButton()"
                               value=<fmt:message bundle="${locale}" key="grade.accept.button"/></td>
                    <td><input class="bb" type="button" onclick="antiMagicButton()"
                               value=<fmt:message bundle="${locale}" key="grade.nullify.button"/></td>
                </tr>
            </table>
        </div>
    </div>
</div>

<%--<jsp:include page="../../include/footer.jsp"/>--%>

<%--</body>--%>
<%--</html>--%>