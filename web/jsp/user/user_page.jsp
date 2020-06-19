<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message bundle="${locale}" key="profile"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user.css">
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">--%>
    <script src="${pageContext.request.contextPath}/js/user.js"></script>
    <meta charset="UTF-8">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="main">
    <div class="left">
        <h2>${user.name} ${user.surname}</h2>
        <div class="photo">
            <img class="displayed" src="${pageContext.request.contextPath}/img/user_default_ava.JPG" alt="photo"></p>
        </div>
        <form>
            <button type="submit"><fmt:message bundle="${locale}" key="profile.photo"/></button>
        </form>
    </div>

    <div class="right">
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Info')">
                <fmt:message bundle="${locale}" key="tab.info"/></button>
            <button class="tablinks" onclick="openTab(event, 'EditInfo')">
                <fmt:message bundle="${locale}" key="tab.info.edit"/></button>
            <button class="tablinks" onclick="openTab(event, 'EditRequest')">
                <c:if test="${grade_report == null}">
                    <fmt:message bundle="${locale}" key="tab.grade.create"/></c:if>
                <c:if test="${grade_report != null}">
                    <fmt:message bundle="${locale}" key="tab.grade.edit"/></c:if>
            </button>
        </div>

        <%--INFO--%>
        <div id="Info" class="tabcontent" style="display: block">
            <div class="info_block">
                <table>
                    <%-- Profile info  --%>
                    <tr>
                        <td colspan="2"><h2><fmt:message bundle="${locale}" key="profile.info.user"/></h2></td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.name"/></td>
                        <td>${user.name}</td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.surname"/></td>
                        <td>${user.surname}</td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.email"/></td>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <td><fmt:message bundle="${locale}" key="user.login"/></td>
                        <td>${user.login}</td>
                    </tr>
                    <%-- Grade report info --%>
                    <tr>
                        <td colspan="2"><h2><fmt:message bundle="${locale}" key="profile.info.grade"/></h2></td>
                    </tr>
                    <c:if test="${grade_report != null}">
                        <tr>
                            <td><fmt:message bundle="${locale}" key="faculty"/></td>
                            <td>${grade_report.faculty.name}</td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="privilege"/></td>
                            <td>${grade_report.privilege.name}</td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="accept.status"/></td>
                            <td>
                                <c:if test="${grade_report.isAccepted() == true}">
                                    <fmt:message bundle="${locale}" key="accept.status.accept"/></c:if>
                                <c:if test="${grade_report.isAccepted() == false}">
                                    <fmt:message bundle="${locale}" key="accept.status.reject"/></c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:message bundle="${locale}" key="paid.status"/></td>
                            <td>
                                <c:if test="${grade_report.isFree() == true}">
                                    <fmt:message bundle="${locale}" key="paid.status.free"/>
                                </c:if>
                                <c:if test="${grade_report.isFree() == false}">
                                    <fmt:message bundle="${locale}" key="paid.status.paid"/>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${grade_report == null}">
                        <tr>
                            <td colspan="2" style="color: red">
                                <fmt:message bundle="${locale}" key="grade.nofound"/>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>

        <%--EDIT INFO--%>
        <div id="EditInfo" class="tabcontent">
            <div class="info_block">
                <h2><fmt:message bundle="${locale}" key="edit.info"/></h2>
                <form class="login" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=edit_user">
                    <div class="login-form">
                        <table width="70%">
                            <tr>
                                <td><fmt:message bundle="${locale}" key="user.name"/></td>
                                <td><input type="text" name="user_name" placeholder="${user.name}" required
                                           pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                           title=<fmt:message bundle="${locale}" key="tip.name"/>></td>
                            </tr>
                            <tr>
                                <td><fmt:message bundle="${locale}" key="user.surname"/></td>
                                <td><input type="text" name="user_surname" placeholder="${user.surname}" required
                                           pattern="([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$"
                                           title=<fmt:message bundle="${locale}" key="tip.surname"/>></td>
                            </tr>
                            <tr>
                                <td><fmt:message bundle="${locale}" key="edit.pass.old"/></td>
                                <td><input type="password" name="user_password" placeholder="Old password" required
                                           pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                           title=<fmt:message bundle="${locale}" key="tip.password.old"/>>
                                </td>
                            </tr>
                            <tr>
                                <td><fmt:message bundle="${locale}" key="edit.pass.nes"/></td>
                                <td><input type="password" name="new_password" placeholder="New password"
                                           required pattern="((?=.*\d)(?=.*[a-z]).{6,40})$"
                                           title=<fmt:message bundle="${locale}" key="tip.password"/>></td>
                            </tr>
                        </table>
                        <p style="color: white">${message_edit_info}</p>
                        <br>
                        <button type="submit"><fmt:message bundle="${locale}" key="edit.submit"/></button>
                    </div>
                </form>
            </div>
        </div>

        <%--TAB WITH GRADE REPORT EDIT--%>
        <div id="EditRequest" class="tabcontent">
            <div class="info_block">
                <c:if test="${grade_report == null}">
                    <h2><fmt:message bundle="${locale}" key="edit.grade.create"/></h2>
                </c:if>
                <c:if test="${grade_report != null}">
                    <h2><fmt:message bundle="${locale}" key="edit.grade"/></h2>
                </c:if>
                <form class="login" method="POST"
                      action="${pageContext.request.contextPath}/controller?command=edit_request">
                    <input type="hidden" name="user_id" value="${user.id}">
                    <div class="login-form">
                        <div class="row">
                            <%-- Creating json with full faculty list  --%>
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
                                    <%-- TODO add default faculty --%>
                                    <td><fmt:message bundle="${locale}" key="grade.fac"/></td>
                                    <td><select id="facultySelector" name="faculty_id" required>
                                        <c:forEach items="${faculties}" var="fac">
                                            <option value="${fac.id}"> ${fac.name}</option>
                                        </c:forEach>
                                    </select></td>
                                </tr>
                                <tr>
                                    <td><fmt:message bundle="${locale}" key="grade.privilege"/></td>
                                    <td><select name="privilege" required>
                                        <c:forEach items="${privileges}" var="priv">
                                            <option value="${priv.id}">${priv.name} </option>
                                        </c:forEach>
                                    </select></td>
                                </tr>

                                <tr>
                                    <td><fmt:message bundle="${locale}" key="grade.mark"/></td>
                                    <td><input type="number" step="0.1" min="1" max="10" placeholder="0,0"
                                               name="attestat_mark" required></td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="mark1">mark 1:</div>
                                    </td>
                                    <td>
                                        <input type="hidden" value="" name="mark_1_subId" id="mark_1_subId">
                                        <input type="number" min="1" max="10" value="1" name="mark_1" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="mark2">mark 2:</div>
                                    </td>
                                    <td>
                                        <input type="hidden" value="" name="mark_2_subId" id="mark_2_subId">
                                        <input type="number" min="1" max="10" value="1" name="mark_2" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div id="mark3">mark 3:</div>
                                    </td>
                                    <td>
                                        <input type="hidden" value="" name="mark_3_subId" id="mark_3_subId">
                                        <input type="number" min="1" max="10" value="1" name="mark_3" required>
                                    </td>
                                </tr>
                            </table>
                            <button type="submit"><fmt:message bundle="${locale}" key="edit.submit"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>