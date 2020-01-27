<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">

<jsp:include page="../template/header.jsp"></jsp:include>

<div class="w3-card-4">

    <h1>USER CABINET</h1>
    <footer class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <form>
<%--            <c:set var="command" value="sign_in" scope=""></c:set>--%>
            <input type="hidden" name="command" value="sign_out" />
            <button>SIGN OUT</button>
        </form>
    </footer>
</div>


</body>
</html>
