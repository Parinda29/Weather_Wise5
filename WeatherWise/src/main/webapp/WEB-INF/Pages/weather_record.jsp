<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Weather Records</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/weather_record.css">

</head>
<body>

<h2>Weather Record Management</h2>

<!-- Display messages -->
<c:if test="${not empty param.message}">
    <p class="message">${param.message}</p>
</c:if>

<c:if test="${not empty param.error}">
    <p class="error">${param.error}</p>
</c:if>

<!-- Weather Record Form -->
<form action="${pageContext.request.contextPath}/weather_record" method="post">
    <input type="hidden" name="weatherRecordId" value="${recordForUpdate.weatherRecordId}"/>

    <label>Temperature:</label>
    <input type="text" name="temperature" value="${recordForUpdate.temperature}" required/>

    <label>Humidity:</label>
    <input type="text" name="humidity" value="${recordForUpdate.humidity}" required/>

    <label>Wind Speed:</label>
    <input type="text" name="windSpeed" value="${recordForUpdate.windSpeed}" required/>

    <label>Pressure:</label>
    <input type="text" name="pressure" value="${recordForUpdate.pressure}" required/>

    <input type="submit" value="${recordForUpdate != null ? 'Update' : 'Add'} Weather Record"/>
</form>

<!-- Table of Records -->
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Temperature</th>
        <th>Humidity</th>
        <th>Wind Speed</th>
        <th>Pressure</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="record" items="${weatherRecords}">
        <tr>
            <td>${record.weatherRecordId}</td>
            <td>${record.temperature}</td>
            <td>${record.humidity}</td>
            <td>${record.windSpeed}</td>
            <td>${record.pressure}</td>
            <td>
                <a href="${pageContext.request.contextPath}/weather_record?action=edit&id=${record.weatherRecordId}">Edit</a> |
                <a href="${pageContext.request.contextPath}/weather_record?action=delete&id=${record.weatherRecordId}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>

    <c:if test="${empty weatherRecords}">
        <tr><td colspan="6">No weather records found.</td></tr>
    </c:if>
    </tbody>
</table>

</body>
</html>
