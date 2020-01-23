<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Registration</h1>
</div>

<div class="w3-container w3-padding">

    <c:if test = "${not empty userName}">
        <h3>${userName}</h3>
        <p>You have been successfully registered!</p>
    </c:if>
        ${requestScope.bundle['value']}

    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>We are glad to see you!</h2>
        </div>
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>First Name:
                <input type="text" name="firstName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Last Name:
                <input type="text" name="lastName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Email:
                <input type="text" name="email" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Login:
                <input type="text" name="login" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Password:
                <input type="password" name="password" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='..'">Back to main</button>
</div>

</body>
</html>