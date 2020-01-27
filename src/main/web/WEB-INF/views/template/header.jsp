<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>

<header>
    <h1>HEADER</h1>

    <c:out value="${sessionScope.page.toString()}"></c:out>

    <form>
        <button name="language" value="ua" type="submit">ua</button>
        <button name="language" value="en" type="submit">en</button>
        <button name="language" value="ru" type="submit">ru</button>
    </form>

    <c:out value="${language.toString()}"></c:out>

<%--    <ul>--%>
<%--        <li><a href="/?language=en"--%>
<%--                <c:set var="chLang" value="${sessionScope.page.toString()}"--%>
<%--                       scope="session"/>>en</a></li>--%>
<%--        <li><a href="/?language=ua"--%>
<%--                <c:set var="chLang" value="${sessionScope.page.toString()}"--%>
<%--                       scope="session"/>>ua</a></li>--%>
<%--        <li><a href="/?language=ru"--%>
<%--                <c:set var="chLang" value="${sessionScope.page.toString()}"--%>
<%--                       scope="session"/>>ru</a></li>--%>
<%--    </ul>--%>


</header>