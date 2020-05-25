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
            <img class="displayed" src="https://vk.com/images/camera_200.png?ava=1" alt="photo"></p>
        </div>
        <form>
            <button type="submit">Edit photo</button>
        </form>
        <form action="${pageContext.request.contextPath}/controller?command=log_out">
            <button type="submit">Log out</button>
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
                <p>Faculty: ${grade_report.faculty.name}</p>
                <p>Privilege: ${grade_report.privilege.name}</p>
                <p>Accepted status:
                    <c:if test="${grade_report.isAccepted() == true}">Accepted</c:if>
                    <c:if test="${grade_report.isAccepted() == false}">Rejected</c:if>
                </p>
                <p>Free paid status:
                    <c:if test="${grade_report.isFree() == true}">Free</c:if>
                    <c:if test="${grade_report.isFree() == false}">Paid</c:if>
                </p>
            </div>
        </div>

        <%--TAB WITH USER INFO EDIT--%>
        <div id="EditInfo" class="tabcontent">
            <div class="info_block">
                <h2>Edit user info</h2>
                <form class="login" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=edit_user">
                    <div class="login-form">
                        <input type="text" name="user_name" placeholder="Name" required>
                        <input type="text" name="user_surname" placeholder="Surname" required>
                        <input type="password" name="user_password" placeholder="Old password" required>
                        <input type="password" name="new_password" placeholder="New password" required>
                        <p>${message}</p>
                        <button type="submit">Submit changes</button>
                    </div>
                </form>
            </div>
        </div>

        <%--TAB WITH GRADE REPORT EDIT--%>
        <div id="EditRequest" class="tabcontent">
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
                        <div class="choose_priv">
                            <select id="facultySelector" name="faculty_id">
                                <c:forEach items="${faculties}" var="fac">
                                    <option value="${fac.id}"> ${fac.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <!-- privelege & middle mark(double!) -->
                        <div class="row">
                            <div class="choose_fac">
                                <select name="privilege">
                                    <c:forEach items="${privileges}" var="priv">
                                        <option value="${priv.id}">${priv.name} </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div>
                                <label class="required">Average school mark: </label>
                                <input type="number" step="0.1" min="1" max="10" placeholder="0,0" name="attestat_mark">
                            </div>
                        </div>

                        <!-- 3 marks(int) -->
                        <div class="row2">

                            <div>
                                <label class="required">
                                    <div id="mark1">mark 1:</div>
                                </label>
                                <input type="hidden" value="" name="mark_1_subId" id="mark_1_subId">
                                <input type="number" min="1" max="10" value="1" name="mark_1">
                            </div>
                            <div>
                                <label class="required">
                                    <div id="mark2">mark 2:</div>
                                </label>
                                <input type="hidden" value="" name="mark_2_subId" id="mark_2_subId">
                                <input type="number" min="1" max="10" value="1" name="mark_2">
                            </div>
                            <div>
                                <label class="required">
                                    <div id="mark3">mark 3:</div>
                                </label>
                                <input type="hidden" value="" name="mark_3_subId" id="mark_3_subId">
                                <input type="number" min="1" max="10" value="1" name="mark_3">
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