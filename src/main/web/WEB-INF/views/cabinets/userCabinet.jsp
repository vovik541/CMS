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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">

    <style>
        @import url("/resources/css/userCabinet.css");
    </style>
</head>

<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<fmt:message key="speaker.confName" var="confName"/>
<fmt:message key="speaker.beginsAt" var="beginsAt"/>
<fmt:message key="speaker.endsAt" var="endsAt"/>
<fmt:message key="speaker.date" var="date"/>
<fmt:message key="speaker.location" var="location"/>
<fmt:message key="speaker" var="speaker"/>

<fmt:message key="register" var="register"/>
<fmt:message key="signOut" var="signOut"/>

<fmt:message key="user.registerInConference" var="regInConf"/>
<fmt:message key="user.registered" var="registered"/>
<fmt:message key="user.visited" var="haveBeenConf"/>
<fmt:message key="user.giveARate" var="giveRate"/>
<fmt:message key="user.rate" var="rate"/>
<fmt:message key="user.haveRated" var="haveRated"/>

<table>
    <tr>
        <th>${confName}</th>
        <th>${speaker}</th>
        <th>${location}</th>
        <th>${date}</th>
        <th>${beginsAt}</th>
        <th>${endsAt}</th>
        <th>${regInConf}</th>
    </tr>
    <c:forEach var="conf" items="${sessionScope.conferencesToRegisterIn}">
        <tr>
            <td>${conf.confName}</td>
            <td>${conf.speakerFirstName} ${conf.speakerLastName}</td>
            <td>${conf.location}</td>
            <td>${conf.date}</td>
            <td>${conf.beginsAt}</td>
            <td>${conf.endsAt}</td>

            <td>
                <c:choose>
                    <c:when test="${conf.registered}">
                        <p>${registered}</p>
                    </c:when>
                    <c:otherwise>
                        <form method="post" id="${conf.conferenceId}reg">
                            <input type="hidden" name="id" value="${conf.conferenceId}" />
                            <input type="hidden" name="command" value="user_cabinet"/>
                            <input type="hidden" name="action" value="register_in_conference" />
                            <button type="submit" form="${conf.conferenceId}reg">${register}</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${not empty sessionScope.conferencesWasPresentIn}">
    <h1>${haveBeenConf}</h1>
    <table>
        <tr>
            <th>${confName}</th>
            <th>${speaker}</th>
            <th>${location}</th>
            <th>${date}</th>
            <th>${beginsAt}</th>
            <th>${endsAt}</th>
            <th>${giveRate}</th>
        </tr>
        <c:forEach var="conf" items="${sessionScope.conferencesWasPresentIn}">
            <tr>
                <td>${conf.confName}</td>
                <td>${conf.speakerFirstName} ${conf.speakerLastName}</td>
                <td>${conf.location}</td>
                <td>${conf.date}</td>
                <td>${conf.beginsAt}</td>
                <td>${conf.endsAt}</td>

                <td>
                    <c:choose>
                        <c:when test="${conf.rate == 0}">
                            <ul>
                                <li>
                                    <form method="post" id="${conf.conferenceId}r1">
                                        <input type="hidden" name="id" value="${conf.conferenceId}" />
                                        <input type="hidden" name="rate" value="1" />
                                        <input type="hidden" name="action" value="give_rate" />
                                        <button class="rateButton" name="command" value="user_cabinet" form="${conf.conferenceId}r1">
                                            1
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form method="post" id="${conf.conferenceId}r2">
                                        <input type="hidden" name="id" value="${conf.conferenceId}" />
                                        <input type="hidden" name="rate" value="2" />
                                        <input type="hidden" name="action" value="give_rate" />
                                        <button class="rateButton" name="command" value="user_cabinet" form="${conf.conferenceId}r2">
                                            2
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form method="post" id="${conf.conferenceId}r3">
                                        <input type="hidden" name="id" value="${conf.conferenceId}" />
                                        <input type="hidden" name="rate" value="3" />
                                        <input type="hidden" name="action" value="give_rate" />
                                        <button class="rateButton" name="command" value="user_cabinet" form="${conf.conferenceId}r3">
                                            3
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form method="post" id="${conf.conferenceId}r4">
                                        <input type="hidden" name="id" value="${conf.conferenceId}" />
                                        <input type="hidden" name="rate" value="4" />
                                        <input type="hidden" name="action" value="give_rate" />
                                        <button class="rateButton" name="command" value="user_cabinet" form="${conf.conferenceId}r4">
                                            4
                                        </button>
                                    </form>
                                </li>
                                <li>
                                    <form method="post" id="${conf.conferenceId}r5">
                                        <input type="hidden" name="id" value="${conf.conferenceId}" />
                                        <input type="hidden" name="rate" value="5" />
                                        <input type="hidden" name="action" value="give_rate" />
                                        <button class="rateButton" name="command" value="user_cabinet" form="${conf.conferenceId}r5">
                                            5
                                        </button>
                                    </form>
                                </li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <p>${haveRated} ${conf.rate}</p>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<div class="w3-card-4">

    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
            <input type="hidden" name="command" value="sign_out" />
            <button>${signOut}</button>
        </form>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>

</body>
</html>
