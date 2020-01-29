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


<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<c:out value="${language.toString()}"></c:out>

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
        <form>
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
                <p>${year}</p><input type="text" name="year"><br />
                <p>${month}</p><input type="text" name="month"><br />
                <p>${day}</p><input type="text" name="day"><br />
                <p>${hour}</p><input type="text" name="begHour"><br />
                <p>${minute}</p><input type="text" name="begMin"><br />
                <p>${endsAt}</p>
                <p>${hour}</p><input type="text" name="endHour"><br />
                <p>${minute}</p><input type="text" name="endMin"><br />

            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>
    </div>

    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
</div>

</body>
</html>
