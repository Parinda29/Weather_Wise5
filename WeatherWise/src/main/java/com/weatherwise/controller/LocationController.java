package com.weatherwise.controller;

import com.weatherwise.model.LocationModel;
import com.weatherwise.service.Locationservice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Controller to handle location-related actions (insert, list).
 */
@WebServlet("/location")
public class LocationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Locationservice locationService;

    @Override
    public void init() throws ServletException {
        locationService = new Locationservice(); // Corrected the service class name
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                insertLocation(request, response);
            } else {
                listLocations(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/Pages/location.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            listLocations(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/Pages/location.jsp").forward(request, response);
        }
    }

    private void insertLocation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String locationName = request.getParameter("locationName");
        int weatherRecordId = Integer.parseInt(request.getParameter("weatherRecordId"));

        LocationModel location = new LocationModel();
        location.setLocationName(locationName);
        location.setWeatherRecordId(weatherRecordId);

        locationService.insertLocation(location);

        // Redirect to location list page after insertion
        response.sendRedirect("location");
    }

    private void listLocations(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<LocationModel> locations = locationService.getAllLocations();
        request.setAttribute("locationList", locations);
        request.getRequestDispatcher("/WEB-INF/Pages/location.jsp").forward(request, response);
    }
}
