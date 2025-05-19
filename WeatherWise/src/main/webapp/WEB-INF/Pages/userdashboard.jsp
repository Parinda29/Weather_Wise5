<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Weather Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userdashboard.css">
</head>
<body>
<div class="container">
    <!-- Left Panel -->
    <div class="left-panel">
        <!-- ✅ Form for city search -->
        <form method="post" action="${pageContext.request.contextPath}/userdashboard">
            <input type="text" name="location" placeholder="Search city..." value="${city}" required>
            <button type="submit">Search</button>
        </form>

        <c:if test="${not empty error}">
            <p style="color: red; margin-top: 10px;">${error}</p>
        </c:if>

        <div class="weather-icon">⛅</div>
        <div class="temperature">
            <c:choose>
                <c:when test="${not empty temperature}">
                    ${temperature}°C
                </c:when>
                <c:otherwise>
                    --°C
                </c:otherwise>
            </c:choose>
        </div>

        <div class="description">
            <c:out value="${description}" default="No weather data available."/>
        </div>

        <%
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
            DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("EEEE, hh:mm a");
        %>
        <div class="date-info">
            <%= now.format(dateFmt) %><br>
            <%= now.format(timeFmt) %><br>
            Day
        </div>

        <div class="city">
            <c:out value="${city}" default="Unknown City"/>
        </div>
    </div>

    <!-- Right Panel -->
    <div class="right-panel">
        <div class="tabs">
            <form method="get" action="${pageContext.request.contextPath}/userdashboard" style="display: inline;">
                <input type="hidden" name="city" value="${city}">
                <input type="hidden" name="day" value="today">
                <button type="submit" class="tab ${activeTab == 'today' ? 'active' : ''}">Today</button>
            </form>
            <form method="get" action="${pageContext.request.contextPath}/userdashboard" style="display: inline;">
                <input type="hidden" name="city" value="${city}">
                <input type="hidden" name="day" value="tomorrow">
                <button type="submit" class="tab ${activeTab == 'tomorrow' ? 'active' : ''}">Tomorrow</button>
            </form>
        </div>

        <div class="grid">
            <div class="card">Temperature<br>${temperature}°C</div>    
            <div class="card">Humidity<br>${humidity}%</div>
            <div class="card">Pressure<br>${pressure} hPa</div>
            <div class="card">Wind<br>${windSpeed} km/h</div>
             
        </div>

        <div class="footer">
            All Data Provided by <a href="#">Company Name</a>
        </div>
    </div>
</div>
</body>
</html>
