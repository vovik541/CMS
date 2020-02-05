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
    <title>Moder</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
<fmt:message key="speaker" var="speakerText"/>
<fmt:message key="speaker.incorrectInput" var="incorrectInputMessage"/>
<fmt:message key="speaker.confAdded" var="confAddedMessage"/>
<fmt:message key="speaker.accByModer" var="accByModer"/>
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
<fmt:message key="yes" var="yes"/>
<fmt:message key="no" var="no"/>
<fmt:message key="userId" var="userId"/>
<fmt:message key="firstName" var="firstName"/>
<fmt:message key="lastName" var="lastName"/>
<fmt:message key="login" var="login"/>
<fmt:message key="email" var="email"/>
<fmt:message key="role" var="role"/>
<fmt:message key="next" var="next"/>
<fmt:message key="deny" var="deny"/>
<fmt:message key="accept" var="accept"/>
<fmt:message key="previous" var="previous"/>
<fmt:message key="id" var="id"/>
<fmt:message key="sent" var="sent"/>
<fmt:message key="speakerAcc" var="speakerAcc"/>
<fmt:message key="speakerDidntAcc" var="speakerDidntAcc"/>
<fmt:message key="speakerAgreement" var="speakerAgreement"/>
<fmt:message key="giveAgreement" var="giveAgreement"/>
<fmt:message key="currentConferences" var="currentConferences"/>
<fmt:message key="pastConferences" var="pastConferences"/>
<fmt:message key="speakerId" var="speakerId"/>
<fmt:message key="conferenceId" var="conferenceId"/>
<fmt:message key="subject" var="subject"/>
<fmt:message key="message" var="message"/>

<fmt:message key="moder.accBySpeaker" var="accBySpeaker"/>
<fmt:message key="moder.selectRecRepPage" var="selRecPerPage"/>
<fmt:message key="moder.giveSpeakerRole" var="giveRole"/>
<fmt:message key="moder.makeSpeaker" var="makeSpeaker"/>
<fmt:message key="moder.makeUser" var="makeUser"/>
<fmt:message key="moder.noPermission" var="noPermission"/>
<fmt:message key="moder.letterText" var="letterText"/>
<fmt:message key="moder.changeConferenceName" var="changeConferenceName"/>
<fmt:message key="moder.changeLocation" var="changeLocation"/>
<fmt:message key="moder.changeSpeaker" var="changeSpeaker"/>
<fmt:message key="moder.changeTime" var="changeTime"/>

<fmt:message key="user.rate" var="rate"/>

