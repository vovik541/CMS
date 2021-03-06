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
    <title>Sign In</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<fmt:message key="login.enter" var="enter"/>
<fmt:message key="login.login" var="logMessage"/>
<fmt:message key="login.password" var="passMessage"/>
<fmt:message key="login.errorLoginPassMessage" var="authError"/>
<fmt:message key="signUp" var="signUp"/>
<fmt:message key="submit" var="submit"/>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h2>${enter}</h2>
</div>

<div class="w3-card-4">
    <div class="container d-flex justify-content-center">
        <div class="row">
            <div class="col">
                <c:if test="${requestScope.errorLoginPassMessage == true}">
                    <div class="alert alert-danger" role="alert">
                        <h3>${authError}</h3>
                    </div>
                </c:if>
                <form method="post" id="signIn" class="w3-selection w3-light-grey w3-padding-64 w3-margin-top pl-5">
                    <p>${logMessage}</p>
                    <input type="text" name="login" class="w3-input w3-border w3-round-large" style="width: 80%"><br />
                    <p>${passMessage}</p>
                    <input type="password" name="password" class="w3-input w3-border w3-round-large" style="width: 80%"><br />
                    <input type="hidden" name="command" value="sign_in" />
                    <button type="submit" form="signIn" class="w3-btn w3-green w3-round-large w3-margin-bottom">${submit}</button>
                </form>
            </div>

        </div>
    </div>
    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form id="GoToSignUp" method="get">
            <input type="hidden" name="EmptyCommandSignUp" value="up"/>
            <button type="submit" form="GoToSignUp">${signUp}</button>
        </form>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>
</body>
</html>
