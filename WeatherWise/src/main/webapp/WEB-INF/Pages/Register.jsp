<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - Weather Wise</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
    <div class="register-container">
        <h1>Register</h1>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
            <c:remove var="error" scope="session" />
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post" class="register-form" enctype="multipart/form-data">

            <label for="fullName">Full Name</label>
            <input type="text" id="full_name" name="fullName" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>

            <label for="contact">Contact</label>
            <input type="tel" id="contact" name="contact" pattern="[0-9]{10}" required>

            <label for="location">Location</label>
            <select id="location" name="location" required>
                <option value="" disabled selected>Select your location</option>
                <option value="Kathmandu">Kathmandu</option>
                <option value="Lalitpur">Lalitpur</option>
                <option value="Bhaktapur">Bhaktapur</option>
                <option value="Other">Other</option>
            </select>

            <label for="gender">Gender</label>
            <select id="gender" name="gender" required>
                <option value="" disabled selected>Select your gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>

            <label for="user_name">Username</label>
            <input type="text" id="user_name" name="username" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
            
            <label for="retypePassword">Retype Password</label>
            <input type="password" id="retypePassword" name="retypePassword" required>
            
            <label for="image">Profile Picture</label>
            <input type="file" id="image" name="image" accept="image/*" required>

            <button type="submit">Register</button>

            <c:if test="${not empty sessionScope.success}">
                <div class="success">${sessionScope.success}</div>
            </c:if>
            
        </form>
    </div>
</body>
</html>
