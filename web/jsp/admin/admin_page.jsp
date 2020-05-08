<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h3>Faculties</h3>
        <div class="tab1">
            <button class="tablinks1" onclick="openCity1(event, 'Add faculties')">Add</button>
            <button class="tablinks1" onclick="openCity1(event, 'Edit faculties')">Edit</button>
            <button class="tablinks1" onclick="openCity1(event, 'Search faculties')">Search</button>
        </div>

        <div id="Add faculties" class="tabcontent1">
            <div class="add_faculty">
                <h3>Adding faculity</h3>
                <form>
                    <input type="text" placeholder="Name of faculty" required>
                    <input type="number" placeholder="Free accept plan" min = "0" required>
                    <input type="number" placeholder="Paid accept plan" min = "0" required>


                    <div class="select1" style="width:200px;">
                        check
                        <select>
                            <option value="1">Math</option>
                            <option value="2">Physics</option>
                            <option value="3">Geography</option>
                            <option value="4">Geology</option>
                            <option value="5">Russian</option>
                            <option value="6">English</option>
                            <option value="7">German</option>
                            <option value="8">Spanish</option>
                            <option value="9">French</option>
                            <option value="10">Informatics</option>
                            <option value="11">History</option>
                            <option value="12">Art</option>
                            <option value="13">Chemistry</option>
                            <option value="14">Biology</option>
                            <option value="15">Society</option>
                        </select>
                    </div>

                    <div class="select1" style="width:200px;">
                        <select>
                            <option value="1">Math</option>
                            <option value="2">Physics</option>
                            <option value="3">Geography</option>
                            <option value="4">Geology</option>
                            <option value="5">Russian</option>
                            <option value="6">English</option>
                            <option value="7">German</option>
                            <option value="8">Spanish</option>
                            <option value="9">French</option>
                            <option value="10">Informatics</option>
                            <option value="11">History</option>
                            <option value="12">Art</option>
                            <option value="13">Chemistry</option>
                            <option value="14">Biology</option>
                            <option value="15">Society</option>
                        </select>
                    </div>

                    <div class="select1" style="width:200px;">
                        <select>
                            <option value="1">Math</option>
                            <option value="2">Physics</option>
                            <option value="3">Geography</option>
                            <option value="4">Geology</option>
                            <option value="5">Russian</option>
                            <option value="6">English</option>
                            <option value="7">German</option>
                            <option value="8">Spanish</option>
                            <option value="9">French</option>
                            <option value="10">Informatics</option>
                            <option value="11">History</option>
                            <option value="12">Art</option>
                            <option value="13">Chemistry</option>
                            <option value="14">Biology</option>
                            <option value="15">Society</option>
                        </select>
                    </div>
                </form>
            </div>
        </div>

        <div id="Edit faculties" class="tabcontent1">
            <h2>Edit faculty div</h2>
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
