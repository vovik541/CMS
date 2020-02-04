<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign out</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<%@ include file="../template/header.jsp" %>

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
<%--<a href="/login">LOGIN</a>--%>
<%--<%@ include file="../template/footer.jsp" %>--%>

</body>
</html>
