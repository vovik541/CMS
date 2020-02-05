<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CMS</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">

<jsp:forward page="/controller"/>

<%--<form method="post">--%>
<%--  <input type="hidden" name="command" value="sign_in">--%>
<%--  <button type="submit"></button>--%>
<%--</form>--%>
<%--<a href="/controller?command=sign_in">LOGIN</a>--%>

</body>
</html>