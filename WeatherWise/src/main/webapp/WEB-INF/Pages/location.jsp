<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Location Manager</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/location.css">

</head>
<body>

<div class="container">
    <!-- Display success message -->
    <c:if test="${not empty param.message}">
        <div class="alert success">${param.message}</div>
    </c:if>

    <!-- Display error message -->
    <c:if test="${not empty param.error}">
        <div class="alert error">${param.error}</div>
    </c:if>

<!-- Display success message -->


    <div class="card">
        <h2>${recordForUpdate != null ? 'Update Location' : 'Add New Location'}</h2>
        <form action="${pageContext.request.contextPath}/location" method="post">
            <input type="hidden" name="action" value="${recordForUpdate != null ? 'update' : 'add'}" />
            <c:if test="${recordForUpdate != null}">
                <input type="hidden" name="locationId" value="${recordForUpdate.locationId}" />
            </c:if>

            <label>Location Name:</label>
            <input type="text" name="locationName" value="${recordForUpdate.locationName}" required />

            <label>Weather Record ID:</label>
            <input type="number" name="weatherRecordId" value="${recordForUpdate.weatherRecordId}" required />

            <input type="submit" value="${recordForUpdate != null ? 'Update' : 'Add'} Location" />
        </form>
    </div>

    <div class="card">
        <h2>Existing Locations</h2>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Weather Record ID</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="loc" items="${locationList}">
                <tr>
                    <td>${loc.locationId}</td>
                    <td>${loc.locationName}</td>
                    <td>${loc.weatherRecordId}</td>
                    <td>
                        <a href="location?action=edit&id=${loc.locationId}" class="btn edit">Edit</a>
                        <a href="location?action=delete&id=${loc.locationId}" class="btn delete"
                           onclick="return confirm('Are you sure you want to delete this location?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
