package com.weatherwise.util;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectionUtil {
    public static final String registerUrl = "/register";
    public static final String loginUrl = "/WEB-INF/Pages/login.jsp";

    public void setMsgAndRedirect(HttpServletRequest req, HttpServletResponse resp, String attr, String msg, String path)
            throws IOException, ServletException {
        req.getSession().setAttribute(attr, msg);

        if (path.startsWith("/WEB-INF")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(path);
            dispatcher.forward(req, resp);  // Internal forward for secure views
        } else {
            resp.sendRedirect(req.getContextPath() + path); // External redirect for public routes
        }
    }

    public void redirectToPage(HttpServletRequest req, HttpServletResponse resp, String path)
            throws IOException, ServletException {
        if (path.startsWith("/WEB-INF")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(path);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + path);
        }
    }
}
