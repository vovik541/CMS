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


<fmt:message key="speaker.confName" var="confName"/>
<fmt:message key="speaker.beginsAt" var="beginsAt"/>
<fmt:message key="speaker.endsAt" var="endsAt"/>
<fmt:message key="speaker.day" var="day"/>
<fmt:message key="speaker.location" var="location"/>

<h1>MODER</h1>

<div class="w3-card-4">
    <div>

        <h5>Change time</h5>
        <form method="post">
            <p>Conference Id</p>
            <input type="number" name="conferenceId">
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="change_time" />
            <p>${beginsAt}</p>
            <input type="date" name="date">
            <input type="time" min="08:00" max="23:00" name="beginsAtTime">
            <p>${endsAt}</p>
            <input type="time" min="08:00" max="23:00" name="endsAtTime">
            <button type="submit">submit</button>
        </form>

        <h5>Change Location</h5>
        <form method="post">
            <p>Conference Id</p>
            <input type="number" name="conferenceId">
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="change_location" />
            <p>${location}</p>
            <input type="text" name="location"class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            <button type="submit">submit</button>

        </form>

        <h5>Conference Name</h5>
        <form method="post">
            <p>Conference Id</p>
            <input type="number" name="conferenceId">
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="change_conference_name" />
            <p>${confName}</p>
            <input type="text" name="conferenceName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            <button type="submit">submit</button>
        </form>

        <h5>Change Speaker</h5>
        <form method="post">
            <p>Speaker Id</p>
            <input type="number" name="speakerId">
            <p>Conference Id</p>
            <input type="number" name="conferenceId">
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="change_speaker" />
            <button type="submit">submit</button>
        </form>

        <form method="post" id="offer">
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="give_speech" />

            <label>
                <p>${confName}</p>
                <input type="text" name="confName" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                <p>${location}</p>
                <input type="text" name="location"class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                <p>${beginsAt}</p>
                <input type="date" name="date">
                <input type="time" min="08:00" max="23:00" name="beginsAtTime">
                <p>${endsAt}</p>
                <input type="time" min="08:00" max="23:00" name="endsAtTime">
            </label>

            <label for="accepted">Accepted By Speaker</label>

            <select class="form-control" id="accepted" name="acceptedBySpeaker">
                <option value="true">Yes</option>
                <option value="false" selected>No</option>
            </select>

            <label for="speakers">For Speaker</label>

            <select class="form-control" id="speakers" name="speakerIdOpt">
                <c:forEach var="speaker" items="${sessionScope.speakersForOption}">
                    <option value="${speaker.customerId}">
                        ${speaker.firstName} ${speaker.lastName}
                    </option>
                </c:forEach>
            </select>

            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom" form="offer">Submit</button>
        </form>
        <c:if test="${requestScope.isAdded}">
            <h3>Has been added!</h3>
        </c:if>
        <c:if test="${requestScope.isInputError}">
            <h3>INCORRECT INPUT!!</h3>
        </c:if>


        <form method="post">
            <input type="hidden" name="command" value="moder_cabinet" />
            <input type="hidden" name="action" value="set_records_per_page" />

            <input type="hidden" name="currentPage" value="1">

            <div class="form-group col-md-4">

                <label for="records">Select records per page:</label>

                <select class="form-control" id="records" name="recordsPerPage">
                    <option value="5">5</option>
                    <option value="10" selected>10</option>
                    <option value="15">15</option>
                    <option value="20">20</option>
                    <option value="25">25</option>
                </select>

            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <table>
            <tr>
                <th>user ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>role</th>
                <th>Make Speaker</th>
            </tr>
            <c:forEach var="user" items="${requestScope.usersForModerView}">
                <tr>
                    <td>${user.customerId}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <c:choose>
                        <c:when test="${user.role eq 'USER'}">
                            <td>
                                <form>
                                    <input type="hidden" name="command" value="moder_cabinet" />
                                    <input type="hidden" name="action" value="give_speaker_role">
                                    <input type="hidden" name="userId" value="${user.customerId}">
                                    <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                                    <input type="hidden" name="currentPage" value="${requestScope.currentPage}" />
                                    <button type="submit">Make Speaker</button>
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${user.role eq 'SPEAKER'}">
                            <td>
                                <form>
                                    <input type="hidden" name="command" value="moder_cabinet" />
                                    <input type="hidden" name="action" value="give_user_role">
                                    <input type="hidden" name="userId" value="${user.customerId}">
                                    <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                                    <input type="hidden" name="currentPage" value="${requestScope.currentPage}" />
                                    <button type="submit">Make User</button>
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                You have po permission to change this role
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>

       <nav>
           <ul class="pagination">
               <c:if test="${requestScope.currentPage != 1}">
                   <li class="page-item">
                       <form method="post">
                           <input type="hidden" name="command" value="moder_cabinet" />
                           <input type="hidden" name="action" value="set_records_per_page" />
                           <input type="hidden" name="currentPage" value="${requestScope.currentPage - 1}" />
                           <input type="hidden" name="recordsPerPage" value="${requestScope.recordsPerPage}" />
                           <button class="page-link">Previous</button>
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
                           <button class="page-link">Next</button>
                       </form>
                   </li>
               </c:if>
           </ul>

       </nav>

    </div>
    <h3>Current conferences</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>${confName}</th>
            <th>SPEAKER</th>
            <th>${location}</th>
            <th>${day}</th>
            <th>${beginsAt}</th>
            <th>${endsAt}</th>
            <th>Speaker Agreement</th>
            <td>Give Agreement</td>
        </tr>
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
                            Speaker accepted
                        </c:when>
                        <c:otherwise>
                            Speaker didn't accept
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
                                <button type="submit">Not accept</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form method="post">
                                <input type="hidden" name="command" value="moder_cabinet" />
                                <input type="hidden" name="action" value="moder_agreed">
                                <input type="hidden" name="conferenceId" value="${conf.conferenceId}">
                                <button type="submit">Accept</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h3>PAST CONFERENCES</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>${confName}</th>
            <th>SPEAKER</th>
            <th>${location}</th>
            <th>${day}</th>
            <th>${beginsAt}</th>
            <th>${endsAt}</th>
            <th>Speaker Agreement</th>

        </tr>
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
                            Speaker accepted
                        </c:when>
                        <c:otherwise>
                            Speaker didn't accept
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>

    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
</div>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>

</body>
</html>
