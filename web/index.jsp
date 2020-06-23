<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="main"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="main_block">
    <h2>Welcome!</h2>
    <div class = "news"><h3>Latest news</h3>
        Now we have the greatest and usefull faculty, named "Usless faculty".<br>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>
    <br><hr>
    <div class="news"><h3>Common statistics:</h3>
        <table style="width: 40%;">
            <tr>
                <td>Count of faculties:</td>
                <td>77</td>
            </tr>

            <tr>
                <td>Count of places:</td>
                <td>55</td>
            </tr>

            <tr>
                <td>Count of Free paid places:</td>
                <td>777</td>
            </tr>

            <tr>
                <td>Count of enrolles:</td>
                <td>77</td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>