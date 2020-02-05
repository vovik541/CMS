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

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
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
</div>

<fmt:message key="signUp" var="signUp"/>
<fmt:message key="signIn" var="signIn"/>
<fmt:message key="signOutMessage" var="signOutMessage"/>

<div class="container d-flex justify-content-center pt-5">
        <div>
            <div class="alert alert-info mr-5 pr-5" role="alert">
                <h1>${signOutMessage}</h1>
            </div>
            <div class="row">
                <div class="col">
                    <form method="get" id="backToLog">
                        <button type="submit" class="btn btn-primary" form="backToLog">${signIn}</button>
                    </form>
                </div>
                <div class="col">
                    <form id="GoToSignUp" method="get">
                        <input type="hidden" name="EmptyCommandSignUp" value="up"/>
                        <button type="submit" class="btn btn-primary" form="GoToSignUp">${signUp}</button>
                    </form>
                </div>
            </div>
        </div>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>

</body>
</html>
