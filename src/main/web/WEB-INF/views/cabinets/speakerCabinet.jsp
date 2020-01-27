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
    <title>Speaker</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<form>
    <button name="language" value="ua" type="submit">ua</button>
    <button name="language" value="en" type="submit">en</button>
    <button name="language" value="ru" type="submit">ru</button>
</form>

<%--<jsp:include page="../template/header.jsp"></jsp:include>--%>

<body class="w3-light-grey">

<c:out value="${language.toString()}"></c:out>

<div class="w3-card-4">

    <h1>SPEAKER</h1>
    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
</div>


</body>
</html>
