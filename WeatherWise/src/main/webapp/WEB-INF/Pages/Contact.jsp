<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us - WeatherWise</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Contact.css">
</head>
<body>

<div class="contact-container">
    <div class="left-column">
        <!-- You can add a map, image, or contact form here if needed -->
        <img src="${pageContext.request.contextPath}/images/contact-illustration.png" alt="Contact Illustration" class="contact-image"/>
    </div>

    <div class="right-column">
        <h1>Contact Us</h1>
        <div class="contact-details">
            <p><strong>Address:</strong><br>
                Kamalpokhari, Naxal,<br>
                Kathmandu, Nepal
            </p>
            <p><strong>Phone:</strong><br>
                +977 9706455416<br>
                +977 9811366949
            </p>
            <p><strong>Email:</strong><br>
                <a href="mailto:weather_wise@gmail.com">weather_wise@gmail.com</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>
