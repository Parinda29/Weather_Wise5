package com.weatherwise.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.weatherwise.model.UsersModel;
import com.weatherwise.service.RegisterService;
import com.weatherwise.util.ImageUtil;
import com.weatherwise.util.PasswordUtil;
import com.weatherwise.util.RedirectionUtil;

@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ImageUtil imageUtil;
    private RegisterService registerService;
    private RedirectionUtil redirectionUtil;

    @Override
    public void init() throws ServletException {
        this.registerService = new RegisterService();
        this.imageUtil = new ImageUtil();
        this.redirectionUtil = new RedirectionUtil();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Pages/Register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UsersModel user = extractUserModel(req, resp);
            if (user == null) return;

            Boolean isRegistered = registerService.addUsers(user);

            if (isRegistered == null) {
                showError(req, resp, "Server error. Please try again later.");
            } else if (!isRegistered) {
                showError(req, resp, "Username or Email already exists. Please use a different one.");
            } else {
                if (uploadImage(req)) {
                	 showSuccess(req, resp, "Successfully registered!", "/WEB-INF/Pages/login.jsp");
                } else {
                    showError(req, resp, "Profile picture upload failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError(req, resp, "Unexpected error occurred during registration.");
        }
    }

    private UsersModel extractUserModel(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String location = req.getParameter("location");
        String gender = req.getParameter("gender");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String retypePassword = req.getParameter("retypePassword");

        if (password == null || !password.equals(retypePassword)) {
            redirectionUtil.setMsgAndRedirect(req, resp, "error", "Passwords do not match.", RedirectionUtil.registerUrl);
            return null;
        }

        String encryptedPassword = PasswordUtil.encrypt(username, password);

        Part image = req.getPart("image");
        String imageUrl = imageUtil.getImageNameFromPart(image);
        int roleId = 2; // default role

        return new UsersModel(fullName, email, contact, location, gender, username, encryptedPassword, imageUrl, roleId);
    }

    private boolean uploadImage(HttpServletRequest req) throws IOException, ServletException {
        Part image = req.getPart("image");
        return imageUtil.uploadImage(image, req.getServletContext().getRealPath("/"), "users");
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        redirectionUtil.setMsgAndRedirect(req, resp, "error", message, RedirectionUtil.registerUrl);
    }

    private void showSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectUrl) throws ServletException, IOException {
        redirectionUtil.setMsgAndRedirect(req, resp, "success", message, redirectUrl);
    }
}
