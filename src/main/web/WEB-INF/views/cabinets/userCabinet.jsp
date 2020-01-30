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
    <title>User</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<fmt:message key="speaker.offer" var="offer"/>
<fmt:message key="speaker.confName" var="confName"/>
<fmt:message key="speaker.beginsAt" var="beginsAt"/>
<fmt:message key="speaker.endsAt" var="endsAt"/>
<fmt:message key="speaker.month" var="month"/>
<fmt:message key="speaker.year" var="year"/>
<fmt:message key="speaker.day" var="day"/>
<fmt:message key="speaker.hour" var="hour"/>
<fmt:message key="speaker.minute" var="minute"/>
<fmt:message key="speaker.location" var="location"/>

<table>
    <tr>
        <th>${confName}</th>
        <th>${location}</th>
        <th>${day}</th>
        <th>${beginsAt}</th>
        <th>${endsAt}</th>
<%--        <th>Confirm</th>--%>
<%--        <td>Accepted By Moder</td>--%>
<%--        <th>Delete</th>--%>
    </tr>
    <c:forEach var="conf" items="${sessionScope.conferencesToRegisterIn}">
        <tr>
            <td>${conf.confName}</td>
            <td>${conf.location}</td>
            <td>${conf.date}</td>
            <td>${conf.beginsAt}</td>
            <td>${conf.endsAt}</td>
        </tr>
    </c:forEach>
</table>


<div class="w3-card-4">

    <h1>USER CABINET</h1>
    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
<%--            <c:set var="command" value="sign_in" scope=""></c:set>--%>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
</div>


</body>
</html>
