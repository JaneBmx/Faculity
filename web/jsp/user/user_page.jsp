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
        <h2>${user.name} ${user.surname}</h2>
        <div class="photo">
            <img class="displayed" src="${pageContext.request.contextPath}/img/user_default_ava.JPG" alt="photo"></p>
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
                <table>
                    <tr>
                        <td colspan="2"><h2>User info</h2></td>
                    </tr>
                    <tr>
                        <td>Name:</td>
                        <td>${user.name}</td>
                    </tr>
                    <tr>
                        <td>Surname:</td>
                        <td>${user.surname}</td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <td>Login:</td>
                        <td>${user.login}</td>
                    </tr>
                    <tr>
                        <td colspan="2"><h2>Grade report info</h2></td>
                    </tr>

                    <tr>
                        <td>Faculty:</td>
                        <td>${grade_report.faculty.name}</td>
                    </tr>
                    <tr>
                        <td>Privilege:</td>
                        <td>${grade_report.privilege.name}</td>
                    </tr>
                    <tr>
                        <td>Accepted status:</td>
                        <td>
                            <c:if test="${grade_report.isAccepted() == true}">Accepted</c:if>
                            <c:if test="${grade_report.isAccepted() == false}">Rejected</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Free paid status:</td>
                        <td>
                            <c:if test="${grade_report.isFree() == true}">Free</c:if>
                            <c:if test="${grade_report.isFree() == false}">Paid</c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <%--TAB WITH USER INFO EDIT--%>
        <div id="EditInfo" class="tabcontent">
            <div class="info_block">
                <h2>Edit user info</h2>
                <form class="login" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=edit_user">
                    <div class="login-form">
                        <table width="70%">
                            <tr>
                                <td>Name</td>
                                <td><input type="text" name="user_name" placeholder="${user.name}" required
                                           pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                           title="2-40 symbols. First in uppercase"></td>
                            </tr>
                            <tr>
                                <td>Surname</td>
                                <td><input type="text" name="user_surname" placeholder="${user.surname}" required
                                           pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                           title="2-40 symbols. First in uppercase"></td>
                            </tr>
                            <tr>
                                <td>Old password</td>
                                <td><input type="password" name="user_password" placeholder="Old password" required
                                           pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                           title="6-40 symbols">
                                </td>
                            </tr>
                            <tr>
                                <td>New password</td>
                                <td><input type="password" name="new_password" placeholder="New password"
                                           required pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                           title="6-40 symbols"></td>
                            </tr>
                        </table>
                        <p style="color: white">${message_edit_info}</p>
                        <br>
                        <button type="submit">Submit changes</button>
                    </div>
                </form>
            </div>
        </div>

        <%--TAB WITH GRADE REPORT EDIT--%>
        <div id="EditRequest" class="tabcontent">
            <div class="info_block">
                <h2>Edit request</h2>
                <form class="login" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=edit_request">
                    <div class="login-form">
                        <div class="row">
                            <script>
                                const fac = {
                                        <c:forEach items="${faculties}" var="fac">
                                        '${fac.id}':
                                            {
                                                name: "${fac.name}",
                                                subjects: [
                                                    <c:forEach items="${fac.subjects}" var="sub">
                                                    {
                                                        id: ${sub.id},
                                                        name: "${sub.name}"
                                                    },
                                                    </c:forEach>
                                                ]
                                            },
                                        </c:forEach>
                                    }
                                ;
                            </script>
                            <table width="70%">
                                <tr>
                                    <td>Choose faculty:</td>
                                    <td><select id="facultySelector" name="faculty_id">
                                        <c:forEach items="${faculties}" var="fac">
                                            <option value="${fac.id}"> ${fac.name}</option>
                                        </c:forEach>
                                    </select></td>
                                </tr>
                                <tr>
                                    <td>Choose privilege:</td>
                                    <td><select name="privilege">
                                        <c:forEach items="${privileges}" var="priv">
                                            <option value="${priv.id}">${priv.name} </option>
                                        </c:forEach>
                                    </select></td>
                                </tr>

                                <tr>
                                    <td>Average school mark:</td>
                                    <td><input type="number" step="0.1" min="1" max="10" placeholder="0,0"
                                               name="attestat_mark"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="mark1">mark 1:</div>
                                    </td>
                                    <td>
                                        <input type="hidden" value="" name="mark_1_subId" id="mark_1_subId">
                                        <input type="number" min="1" max="10" value="1" name="mark_1">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="mark2">mark 2:</div>
                                    </td>
                                    <td>
                                        <input type="hidden" value="" name="mark_2_subId" id="mark_2_subId">
                                        <input type="number" min="1" max="10" value="1" name="mark_2">
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="mark3">mark 3:</div>
                                    </td>
                                    <td>
                                        <input type="hidden" value="" name="mark_3_subId" id="mark_3_subId">
                                        <input type="number" min="1" max="10" value="1" name="mark_3">
                                    </td>
                                </tr>
                            </table>
                            <button type="submit">Submit changes</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>
</body>
</html>