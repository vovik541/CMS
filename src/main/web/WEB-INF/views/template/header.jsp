<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
    <h1>HEADER</h1>
<%--    <form>--%>
<%--        <button name="language" value="ua">ua</button>--%>
<%--        <button name="language" value="en">en</button>--%>
<%--        <button name="language" value="ru">ru</button>--%>
<%--    </form>--%>

    <c:out value="${sessionScope.page.toString()}/?language=en"></c:out>


    <ul>
        <li><a href="${sessionScope.page.toString()}/?language=en" name="chLang" value="${sessionScope.page.toString()}">en</a></li>
        <li><a href="${sessionScope.page.toString()}/?language=en" name="chLang" value="${sessionScope.page.toString()}">en</a></li>
<%--        <li><a href="${sessionScope.page.toString()+'?language=en'}">ru</a></li>--%>
<%--        <li><a href="${sessionScope.page.toString()+'?language=en'}">ua</a></li>--%>
    </ul>


</header>