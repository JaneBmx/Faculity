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
        <button class="tablinks" onclick="openCity(event, 'London')">Faculties</button>
        <button class="tablinks" onclick="openCity(event, 'Paris')">Users</button>
        <button class="tablinks" onclick="openCity(event, 'Tokyo')">Gradereports</button>
    </div>

    <!-- Faculties -->
    <div id="London" class="tabcontent">
        <div class="tab1">
            <button class="tablinks1" onclick="openCity1(event, 'Add faculties')">Add new faculty</button>
            <button class="tablinks1" onclick="openCity1(event, 'Edit faculties')">Edit faculty</button>
            <button class="tablinks1" onclick="openCity1(event, 'Search faculties')">Search</button>
        </div>

        <div id="Add faculties" class="tabcontent1">
            <div class="add_faculty">
                <form method="post" action="${pageContext.request.contextPath}/controller?command=add_faculty">
                    <p><label>Name of faculty: </label>
                        <input type="text" placeholder="Name of faculty" name="faculty_name" required></p>
                    <p><label>Free accept plan: </label>
                        <input type="number" placeholder="Free accept plan" min="0" name="free" required></p>
                    <p><label>Paid accept plan: </label>
                        <input type="number" placeholder="Paid accept plan" min="0" name="paid" required></p>
                    <p><label>First subject: </label>
                        <select name="sub_1_id">
                            <c:forEach items="${subjects}" var="sub">
                                <option value="${sub.id}">${sub.name}</option>
                            </c:forEach>
                        </select></p>

                    <p><label>Second subject: </label>
                        <select name="sub_2_id">
                            <c:forEach items="${subjects}" var="sub">
                                <option value="${sub.id}">${sub.name}</option>
                            </c:forEach>
                        </select></p>

                    <p><label>Third subject: </label>
                        <select name="sub_3_id">
                            <c:forEach items="${subjects}" var="sub">
                                <option value="${sub.id}">${sub.name}</option>
                            </c:forEach>
                        </select></p>
                    <button type="submit">Add</button>
                </form>
            </div>
        </div>

        <div id="Edit faculties" class="tabcontent1">
            <div class="inner_edit">
                <table width="100%">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Free accept plan</th>
                        <th>Paid accept plan</th>
                        <th colspan="3">Subjects</th>
                        <th><th>
                        <th><th>
                    </tr>
                    <c:forEach items="${faculties}" var="fac">
                        <tr>
                            <td>${fac.id}</td>
                            <td>${fac.name}</td>
                            <td>${fac.freeAcceptPlan}</td>
                            <td>${fac.paidAcceptPlan}</td>
                            <c:forEach items="${fac.subjects}" var="sub">
                                <td>${sub.name}</td>
                            </c:forEach>
                            <td><button class="delete">Delete</button><td>
                            <td><button class="edit" >Edit</button><td>
                        </tr>
                    </c:forEach>
                </table>
                <%--                <c:forEach items="${faculties}" var="fac">--%>
                <%--                    <p> ${fac.id} ${fac.name} ${fac.freeAcceptPlan}  ${fac.paidAcceptPlan} <c:forEach items="${fac.subjects}" var="sub"> ${sub.name}</c:forEach></p>--%>
                <%--                </c:forEach>--%>
            </div>
        </div>

        <div id="Search faculties" class="tabcontent1">
            <h2>Search faculty div</h2>
        </div>

    </div>

    <!-- Users -->
    <div id="Paris" class="tabcontent">
        <h3>Users</h3>

        <div class="tab2">
            <button class="tablinks2" onclick="openCity2(event, 'Edit user')">Edit</button>
            <button class="tablinks2" onclick="openCity2(event, 'Search users')">Search</button>
        </div>

        <div id="Edit user" class="tabcontent2">
            <h2>Edit user div</h2>
        </div>

        <div id="Search users" class="tabcontent2">
            <h2>Search user div</h2>
        </div>

        <p>Paris is the capital of France.</p>
    </div>

    <!-- Gradereports -->
    <div id="Tokyo" class="tabcontent">
        <h3>Gradereports</h3>

        <div class="tab3">
            <button class="tablinks3" onclick="openCity3(event, 'Add')">Add</button>
            <button class="tablinks3" onclick="openCity3(event, 'Edit')">Edit</button>
            <button class="tablinks3" onclick="openCity3(event, 'Search')">Search</button>
            <button class="tablinks3" onclick="openCity3(event, 'Accept')">Accept</button>
        </div>

        <div id="Add" class="tabcontent3">
            <h2>Add gradereport div</h2>
        </div>

        <div id="Edit" class="tabcontent3">
            <h2>Edit gradereport div</h2>
        </div>

        <div id="Search" class="tabcontent3">
            <h2>Search gradereport div</h2>
        </div>

        <div id="Accept" class="tabcontent3">
            <h2>Button for accepting srudents</h2>
        </div>


        <p>Tokyo is the capital of Japan.</p>
    </div>
</div>

<jsp:include page="../../include/footer.jsp"/>

</body>
</html>
