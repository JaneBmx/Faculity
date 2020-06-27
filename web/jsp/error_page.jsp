<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css">
</head>
<body>
<jsp:include page="../include/header.jsp"/>

<div class="error">
    <h1>${pageContext.errorData.statusCode}</h1>
    <hr>
    <p>
        Бесконечно можно смотреть на три вещи:<br>
        как горит огонь<br>
        как течет вода<br>
        *потом*
    </p>
</div>

<jsp:include page="../include/footer.jsp"/>
</body>
</html>
