<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="message"/>

<html lang="${language}">
<head>
    <title></title>

</head>
<body>
<div>
    <h1>My super app1!</h1>
</div>
${1 < 2}
<div>       <!-- content -->
    <div>    <!-- buttons holder -->
        <button name="listName" onclick="location.href='/list'">List users</button>
        <button name="addName" onclick="location.href='/add'">Add user</button>
    </div>
</div>
</body>
</html>