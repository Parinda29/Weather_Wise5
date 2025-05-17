package com.weatherwise.controller;

import com.weatherwise.model.Weather_RecordModel;
import com.weatherwise.config.DbConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/weather_record")
public class Weather_RecordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle both listing and editing form population
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        try (Connection conn = DbConfig.getdbConnection()) {

            if ("edit".equalsIgnoreCase(action) && id != null) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM weather_record WHERE weather_record_id = ?");
                ps.setInt(1, Integer.parseInt(id));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Weather_RecordModel record = new Weather_RecordModel(
                        rs.getInt("weather_record_id"),
                        rs.getDouble("temperature"),
                        rs.getInt("humidity"),
                        rs.getDouble("wind_speed"),
                        rs.getInt("pressure")
                    );
                    request.setAttribute("recordForUpdate", record);
                }
            }

            if ("delete".equalsIgnoreCase(action) && id != null) {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM weather_record WHERE weather_record_id = ?");
                ps.setInt(1, Integer.parseInt(id));
                ps.executeUpdate();
                response.sendRedirect(request.getContextPath() + "/weather_record?message=Record deleted successfully");
                return;
            }

            // List all records
            List<Weather_RecordModel> weatherRecords = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM weather_record");

            while (rs.next()) {
                weatherRecords.add(new Weather_RecordModel(
                    rs.getInt("weather_record_id"),
                    rs.getDouble("temperature"),
                    rs.getInt("humidity"),
                    rs.getDouble("wind_speed"),
                    rs.getInt("pressure")
                ));
            }

            request.setAttribute("weatherRecords", weatherRecords);
            request.getRequestDispatcher("/WEB-INF/Pages/weather_record.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error loading weather records: " + e.getMessage(), e);
        }
    }

    // Handle add and update
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("weatherRecordId");
        String temperatureStr = request.getParameter("temperature");
        String humidityStr = request.getParameter("humidity");
        String windSpeedStr = request.getParameter("windSpeed");
        String pressureStr = request.getParameter("pressure");

        try (Connection conn = DbConfig.getdbConnection()) {
            // Validate fields
            if (temperatureStr == null || humidityStr == null || windSpeedStr == null || pressureStr == null ||
                temperatureStr.isEmpty() || humidityStr.isEmpty() || windSpeedStr.isEmpty() || pressureStr.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/weather_record?error=All fields are required");
                return;
            }

            double temperature = Double.parseDouble(temperatureStr);
            int humidity = Integer.parseInt(humidityStr);
            double windSpeed = Double.parseDouble(windSpeedStr);
            int pressure = Integer.parseInt(pressureStr);

            if (id == null || id.isEmpty()) {
                // Insert new record
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO weather_record (temperature, humidity, wind_speed, pressure) VALUES (?, ?, ?, ?)");
                ps.setDouble(1, temperature);
                ps.setInt(2, humidity);
                ps.setDouble(3, windSpeed);
                ps.setInt(4, pressure);
                ps.executeUpdate();

                response.sendRedirect(request.getContextPath() + "/weather_record?message=Record added successfully");
            } else {
                // Update existing
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE weather_record SET temperature = ?, humidity = ?, wind_speed = ?, pressure = ? WHERE weather_record_id = ?");
                ps.setDouble(1, temperature);
                ps.setInt(2, humidity);
                ps.setDouble(3, windSpeed);
                ps.setInt(4, pressure);
                ps.setInt(5, Integer.parseInt(id));
                ps.executeUpdate();

                response.sendRedirect(request.getContextPath() + "/weather_record?message=Record updated successfully");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/weather_record?error=Invalid number format");
        } catch (Exception e) {
            throw new ServletException("Error saving record: " + e.getMessage(), e);
        }
    }
}
