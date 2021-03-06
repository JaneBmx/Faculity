<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="admin.page"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

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
            <table width="100%">
                <tr>
                    <td style="width: 2%">ID</td>
                    <td style="width: 29%"><fmt:message bundle="${locale}" key="faculty.name"/></td>
                    <td colspan="3" style="width: 39%"><fmt:message bundle="${locale}" key="faculty.subs"/></td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="faculty.plan.free"/></td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="faculty.plan.paid"/></td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="delete"/></td>
                </tr>
            </table>
            <div class="inner_edit">
                <div id="experemental">
                    <%-- JSON  with all correct faculties --%>
                </div>
            </div>
        </div>

        <%-- Facultues: Add new  --%>
        <div id="Add faculties" class="tabcontent1">
            <div class="add_faculty">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=add_faculty">
                    <table width="60%" style="margin: auto">
                        <tr>
                            <td colspan="2">
                                <h2><fmt:message bundle="${locale}" key="faculty.add.title"/></h2>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.name"/></td>
                            <td><input type="text" placeholder="Name of faculty" name="faculty_name" required
                                       style="width: 100%; height: 2rem;"></td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.plan.free"/></td>
                            <td><input type="number" placeholder="Free accept plan" min="0" max="100" name="free"
                                       required style="width: 100%; height: 2rem;"></td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.plan.paid"/></td>
                            <td><input type="number" placeholder="Paid accept plan" min="0" max="100" name="paid"
                                       required style="width: 100%; height: 2rem;"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><fmt:message bundle="${locale}" key="faculty.choose.sub"/></td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.sub"/> 1:</td>
                            <td>
                                <select name="sub_1_id" style="width: 100%; height: 2rem;">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.sub"/> 2:</td>
                            <td>
                                <select name="sub_2_id" style="width: 100%; height: 2rem;">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty.sub"/> 3:</td>
                            <td>
                                <select name="sub_3_id" style="width:100%; height: 2rem;">
                                    <c:forEach items="${subjects}" var="sub">
                                        <option value="${sub.id}">${sub.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <p style="color: red">${message_add_faculty}</p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <button type="submit" style="position: center">
                                    <fmt:message bundle="${locale}" key="faculty.add"/>
                                </button>
                            </td>
                        </tr>
                    </table>
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

        <div id="All_users" class="tabcontent2" style="display: block">
            <table width="100%">
                <tr>
                    <td style="width: 3%;">ID</td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="user.role"/></td>
                    <td style="width: 17%"><fmt:message bundle="${locale}" key="user.name"/></td>
                    <td style="width: 17%"><fmt:message bundle="${locale}" key="user.surname"/></td>
                    <td style="width: 33%"><fmt:message bundle="${locale}" key="user.email"/></td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="user.login"/></td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="delete"/></td>
                </tr>
            </table>
            <div class="inner_edit">
                <%--Here js will load list of users--%>
                <div id="Search_users"></div>
            </div>
        </div>

        <div id="Edit_user" class="tabcontent2">
            <form method="post" action="${pageContext.request.contextPath}/controller?command=Add_user">
                <table width="60%" style="margin: auto">
                    <tr>
                        <td colspan="2"><h2><fmt:message bundle="${locale}" key="user.add.title"/></h2></td>
                    </tr>
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
                                   title="2-40 symbols. First in uppercase"
                                   style="width: 100%; height: 2rem;"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.surname"/></td>
                        <td><input type="text" placeholder="Surname" name="user_surname" required
                                   pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                   title="2-40 symbols. First in uppercase"
                                   style="width: 100%; height: 2rem;"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.email"/></td>
                        <td><input type="email" placeholder="Email" name="user_email" required
                                   pattern="^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$"
                                   title="someletters@letters.letters"
                                   style="width: 100%; height: 2rem;"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.login"/></td>
                        <td><input type="text" placeholder="Login" name="user_login" required
                                   pattern="([a-zA-Z\\d]{4,40})$"
                                   title="4-40 latin symbols & digits"
                                   style="width: 100%; height: 2rem;"></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.password"/></td>
                        <td><input type="password" placeholder="Password" name="user_password" required
                                   pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                   title="6-40 symbols"
                                   style="width: 100%; height: 2rem;"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p style="color: red">${message_add_user}</p></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" style="margin-left: 30%">
                                <fmt:message bundle="${locale}" key="user.add"/></button>
                        </td>
                    </tr>
                </table>
            </form>
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
            <table width="100%">
                <tr>
                    <td style="width: 2%">ID</td>
                    <td style="width: 28%"><fmt:message bundle="${locale}" key="faculty"/></td>
                    <td style="width: 9%"><fmt:message bundle="${locale}" key="accept.status"/></td>
                    <td style="width: 9%"><fmt:message bundle="${locale}" key="paid.status.free"/></td>
                    <td style="width: 18%"><fmt:message bundle="${locale}" key="privilege"/></td>
                    <td style="width: 12%"><fmt:message bundle="${locale}" key="grade.midmark"/></td>
                    <td style="width: 12%"><fmt:message bundle="${locale}" key="grade.average"/></td>
                    <td style="width: 10%"><fmt:message bundle="${locale}" key="delete"/></td>
                </tr>
            </table>
            <div class="inner_edit">
                <div id="all_grades">
                    <%--Here js will load list of grade reports--%>
                </div>
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
                <%--                <tr>--%>
                <%--                    <td width="50%"><h3 class="red">--%>
                <%--                        <fmt:message bundle="${locale}" key="grade.accept.title"/></h3>--%>
                <%--                    </td>--%>
                <%--                    <td width="50%"><h3 class="blue">--%>
                <%--                        <fmt:message bundle="${locale}" key="grade.nullify.title"/></h3>--%>
                <%--                    </td>--%>
                <%--                </tr>--%>
                <tr style="border: white">
                    <td width="50%"><p><fmt:message bundle="${locale}" key="grade.accept.desc"/></p></td>
                    <td width="50%"><p><fmt:message bundle="${locale}" key="grade.nullify.desc"/></p></td>
                </tr>
                <tr style="border: white">
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

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>

</body>
</html>