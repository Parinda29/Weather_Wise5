<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - Weather Wise</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body> 
    <div class="login-container">
        <div class="top-box">
            <p class="signup-text">Don't you have account?</p>
           <a href="${pageContext.request.contextPath}/register">Sign up</a>
        </div>
        <p style="color: red; text-align: center;">${error}</p>
        <div class="login-form-box">
            <h2>WELCOME</h2>
            <form action="login" method="post" class="login-form">
                <div class="input-group">
                    <label for="username"><i class="icon">&#128100;</i></label>
                    <input type="text" id="username" name="username" placeholder="Username" required />
                </div>
                <div class="input-group">
                    <label for="password"><i class="icon">&#128274;</i></label>
                    <input type="password" id="password" name="password" placeholder="Password" required />
                </div>
                <button type="submit" class="login-btn">LOGIN</button>
                
            </form>
        </div>
    </div>
</body>
</html>