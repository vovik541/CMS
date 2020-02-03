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
</head>


<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<fmt:message key="speaker.offer" var="offer"/>
<fmt:message key="speaker.confName" var="confName"/>
<fmt:message key="speaker.beginsAt" var="beginsAt"/>
<fmt:message key="speaker.endsAt" var="endsAt"/>
<fmt:message key="speaker.location" var="location"/>

<div class="w3-card-4">

    <h1>SPEAKER</h1>

    <h5>YOUR RATE is ${sessionScope.speakerRate}</h5>

    <div>
        <h1>${offer}</h1>
        <c:if test="true">
            <form method="post" id="offer">
                <input type="hidden" name="command" value="speaker_cabinet" />
                <input type="hidden" name="action" value="offer_a_speech" />

                <p>${confName}</p>
                <label>
                    <input type="text" name="confName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>
                <p>${location}</p>
                <label>
                    <input type="text" name="location"class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                </label>

                <p>${beginsAt}</p>
                <label>
                    <input type="date" name="date">
                </label>
                <label>
                    <input type="time" min="08:00" max="23:00" name="beginsAtTime">
                </label>
                <p>${endsAt}</p>
                <label>
                    <input type="time" min="08:00" max="23:00" name="endsAtTime">
                </label>
                <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" form="offer"
                        <%--<c:set var="action" value="offer_a_speech" scope="session"/>--%>>Submit</button>
            </form>
        </c:if>
        <c:if test="${requestScope.isAdded}">
            <h3>Has been added!</h3>
        </c:if>
        <c:if test="${requestScope.isInputError}">
            <h3>INCORRECT INPUT!!</h3>
        </c:if>
    </div>

    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
    <div>
        <table>
            <tr>
                <th>${confName}</th>
                <th>${location}</th>
                <th>${day}</th>
                <th>${beginsAt}</th>
                <th>${endsAt}</th>
                <th>Confirm</th>
                <th>Accepted By Moder</th>
                <th>More details</th>
                <th>Delete</th>
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
                                    <button type="submit" form="${conf.conferenceId}refuse">refuse</button>
                                </form>
                            </c:when>
                            <c:when test="${not conf.acceptedBySpeaker}">
                                <form method="post" id="${conf.conferenceId}confirm">
                                    <input type="hidden" name="id" value="${conf.conferenceId}" />
                                    <input type="hidden" name="command" value="speaker_cabinet" />
                                    <input type="hidden" name="action" value="confirm_conference" />
                                    <button type="submit" form="${conf.conferenceId}confirm">Confirm</button>
                                </form>
                            </c:when>
                            <c:otherwise></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${conf.acceptedByModer}">ACCEPTED</c:when>
                            <c:otherwise>NOT ACCEPTED</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.conferenceInfo.conferenceId == conf.conferenceId}">
                                <p>presented: ${sessionScope.conferenceInfo.numberOfPresentUsers}</p>
                                <p>registered: ${sessionScope.conferenceInfo.numberOfRegisteredUsers}</p>
                            </c:when>

                            <c:otherwise>
                                <form method="post" id="${conf.conferenceId}info">
                                    <input type="hidden" name="id" value="${conf.conferenceId}" />
                                    <input type="hidden" name="command" value="speaker_cabinet" />
                                    <input type="hidden" name="action" value="get_more_info" />
                                    <input type="hidden" name="id" value="${conf.conferenceId}" />
                                    <button form="${conf.conferenceId}info"></button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${conf.acceptedByModer}">
                                You can not delete conf accepted by Moder
                            </c:when>
                            <c:otherwise>
                                <form method="post" id="${conf.conferenceId}del">
                                    <input type="hidden" name="id" value="${conf.conferenceId}" />
                                    <input type="hidden" name="command" value="speaker_cabinet" />
                                    <input type="hidden" name="action" value="delete_conference" />
                                        <%--                            <c:set var="action" value="delete_conference" scope="session"/>--%>
                                    <button type="submit" form="${conf.conferenceId}del">Delete</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
