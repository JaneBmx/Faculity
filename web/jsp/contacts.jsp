<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="infa">
    <h2>Contacts</h2>
    <table>
        <tr>
            <td>email:</td>
            <td>contact@examle.heh</td>
        </tr>
        <tr>
            <td>phone:</td>
            <td>8-800-555-35-35</td>
        </tr>
        <tr>
            <td>address:</td>
            <td>st. Hmmmmm, 45, Minsk, Belarus</td>
        </tr>
        <tr></tr>
        <tr></tr>
    </table>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>