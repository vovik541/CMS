<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="w3-container w3-blue-grey w3-opacity w3-right-align">

<%--
    <c:if test="${not empty .role}">
        <h3>${dept.deptName}</h3>
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/login'">Sign In</button>
        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/register'">Sign Up</button>
    </c:if>
--%>

    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/login'">Sign In</button>
            <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/register'">Sign Up</button>
        </c:when>
        <c:when test="${not empty sessionScope.user}">
            <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='/logout'">Sign Out</button>
        </c:when>
        <c:otherwise>
            <%-- Statements which gets executed when all <c:when> tests are false.  --%>
        </c:otherwise>
    </c:choose>


<%--    <c:set var = "salary" scope = "session"/>--%>


</header>
