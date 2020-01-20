<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-card-4">
    <div class="w3-container w3-blue-grey w3-opacity w3-right-align">
        <h2>Logging In</h2>
    </div>

    <c:choose>
        <c:when test="${noMatches}">
            <div class="w3-container w3-red">
                <p>Your login or password doesn't matches!</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="w3-container w3-green">
                <p> Hurry up ^_^ </p>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="w3-container">
        <form method="post" class="w3-selection w3-light-grey w3-padding-64 w3-margin-top">
            <p>Login:</p>
            <label>
                <input type="text" name="login" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>
                <p>Password:</p>
                <input type="password" name="password" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <button class="w3-btn w3-round-large" onclick="location.href='..'">Back to main</button>
    </footer>
</div>


</body>
</html>