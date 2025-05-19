package com.weatherwise.controller;

import java.io.IOException;
import com.weatherwise.model.UsersModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Profile" })
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersModel user = new UsersModel();
        user.setFullName("John Doe");
        user.setGender("Male");
        user.setEmail("john.doe@gmail.com");
        user.setContact("+1 123-456-7890");
        user.setLocation("New York, USA");
        user.setImage(req.getContextPath() + "/resources/images/default-profile.png");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/Pages/Profile.jsp").forward(req, resp);
    }
}
