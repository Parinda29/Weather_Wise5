package com.weatherwise.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Parinda Rai
 */
@WebServlet("/admin-dashboard")
public class AdminDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L; 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("city", "Kathmandu");
        request.setAttribute("temperature", "14");
        request.setAttribute("description", "Mostly Clear");
        request.setAttribute("humidity", "32");
        request.setAttribute("windSpeed", "12");
        request.setAttribute("pressure", "720");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Pages/admindashboard.jsp");
        dispatcher.forward(request, response);
    }
}
