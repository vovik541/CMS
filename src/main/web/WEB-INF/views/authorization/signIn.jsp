<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>


<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<fmt:message key="login.enter" var="enter"/>
<fmt:message key="login.login" var="logMessage"/>
<fmt:message key="login.password" var="passMessage"/>
<fmt:message key="login.errorLoginPassMessage" var="authError"/>


<div class="w3-card-4">
    <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h2>${enter}</h2>
    </div>

    <c:if test="${requestScope.errorLoginPassMessage == true}">
        <h1 style="color: red">${authError}</h1>
    </c:if>

    <div class="w3-container">
        <form method="post" class="w3-selection w3-light-grey w3-padding-64 w3-margin-top">
            <input type="hidden" name="command" value="sign_in" />
            <p>${logMessage}</p>
            <label>
                <input type="text" name="login" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>
                <p>${passMessage}</p>
                <input type="password" name="password" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form method="get">
            <input type="hidden" name="command" value="sign_up" />
            <input type="hidden" name="action" value="sign_up" />
            <button type="submit">Sign Up</button>
        </form>
        <button class="w3-btn w3-round-large" onclick="location.href='..'">Back to main</button>
    </footer>
</div>


</body>
</html>
