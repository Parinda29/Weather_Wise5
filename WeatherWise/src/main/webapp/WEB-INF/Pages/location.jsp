<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Location Manager</title>
</head>
<body>
    <h2>Add New Location</h2>
    <form action="location" method="post">
        <label>Location Name:</label>
        <input type="text" name="locationName" required /><br/>

        <label>Weather Record ID:</label>
        <input type="number" name="weatherRecordId" required /><br/>

        <input type="submit" value="Add Location" />
    </form>

    <hr/>

    <h2>Existing Locations</h2>
    <table border="1">
        <tr>
            <th>Location ID</th>
            <th>Location Name</th>
            <th>Weather Record ID</th>
        </tr>
        <c:forEach var="loc" items="${locations}">
            <tr>
                <td>${loc.locationId}</td>
                <td>${loc.locationName}</td>
                <td>${loc.weatherRecordId}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
