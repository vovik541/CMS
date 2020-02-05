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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">

</head>
<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<fmt:message key="speaker.offer" var="offer"/>
<fmt:message key="speaker.confName" var="confName"/>
<fmt:message key="speaker.beginsAt" var="beginsAt"/>
<fmt:message key="speaker.endsAt" var="endsAt"/>
<fmt:message key="speaker.date" var="date"/>
<fmt:message key="speaker.location" var="location"/>
<fmt:message key="speaker" var="speaker"/>
<fmt:message key="speaker.incorrectInput" var="incorrectInputMessage"/>
<fmt:message key="speaker.confAdded" var="confAddedMessage"/>
<fmt:message key="speaker.moreDetails" var="moreDetails"/>
<fmt:message key="speaker.accByModer" var="accByModer"/>
<fmt:message key="speaker.present" var="present"/>
<fmt:message key="speaker.registered" var="registered"/>
<fmt:message key="speaker.denyMessage" var="denyMessage"/>

<fmt:message key="signOut" var="signOut"/>
<fmt:message key="submit" var="submit"/>
<fmt:message key="delete" var="delete"/>
<fmt:message key="confirm" var="confirm"/>
<fmt:message key="refuse" var="refuse"/>
<fmt:message key="status" var="status"/>
<fmt:message key="accepted" var="accepted"/>
<fmt:message key="notAccepted" var="notAccepted"/>
<fmt:message key="show" var="show"/>
<fmt:message key="currentConferences" var="currentConferences"/>

<fmt:message key="user.rate" var="rate"/>

<div class="w3-card-4">

    <h5>${rate} ${sessionScope.speakerRate}</h5>

    <div class="container d-flex justify-content-center">
        <div class="row">
            <div class="col">
                <div>
                    <h3>${currentConferences}</h3>
                </div>
                <table class="table table-striped">
                    <tr>
                        <th>${confName}</th>
                        <th>${location}</th>
                        <th>${date}</th>
                        <th>${beginsAt}</th>
                        <th>${endsAt}</th>
                        <th>${status}</th>
                        <th>${accByModer}</th>
                        <th>${moreDetails}</th>
                        <th>${delete}</th>
                    </tr>
                    <c:forEach var="conf" items="${sessionScope.speakerConfList}">
                        <tr>
                            <td>${conf.confName}</td>
                            <td>${conf.location}</td>
                            <td>${conf.date}</td>
                            <td>${conf.beginsAt}</td>
                            <td>${conf.endsAt}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${conf.acceptedBySpeaker}">
                                        <form method="post" id="${conf.conferenceId}refuse">
                                            <input type="hidden" name="id" value="${conf.conferenceId}" />
                                            <input type="hidden" name="command" value="speaker_cabinet" />
                                            <input type="hidden" name="action" value="refuse_conference" />
                                            <button type="submit" form="${conf.conferenceId}refuse" class="btn btn-primary" >${refuse}</button>
                                        </form>
                                    </c:when>
                                    <c:when test="${not conf.acceptedBySpeaker}">
                                        <form method="post" id="${conf.conferenceId}confirm">
                                            <input type="hidden" name="id" value="${conf.conferenceId}" />
                                            <input type="hidden" name="command" value="speaker_cabinet" />
                                            <input type="hidden" name="action" value="confirm_conference" />
                                            <button type="submit" form="${conf.conferenceId}confirm" class="btn btn-primary" >${confirm}</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise></c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${conf.acceptedByModer}">${accepted}</c:when>
                                    <c:otherwise>${notAccepted}</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${sessionScope.conferenceInfo.conferenceId == conf.conferenceId}">
                                        <p>${present} ${sessionScope.conferenceInfo.numberOfPresentUsers}</p>
                                        <p>${registered} ${sessionScope.conferenceInfo.numberOfRegisteredUsers}</p>
                                    </c:when>

                                    <c:otherwise>
                                        <form method="post" id="${conf.conferenceId}info">
                                            <input type="hidden" name="id" value="${conf.conferenceId}" />
                                            <input type="hidden" name="command" value="speaker_cabinet" />
                                            <input type="hidden" name="action" value="get_more_info" />
                                            <input type="hidden" name="id" value="${conf.conferenceId}" />
                                            <button form="${conf.conferenceId}info" class="btn btn-primary" >${show}</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${conf.acceptedByModer}">
                                        <p>${denyMessage}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <form method="post" id="${conf.conferenceId}del">
                                            <input type="hidden" name="id" value="${conf.conferenceId}" />
                                            <input type="hidden" name="command" value="speaker_cabinet" />
                                            <input type="hidden" name="action" value="delete_conference" />
                                            <button type="submit" form="${conf.conferenceId}del" class="btn btn-primary" >${delete}</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="container d-flex justify-content-center">
        <div class="row">
            <div class="col m-3 p-3" style="background-color: #E5E5E5">
                <h1>${offer}</h1>
                <c:if test="true">
                    <form method="post" id="offer">
                        <input type="hidden" name="command" value="speaker_cabinet" />
                        <input type="hidden" name="action" value="offer_a_speech" />

                        <p>${confName}</p>
                        <input type="text" name="confName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%"><br />
                        <p>${location}</p>
                        <input type="text" name="location"class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%"><br />
                        <p>${beginsAt}</p>
                        <input type="date" name="date">
                        <input type="time" min="08:00" max="23:00" name="beginsAtTime">
                        <p>${endsAt}</p>
                        <input type="time" min="08:00" max="23:00" name="endsAtTime">
                        <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" form="offer">${submit}</button>
                    </form>
                </c:if>
                <c:if test="${requestScope.isAdded}">
                    <div class="alert alert-info" role="alert">
                        <h3>${confAddedMessage}</h3>
                    </div>
                </c:if>
                <c:if test="${requestScope.isInputError}">
                    <div class="alert alert-danger" role="alert">
                        <h3>${incorrectInputMessage}</h3>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form method="post">
            <input type="hidden" name="command" value="sign_out" />
            <button type="submit">${signOut}</button>
        </form>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>
</body>
</html>
