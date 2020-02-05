<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title>Add new user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="w3-light-grey">

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <c:choose>
            <c:when test="${language.toString() eq 'ru'}">
                <a href="?command=sign_up&language=en" class="w3-btn w3-round-large">en</a>
                <a href="?command=sign_up&language=ua" class="w3-btn w3-round-large">ua</a>
            </c:when>
            <c:when test="${language.toString() eq 'ua'}">
                <a href="?command=sign_up&language=en" class="w3-btn w3-round-large">en</a>
                <a href="?command=sign_up&language=ru" class="w3-btn w3-round-large">ru</a>
            </c:when>
            <c:when test="${language.toString() eq 'en'}">
                <a href="?command=sign_up&language=ua" class="w3-btn w3-round-large">ua</a>
                <a href="?command=sign_up&language=ru" class="w3-btn w3-round-large">ru</a>
            </c:when>
    </c:choose>
</div>

<fmt:message key="signIn" var="signInText"/>
<fmt:message key="firstName" var="firstName"/>
<fmt:message key="lastName" var="lastName"/>
<fmt:message key="login.login" var="login"/>
<fmt:message key="login.password" var="password"/>
<fmt:message key="userExistsError" var="userExistsError"/>
<fmt:message key="userInputErrorMessage" var="userInputErrorMessage"/>
<fmt:message key="register" var="register"/>
<fmt:message key="greetings" var="greetings"/>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>${register}</h1>
</div>

<c:if test="${requestScope.userExistsErrorMessage == true}">
    <div class="alert alert-danger" role="alert">
        <h1 style="color: red">${userExistsError}</h1>
    </div>
</c:if>
<c:if test="${requestScope.userInputErrorMessage}">
    <div class="alert alert-danger" role="alert">
        <h1 style="color: red">${userInputErrorMessage}</h1>
    </div>
</c:if>
<div class="w3-contain  er w3-center w3-green p-3">
    <h2>${greetings}</h2>
</div>

<div class="container d-flex justify-content-center">
    <div class="row">
        <form method="post" class="w3-selection w3-light-grey w3-padding" id="signUpForm">
            <p>${firstName}</p>
            <input type="text" name="firstName" class="w3-input w3-border w3-round-large" style="width: 110%"><br />
            <p>${lastName}</p>
            <input type="text" name="lastName" class="w3-input w3-border w3-round-large" style="width: 110%"><br />
            <p>Email:</p>
            <input type="text" name="email" class="w3-input w3-border w3-round-large" style="width: 110%"><br />
            <p>${login}</p>
            <input type="text" name="login" class="w3-input w3-border w3-round-large" style="width: 110%"><br />
            <p>${password}</p>
            <input type="password" name="password" class="w3-input w3-border w3-round-large" style="width: 110%"><br />

            <input type="hidden" name="command" value="sign_up" />
            <input type="hidden" name="isPressed" value="true" />

            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" form="signUpForm">Submit</button>
        </form>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <form method="get" id="backToLog">
        <input type="hidden" name="command" value="sign_in" />
        <button type="submit" form="backToLog">${signInText}</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>
</body>
</html>