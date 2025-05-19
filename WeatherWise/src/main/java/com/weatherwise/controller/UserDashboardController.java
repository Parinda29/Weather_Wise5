package com.weatherwise.controller;

import com.weatherwise.config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 * @author Parinda Rai
 */
@WebServlet("/userdashboard")
public class UserDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/Pages/userdashboard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String location = request.getParameter("location");

        try (Connection conn = DbConfig.getdbConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT wr.temperature, wr.humidity, wr.wind_speed, wr.pressure " +
                "FROM location l " +
                "JOIN weather_record wr ON l.weather_record_id = wr.weather_record_id " +
                "WHERE l.location_name = ?");
            ps.setString(1, location);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("city", location);
                request.setAttribute("temperature", rs.getDouble("temperature"));
                request.setAttribute("humidity", rs.getInt("humidity"));
                request.setAttribute("windSpeed", rs.getDouble("wind_speed"));
                request.setAttribute("pressure", rs.getInt("pressure"));
                request.setAttribute("description", "Weather data found!");
            } else {
                request.setAttribute("error", "No weather data found for the selected location.");
            }

            request.getRequestDispatcher("/WEB-INF/Pages/userdashboard.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error retrieving weather data: " + e.getMessage(), e);
        }
    }
}
