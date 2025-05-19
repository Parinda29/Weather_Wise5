<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
<div class="profile-container">
    <div class="profile-image">
        <img src="${user.image}" alt="Profile Picture">
    </div>
    <div class="profile-details">
        <h2>${user.fullName}</h2>
        <p><strong>Gender:</strong> ${user.gender}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Contact:</strong> ${user.contact}</p>
        <p><strong>Location:</strong> ${user.location}</p>
    </div>
</div>
</body>
</html>
