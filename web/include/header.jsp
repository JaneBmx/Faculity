<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="logo">
        <a href="../index.jsp">
            <h2>cool university</h2>
        </a>
    </div>

    <ul class="nav">
        <li><a href="../jsp/about.jsp">about</a></li>
        <li><a href="../jsp/contacts.jsp">contacts</a></li>
        <li><a href="">language</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=login_page">login</a></li>
    </ul>
</header>
