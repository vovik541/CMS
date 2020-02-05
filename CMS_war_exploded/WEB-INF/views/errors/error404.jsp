<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        @import url("/resources/css/error.css");
    </style>
    <title>404</title>
</head>
<body>

<svg width="380px" height="500px">

</svg>
<div class="image-box">
    <img STYLE="width: 40%" src="/resources/images/annoyedСhan.png" alt="AnnoyedChan">
</div>
<div class="message-box">
    <h1>404</h1>
    <p>Page not found</p>
    <div class="buttons-con">
        <div class="action-link-wrap">
            <a onclick="history.back(-1)" class="link-button link-back-button">Go Back</a>
            <a href="/controller" class="link-button">Go to Home Page</a>
        </div>
    </div>
</div>
</body>
</html>