<div class="container">
    <div class="row">
        <div class="col">
            <form method="post">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="set_records_per_page" />

                <input type="hidden" name="currentPage" value="1">

                <div class="form-group col-md-4">

                    <label for="records">${selRecPerPage}</label>

                    <select class="form-control" id="records" name="recordsPerPage" style="width: 65px">
                        <option value="5" selected>5</option>
                        <option value="10">10</option>
                        <option value="15">15</option>
                        <option value="20">20</option>
                        <option value="25">25</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>
    </div>

    <div class="row>">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">${userId}</th>
                <th scope="col">${firstName}</th>
                <th scope="col">${lastName}</th>
                <th scope="col">${login}</th>
                <th scope="col">${email}</th>
                <th scope="col">${role}</th>
                <th scope="col">${giveRole}</th>
            </tr>
            </thead>
            <c:forEach var="user" items="${requestScope.usersForModerView}">
                <tr style="padding-top: 2px">
                    <td>${user.customerId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <c:choose>
                        <c:when test="${user.role eq 'USER'}">
                            <td>
                                <form method="post">
                                    <input type="hidden" name="command" value="moder_cabinet" />
                                    <input type="hidden" name="action" value="give_speaker_role">
                                    <input type="hidden" name="userId" value="${user.customerId}">
                                    <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                                    <input type="hidden" name="currentPage" value="${requestScope.currentPage}" />
                                    <button type="submit">${makeSpeaker}</button>
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${user.role eq 'SPEAKER'}">
                            <td>
                                <form method="post">
                                    <input type="hidden" name="command" value="moder_cabinet" />
                                    <input type="hidden" name="action" value="give_user_role">
                                    <input type="hidden" name="userId" value="${user.customerId}">
                                    <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                                    <input type="hidden" name="currentPage" value="${requestScope.currentPage}" />
                                    <button type="submit">${makeUser}</button>
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <p>${noPermission}</p>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="row">
        <nav>
            <ul class="pagination">
                <c:if test="${requestScope.currentPage != 1}">
                    <li class="page-item">
                        <form method="post">
                            <input type="hidden" name="command" value="moder_cabinet" />
                            <input type="hidden" name="action" value="set_records_per_page" />
                            <input type="hidden" name="currentPage" value="${requestScope.currentPage - 1}" />
                            <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                            <button class="page-link">${previous}</button>
                        </form>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${requestScope.nOfPages}" var="i">
                    <li class="page-item">
                        <form method="post">
                            <input type="hidden" name="command" value="moder_cabinet" />
                            <input type="hidden" name="action" value="set_records_per_page" />
                            <input type="hidden" name="currentPage" value="${i}" />
                            <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                            <button class="page-link">${i}</button>
                        </form>
                    </li>
                </c:forEach>
                <c:if test="${requestScope.currentPage != requestScope.nOfPages}">
                    <li class="page-item">
                        <form method="post">
                            <input type="hidden" name="command" value="moder_cabinet" />
                            <input type="hidden" name="action" value="set_records_per_page" />
                            <input type="hidden" name="currentPage" value="${requestScope.currentPage + 1}" />
                            <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                            <button class="page-link">${next}</button>
                        </form>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm m-3 p-3" style="background-color: #E5E5E5">
            <h5>${changeTime}</h5>
            <form method="post">
                <p>${conferenceId}</p>
                <input type="number" name="conferenceId">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="change_time" />
                <p>${beginsAt}</p>
                <input type="date" name="date">
                <input type="time" min="08:00" max="23:00" name="beginsAtTime">
                <p>${endsAt}</p>
                <input type="time" min="08:00" max="23:00" name="endsAtTime">
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>
        <div class="col-sm m-3 p-3" style="background-color: #E5E5E5">
            <h5>${changeLocation}</h5>
            <form method="post">
                <p>${conferenceId}</p>
                <input type="number" name="conferenceId">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="change_location" />
                <p>${location}</p>
                <input type="text" name="location" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%"><br />
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>
        <div class="col-sm m-3 p-3" style="background-color: #E5E5E5">
            <h5>${changeConferenceName}</h5>
            <form method="post">
                <p>${conferenceId}</p>
                <input type="number" name="conferenceId">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="change_conference_name" />
                <p>${confName}</p>
                <input type="text" name="conferenceName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%"><br />
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>
        <div class="col-sm m-3 p-3" style="background-color: #E5E5E5">
            <h5>${changeSpeaker}</h5>
            <form method="post">
                <p>${speakerId}</p>
                <input type="number" name="speakerId">
                <p>${conferenceId}</p>
                <input type="number" name="conferenceId">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="change_speaker" />
                <div class=" pt-3 mt-2">
                    <button type="submit" class="btn btn-primary">${submit}</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <h3>${currentConferences}</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${confName}</th>
                <th>${speakerText}</th>
                <th>${location}</th>
                <th>${date}</th>
                <th>${beginsAt}</th>
                <th>${endsAt}</th>
                <th>${speakerAgreement}</th>
                <th>${giveAgreement}</th>
            </tr>
            </thead>
            <c:forEach var="conf" items="${requestScope.currentConferences}">
                <tr>
                    <td>${conf.conferenceId}</td>
                    <td>${conf.confName}</td>
                    <td>${conf.speakerFirstName} ${conf.speakerLastName}</td>
                    <td>${conf.location}</td>
                    <td>${conf.date}</td>
                    <td>${conf.beginsAt}</td>
                    <td>${conf.endsAt}</td>
                    <td>
                        <c:choose>
                            <c:when test="${conf.acceptedBySpeaker eq 'true'}">
                                <p>${speakerAcc}</p>
                            </c:when>
                            <c:otherwise>
                                <p>${speakerDidntAcc}</p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${conf.acceptedByModer eq 'true'}">
                                <form method="post">
                                    <input type="hidden" name="command" value="moder_cabinet" />
                                    <input type="hidden" name="action" value="moder_disagreed">
                                    <input type="hidden" name="conferenceId" value="${conf.conferenceId}">
                                    <button type="submit">${deny}</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="post">
                                    <input type="hidden" name="command" value="moder_cabinet" />
                                    <input type="hidden" name="action" value="moder_agreed">
                                    <input type="hidden" name="conferenceId" value="${conf.conferenceId}">
                                    <button type="submit">${accept}</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="row">

        <h3>${pastConferences}</h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${confName}</th>
                <th>${speakerText}</th>
                <th>${location}</th>
                <th>${date}</th>
                <th>${beginsAt}</th>
                <th>${endsAt}</th>
                <th>${speakerAgreement}</th>
            </tr>
            </thead>
            <c:forEach var="conf" items="${requestScope.pastConferences}">
                <tr>
                    <td>${conf.conferenceId}</td>
                    <td>${conf.confName}</td>
                    <td>${conf.speakerFirstName} ${conf.speakerLastName}</td>
                    <td>${conf.location}</td>
                    <td>${conf.date}</td>
                    <td>${conf.beginsAt}</td>
                    <td>${conf.endsAt}</td>
                    <td>
                        <c:choose>
                            <c:when test="${conf.acceptedBySpeaker eq 'true'}">
                                <p>${speakerAcc}</p>
                            </c:when>
                            <c:otherwise>
                                <p>${speakerDidntAcc}</p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="container pt-3 d-flex justify-content-around" >
    <div class="row" >
        <div class="col pt-3 mr-4" style="background-color: #E5E5E5; ">
            <form method="post" id="offer">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="give_speech" />
                <h3>${offer}</h3>
                <p>${confName}</p>
                <input type="text" name="confName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%"><br />
                <p>${location}</p>
                <input type="text" name="location"class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%"><br />
                <p>${beginsAt}</p>
                <input type="date" name="date">
                <input type="time" min="08:00" max="23:00" name="beginsAtTime">
                <p>${endsAt}</p>
                <input type="time" min="08:00" max="23:00" name="endsAtTime">
                <label for="accepted">${accBySpeaker}</label>

                <select class="form-control" id="accepted" name="acceptedBySpeaker">
                    <option value="true">${yes}</option>
                    <option value="false" selected>${no}</option>
                </select>

                <label for="speakers"><%--${speaker}--%></label>

                <select class="form-control" id="speakers" name="speakerIdOpt">
                    <c:forEach var="speaker" items="${sessionScope.speakersForOption}">
                        <option value="${speaker.customerId}">
                                ${speaker.firstName} ${speaker.lastName}
                        </option>
                    </c:forEach>
                </select>
                <div class="pt-3">
                    <button type="submit" class="btn btn-primary" form="offer">${submit}</button>
                </div>
            </form>
            <c:if test="${requestScope.isAdded}">
                <div class="alert alert-success" role="alert">
                    <h3>${confAddedMessage}</h3>
                </div>
            </c:if>
            <c:if test="${requestScope.isInputError}">
                <div class="alert alert-danger" role="alert">
                    <h3>${incorrectInputMessage}</h3>
                </div>
            </c:if>
        </div>
        <div class="col pt-3 ml-4" style="background-color: #E5E5E5; ">
            <h3>${letterText}</h3>
            <c:if test="${requestScope.letterError == true}">
                <div class="alert alert-danger" role="alert">
                    <h5>${incorrectInputMessage}</h5>
                </div>
            </c:if>
            <form method="post" id="letter">
                <input type="hidden" name="command" value="moder_cabinet" />
                <input type="hidden" name="action" value="sent_letter">
                <p>${subject}</p>
                <input type="text" name="subject" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%">
                <p>${message}</p>
                <input type="text" name="message" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 80%">

                <label for="conferenceIdLt"></label>

                <select class="form-control" id="conferenceIdLt" name="conferenceId" style="width: 80%">
                    <c:forEach var="conference" items="${sessionScope.conferencesToRegisterIn}">
                        <option value="${conference.conferenceId} ">
                                ${conference.confName}
                        </option>
                    </c:forEach>
                </select>
                <div class="pt-3">
                    <button form="letter" class="btn btn-primary">${sent}</button>
                </div>
            </form>
        </div>
    </div>
</div>


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
