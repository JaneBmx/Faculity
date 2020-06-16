<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="text" var="locale"/>

<div class="topnav">
    <a class="active" href="${pageContext.request.contextPath}/index.jsp">COOL UNIVERSITY</a>
    <a href="${pageContext.request.contextPath}/jsp/about.jsp">
        <fmt:message bundle="${locale}" key="header.about"/></a>
    <a href="${pageContext.request.contextPath}/jsp/contacts.jsp">
        <fmt:message bundle="${locale}" key="header.contacts"/>
    </a>
    <a href="${pageContext.request.contextPath}/controller?command=SWITCH_LANG">
        <fmt:message bundle="${locale}" key="header.language"/>
    </a>
    <div class="log">
        <c:if test="${user!= null}">
            <a href="${pageContext.request.contextPath}/controller?command=profile"> ${user.login}</a>
            <a href="${pageContext.request.contextPath}/controller?command=log_out">
                <fmt:message bundle="${locale}" key="header.logout"/>
            </a>
        </c:if>
        <c:if test="${user== null}">
            <a href="${pageContext.request.contextPath}/controller?command=LOGIN">
                <fmt:message bundle="${locale}" key="header.login"/>
            </a>
        </c:if>
    </div>
</div>