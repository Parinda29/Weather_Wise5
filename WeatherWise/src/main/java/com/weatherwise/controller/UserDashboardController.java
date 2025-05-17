package com.weatherwise.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/user-dashboard")
public class UserDashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Dummy weather data for today and tomorrow
    private static final Map<String, WeatherData> todayWeather = new HashMap<>();
    private static final Map<String, WeatherData> tomorrowWeather = new HashMap<>();

    static {
        // Today's data
        todayWeather.put("kathmandu", new WeatherData("Kathmandu", "14", "Mostly Clear", "32", "12", "720"));
        todayWeather.put("Lalitpur", new WeatherData("Lalitpur", "31", "Mostly Cloudy", "70", "18", "1010"));
        todayWeather.put("Bhaktapur", new WeatherData("Bhaktapur", "35", "Sunny", "40", "20", "1005"));
        todayWeather.put("Dharan", new WeatherData("Dharan", "35", "Sunny", "40", "20", "1005"));

        // Tomorrow's data
        tomorrowWeather.put("kathmandu", new WeatherData("Kathmandu", "17", "Partly Cloudy", "35", "14", "725"));
        tomorrowWeather.put("Lalitpur", new WeatherData("Lalitpur", "33", "Cloudy", "72", "19", "1012"));
        tomorrowWeather.put("Bhaktapur", new WeatherData("Bhaktapur", "37", "Hot", "38", "22", "1008"));
        tomorrowWeather.put("Dharan", new WeatherData("Dharan", "37", "Hot", "38", "22", "1008"));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityParam = req.getParameter("city");
        String dayParam = req.getParameter("day");
        String userName = "John Doe";

        if (cityParam == null || cityParam.trim().isEmpty()) {
            cityParam = "kathmandu";
        }
        String cityKey = cityParam.toLowerCase();

        if (dayParam == null || dayParam.trim().isEmpty()) {
            dayParam = "today";
        }

        WeatherData data;
        if ("tomorrow".equalsIgnoreCase(dayParam)) {
            data = tomorrowWeather.getOrDefault(cityKey, tomorrowWeather.get("kathmandu"));
        } else {
            data = todayWeather.getOrDefault(cityKey, todayWeather.get("kathmandu"));
        }

        req.setAttribute("userName", userName);
        req.setAttribute("city", data.city);
        req.setAttribute("temperature", data.temperature);
        req.setAttribute("description", data.description);
        req.setAttribute("humidity", data.humidity);
        req.setAttribute("windSpeed", data.windSpeed);
        req.setAttribute("pressure", data.pressure);
        req.setAttribute("activeTab", dayParam);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/Pages/userdashboard.jsp");
        dispatcher.forward(req, resp);
    }

    static class WeatherData {
        String city, temperature, description, humidity, windSpeed, pressure;

        WeatherData(String city, String temperature, String description, String humidity, String windSpeed, String pressure) {
            this.city = city;
            this.temperature = temperature;
            this.description = description;
            this.humidity = humidity;
            this.windSpeed = windSpeed;
            this.pressure = pressure;
        }
    }
}
