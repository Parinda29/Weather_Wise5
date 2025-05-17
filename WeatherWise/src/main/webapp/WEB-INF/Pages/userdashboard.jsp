<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
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
        <form method="get" action="${pageContext.request.contextPath}/user-dashboard">
            <input type="text" name="city" placeholder="Search city..." value="${city}" required>
            <button type="submit">Search</button>
        </form>

        <div class="weather-icon">⛅</div>
        <div class="temperature">${temperature}°C</div>
        <div class="description">${description}</div>

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

        <div class="city">${city}</div>
    </div>

    <!-- Right Panel -->
    <div class="right-panel">
        <div class="tabs">
          
            <form method="get" action="${pageContext.request.contextPath}/user-dashboard" style="display: inline;">
                <input type="hidden" name="city" value="${city}">
                <input type="hidden" name="day" value="today">
                <button type="submit" class="tab ${activeTab == 'today' ? 'active' : ''}">Today</button>
            </form>
            <form method="get" action="${pageContext.request.contextPath}/user-dashboard" style="display: inline;">
                <input type="hidden" name="city" value="${city}">
                <input type="hidden" name="day" value="tomorrow">
                <button type="submit" class="tab ${activeTab == 'tomorrow' ? 'active' : ''}">Tomorrow</button>
            </form>
        </div>

        <div class="grid">
            <div class="card">Wind<br>${windSpeed} km/h</div>
            <div class="card">Humidity<br>${humidity}%</div>
            <div class="card">Real Feel<br>${temperature}°C</div>
            <div class="card">UV Index<br>3 Moderate</div>
            <div class="card">Pressure<br>${pressure} hPa</div>
            <div class="card">Chance of Rain<br>70%</div>
            <div class="card">Temperature History<br>↑ 31°C ↓ 31°C</div>
            <div class="card">Sun<br>Rise: 5:17 am<br>Set: 5:17 am</div>
            <div class="card">Moon<br>Rise: 5:17 am<br>Set: 5:17 am</div>
        </div>

        <div class="footer">
            All Data Provided by <a href="#">Company Name</a>
        </div>
    </div>
</div>
</body>
</html>
