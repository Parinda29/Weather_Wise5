<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weather Forecast</title>
    <link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/header.css" />
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/home.css" />
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/footer.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">

</head>
<body>
    <div class="container">
        <nav class="navbar">
            <div class="logo">BrandLogo</div>
            <ul class="nav-links">
                <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/Profile">Protfolio</a></li>
                <li><a href="${pageContext.request.contextPath}/about-us">About-Us</a></li>
                <li><a href="${pageContext.request.contextPath}/login">login</a></li>
                <li><a href="${pageContext.request.contextPath}/Contact">Contact</a></li>
            </ul>
        </nav>

        <div class="main-content">
            <div class="left-box">
                <h1><span class="highlight">Weather</span> Wise</h1>
                <p>I tried to catch some fog, but i mist.</p>
            </div>
            <div class="right-box">
                <div class="weather-visual">
                    <img src="https://cdn-icons-png.flaticon.com/512/869/869869.png" alt="rain" class="rain-icon">
                </div>
                <div class="weather-person">
                    <img src="https://cdn-icons-png.flaticon.com/512/4140/4140037.png" alt="weather girl" class="person-icon">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
