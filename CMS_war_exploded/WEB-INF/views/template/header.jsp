<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<header class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <c:out value="${sessionScope.page.toString()}"></c:out>

    <c:choose>
        <c:when test="${language.toString() eq 'ru'}">
            <form>
                <button name="language" value="en" type="submit" class="w3-btn w3-round-large">en</button>
                <button name="language" value="ua" type="submit" class="w3-btn w3-round-large">ua</button>
            </form>
        </c:when>
        <c:when test="${language.toString() eq 'ua'}">
            <form>
                <button name="language" value="en" type="submit" class="w3-btn w3-round-large">en</button>
                <button name="language" value="ru" type="submit" class="w3-btn w3-round-large">ru</button>
            </form>
        </c:when>
        <c:when test="${language.toString() eq 'en'}">
            <form>
                <button name="language" value="ua" type="submit" class="w3-btn w3-round-large">ua</button>
                <button name="language" value="ru" type="submit" class="w3-btn w3-round-large">ru</button>
            </form>
        </c:when>
    </c:choose>
</header>