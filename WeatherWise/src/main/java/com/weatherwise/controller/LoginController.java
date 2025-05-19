package com.weatherwise.controller;

import java.io.IOException;

import com.weatherwise.model.UsersModel;
import com.weatherwise.service.LoginService;
import com.weatherwise.util.CookiesUtil;
import com.weatherwise.util.SessionUtil;
import com.weatherwise.util.RedirectionUtil;
import com.weatherwise.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Parinda Rai
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ValidationUtil validationUtil;
    private RedirectionUtil redirectionUtil;
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        this.validationUtil = new ValidationUtil();
        this.redirectionUtil = new RedirectionUtil();
        this.loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(RedirectionUtil.loginUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Check if the username or password is empty or null
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password is empty!");
            req.setAttribute("error", "Please enter both username and password.");
            req.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(req, resp);
            return;
        }

        if (!validationUtil.isNullOrEmpty(username) && !validationUtil.isNullOrEmpty(password)) {

            // ✅ Hardcoded admin logic first
            if ("parinda".equals(username) && "23056547".equals(password)) {
                SessionUtil.setAttribute(req, "username", username);
                CookiesUtil.addCookie(resp, "role", "admin", 5 * 30); // 5 * 30 minutes
                System.out.println("Admin login successful!");
                req.getRequestDispatcher("/WEB-INF/Pages/admindashboard.jsp").forward(req, resp);
                return;
            }

            UsersModel userModel = new UsersModel(username, password);
            Boolean loginStatus = loginService.loginUser(userModel);

            if (loginStatus != null && loginStatus) {
                SessionUtil.setAttribute(req, "username", username);
                CookiesUtil.addCookie(resp, "role", "user", 5 * 30);

                // ✅ FIXED: Forwarding to JSP inside /WEB-INF
                req.getRequestDispatcher("/WEB-INF/Pages/userdashboard.jsp").forward(req, resp);
            } else {
                handleLoginFailure(req, resp, loginStatus);
            }

        } else {
            redirectionUtil.setMsgAndRedirect(req, resp, "error", "Please fill all the fields!",
                    RedirectionUtil.loginUrl);
        }
    }

    /**
     * Handles login failures by setting attributes and forwarding to the login page.
     */
    private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
            throws ServletException, IOException {
        String errorMessage;
        if (loginStatus == null) {
            errorMessage = "Our server is under maintenance. Please try again later!";
        } else {
            errorMessage = "Invalid username or password. Please try again!";
        }
        req.setAttribute("error", errorMessage);
        req.getRequestDispatcher(RedirectionUtil.loginUrl).forward(req, resp);
    }
}
