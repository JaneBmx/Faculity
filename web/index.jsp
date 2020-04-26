<%--
  Created by IntelliJ IDEA.
  User: Bubaleh
  Date: 21.04.2020
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <%--    <script src="${pageContext.request.contextPath}/js/index.js"></script>--%>
</head>
<body>
<header>
    <div class="navWrapper" id="home">
        <div class=" clearfix">
            <h2 class="companyName">cool university</h2>
            <nav class="mainNav clearfix">
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="jsp/signin.jsp" class="smoothScroll">Sign in</a></li>
                    <li><a href="jsp/about.jsp" class="smoothScroll">About</a></li>
                    <li><a href="jsp/contacts.jsp" class="smoothScroll">Contact</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <section class="hero">
        <div class="innerWrapper">
            <h1>May I Have Your Attention, Please!</h1>
            <h3>I'm a tagline, What's up? </h3>
        </div>
    </section>
</header>
</body>
</html>

