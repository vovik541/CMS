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
<fmt:message key="speaker.month" var="month"/>
<fmt:message key="speaker.year" var="year"/>
<fmt:message key="speaker.day" var="day"/>
<fmt:message key="speaker.hour" var="hour"/>
<fmt:message key="speaker.minute" var="minute"/>
<fmt:message key="speaker.location" var="location"/>

<div class="w3-card-4">

    <h1>SPEAKER</h1>

    <div>
        <h1>${offer}</h1>
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
            <p>${year}</p>
                <label><input type="text" name="year"><br /></label>
            <p>${month}</p>
            <label>
                <input type="text" name="month"><br />
            </label>
            <p>${day}</p>
            <label>
                <input type="text" name="day"><br />
            </label>
            <p>${hour}</p>
            <label>
                <input type="text" name="begHour"><br />
            </label>
            <p>${minute}</p>
            <label>
                <input type="text" name="begMin"><br />
            </label>
            <p>${endsAt}</p>
            <p>${hour}</p>
            <label>
                <input type="text" name="endHour"><br />
            </label>
            <p>${minute}</p>
            <label>
                <input type="text" name="endMin"><br />
            </label>

            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" form="offer"
                    <c:set var="action" value="offer_a_speech" scope="session"/>>Submit</button>
        </form>
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
                <td>Accepted By Moder</td>
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
