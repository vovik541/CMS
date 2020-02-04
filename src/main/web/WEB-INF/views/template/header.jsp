<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<header>
    <c:out value="${sessionScope.page.toString()}"></c:out>

    <c:choose>
        <c:when test="${language.toString() eq 'ru'}">
            <form>
                <button name="language" value="en" type="submit">en</button>
                <button name="language" value="ua" type="submit">ua</button>
            </form>
        </c:when>
        <c:when test="${language.toString() eq 'ua'}">
            <form>
                <button name="language" value="en" type="submit">en</button>
                <button name="language" value="ru" type="submit">ru</button>
            </form>
        </c:when>
        <c:when test="${language.toString() eq 'en'}">
            <form>
                <button name="language" value="ua" type="submit">ua</button>
                <button name="language" value="ru" type="submit">ru</button>
            </form>
        </c:when>
    </c:choose>
</header>