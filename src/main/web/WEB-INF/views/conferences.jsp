<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>conferences</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-light-grey">
<%--<%@ include file="template/header.jsp" %>--%>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/login'">Sign In</button>
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/register'">Sign Up</button>
    </c:when>
    <c:when test="${not empty sessionScope.user}">
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/logout'">Sign Out</button>
    </c:when>
    <c:otherwise>
        <%-- Statements which gets executed when all <c:when> tests are false.  --%>
    </c:otherwise>
</c:choose>

<fmt:message key="greetings" />

<fmt:message key="greetings" />

<form>
    <button name="language" value="ua" type="submit">ua</button>
    <button name="language" value="en" type="submit">en</button>
    <button name="language" value="ru" type="submit">ru</button>
</form>

<h1>CONFERENCES!</h1>
<%--<%@ include file="template/footer.jsp" %>--%>
</body>
</html>
