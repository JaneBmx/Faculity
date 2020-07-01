<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<html>
<head>
    <title><fmt:message bundle="${locale}" key="error"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="error">
    <h1>${pageContext.errorData.statusCode}</h1>
    <hr>
    ${pageContext.exception}
    <p>
        <fmt:message bundle="${locale}" key="error.page.msg"/><br>
        <a href="${pageContext.request.contextPath}/index.jsp">
            <fmt:message bundle="${locale}" key="error.back"/></a>
    </p>
</div>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>