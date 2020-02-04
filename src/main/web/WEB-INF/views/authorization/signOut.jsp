<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign out</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="w3-light-grey">

<c:choose>
    <c:when test="${language.toString() eq 'ru'}">
        <a href="?command=sign_out&language=en" class="w3-btn w3-round-large">en</a>
        <a href="?command=sign_out&language=ua" class="w3-btn w3-round-large">ua</a>
    </c:when>
    <c:when test="${language.toString() eq 'ua'}">
        <a href="?command=sign_out&language=en" class="w3-btn w3-round-large">en</a>
        <a href="?command=sign_out&language=ru" class="w3-btn w3-round-large">ru</a>
    </c:when>
    <c:when test="${language.toString() eq 'en'}">
        <a href="?command=sign_out&language=ua" class="w3-btn w3-round-large">ua</a>
        <a href="?command=sign_out&language=ru" class="w3-btn w3-round-large">ru</a>
    </c:when>
</c:choose>

<fmt:message key="signUp" var="signUp"/>
<fmt:message key="signIn" var="signIn"/>


<div>
    <h1>You were logged out</h1>
</div>
<form method="get" id="backToLog">
    <button type="submit" form="backToLog">${signIn}</button>
</form>
<form id="GoToSignUp" method="get">
    <input type="hidden" name="EmptyCommandSignUp" value="up"/>
    <button type="submit" form="GoToSignUp">${signUp}</button>
</form>
<%--<%@ include file="../template/footer.jsp" %>--%>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>

</body>
</html>
