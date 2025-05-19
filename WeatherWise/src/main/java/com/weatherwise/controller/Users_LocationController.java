package com.weatherwise.controller;

import com.weatherwise.model.Users_LocationModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Parinda Rai
 */
@WebServlet("/userlocation")
public class Users_LocationController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials - it's better to move these to a config file or environment variables
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/weatherwise_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yourpassword"; // Replace with actual password

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users_LocationModel> userLocations = new ArrayList<>();

        // Get the list of users' location associations from the database
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users_location");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Users_LocationModel ul = new Users_LocationModel();
                ul.setUserId(rs.getInt("user_id"));
                ul.setLocationId(rs.getInt("location_id"));
                userLocations.add(ul);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error retrieving user-location data");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // Set the user locations as a request attribute and forward to the JSP page
        request.setAttribute("userLocations", userLocations);
        request.getRequestDispatcher("/user_location.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userIdStr = request.getParameter("userId");
        String locationIdStr = request.getParameter("locationId");

        // Validate the input parameters
        if (userIdStr != null && locationIdStr != null) {
            try {
                int userId = Integer.parseInt(userIdStr);
                int locationId = Integer.parseInt(locationIdStr);

                // Insert the new user-location record into the database
                try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement ps = conn.prepareStatement(
                             "INSERT INTO users_location (user_id, location_id) VALUES (?, ?)")) {

                    ps.setInt(1, userId);
                    ps.setInt(2, locationId);
                    int rowsAffected = ps.executeUpdate();

                    // Handle the response based on whether the insert was successful
                    if (rowsAffected > 0) {
                        response.sendRedirect(request.getContextPath() + "/userlocation");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/error.jsp");
                    }
                }

            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
