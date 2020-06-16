<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="topnav">
    <a class="active" href="${pageContext.request.contextPath}/index.jsp">COOL UNIVERSITY</a>
    <a href="${pageContext.request.contextPath}/jsp/about.jsp">
<%--        <fmt:message bundle="${locale}" key="header.about"/>--%>
        About</a>
    <a href="${pageContext.request.contextPath}/jsp/contacts.jsp">Contact</a>
    <a href="${pageContext.request.contextPath}/controller?command=switch_lang?lang=ru">Language</a>
    <div class="log">
        <c:if test="${user!= null}">
            <a href="${pageContext.request.contextPath}/controller?command=profile"> ${user.login}</a>
            <a href="${pageContext.request.contextPath}/controller?command=log_out">Log out</a>
        </c:if>
        <c:if test="${user== null}">
            <a href="${pageContext.request.contextPath}/controller?command=LOGIN">Log in</a>
        </c:if>
    </div>
</div>