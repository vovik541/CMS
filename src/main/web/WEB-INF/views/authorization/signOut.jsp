<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign out</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">

<%--<%@ include file="../template/header.jsp" %>--%>

<div>
    <h1>You were logged out</h1>
</div>
<form method="get" id="backToLog">
    <input type="hidden" name="command" value="sign_in" />
    <button type="submit" form="backToLog">Sign In</button>
</form>
<form id="GoToSignUp" method="get">
    <input type="hidden" name="command" value="sign_up" />
    <button type="submit" form="GoToSignUp">Sign Up</button>
</form>
<%--<a href="/login">LOGIN</a>--%>
<%--<%@ include file="../template/footer.jsp" %>--%>

</body>
</html>
