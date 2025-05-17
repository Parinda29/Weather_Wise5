<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>WeatherWise Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admindashboard.css">
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h1>WeatherWise</h1>
        <a href="${pageContext.request.contextPath}/admin-dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/location">Location</a>
        <a href="${pageContext.request.contextPath}/weather_record">Weather Record</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="header">
            <h1>Welcome, Admin</h1>
            <p>Here’s the latest weather overview for <strong>${city}</strong></p>
        </div>

        <div class="weather-summary">
            <h3>Temperature</h3>
            <p>${temperature}°C</p>

            <h3>Description</h3>
            <p>${description}</p>

            <div class="details">
                <span>Humidity: ${humidity}%</span>
                <span>Wind Speed: ${windSpeed} km/h</span>
                <span>Pressure: ${pressure} hPa</span>
            </div>
        </div>
    </div>
</body>
</html>
