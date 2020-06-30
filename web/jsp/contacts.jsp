<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="contacts"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/contacts.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="infa">
    <table>
        <tr>
            <td colspan="2">
                <h2><fmt:message bundle="${locale}" key="contacts"/></h2>
            </td>
        </tr>
        <tr>
            <td><fmt:message bundle="${locale}" key="user.email"/>:</td>
            <td>contact@examle.heh</td>
        </tr>
        <tr>
            <td><fmt:message bundle="${locale}" key="phone"/>:</td>
            <td>8-800-555-35-35</td>
        </tr>
        <tr>
            <td><fmt:message bundle="${locale}" key="address"/>:</td>
            <td><fmt:message bundle="${locale}" key="address.curr"/></td>
        </tr>
        <tr></tr>
        <tr></tr>
    </table>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>