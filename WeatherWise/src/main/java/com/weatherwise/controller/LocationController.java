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
 * @author Parinda Rai
 */
@WebServlet("/location")
public class LocationController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Locationservice locationService;

    @Override
    public void init() throws ServletException {
        locationService = new Locationservice();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equalsIgnoreCase(action)) {
                insertLocation(request, response);
            } else if ("update".equalsIgnoreCase(action)) {
                updateLocation(request, response);
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
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        try {
            if ("edit".equalsIgnoreCase(action) && id != null) {
                LocationModel location = locationService.getLocationById(Integer.parseInt(id));
                request.setAttribute("recordForUpdate", location);
            } else if ("delete".equalsIgnoreCase(action) && id != null) {
                locationService.deleteLocation(Integer.parseInt(id));
                response.sendRedirect("location?message=Location deleted successfully");
                return;
            }

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

        // Check if a location with the same weatherRecordId already exists
        if (locationService.locationWithWeatherRecordExists(locationName, weatherRecordId)) {
            response.sendRedirect("location?error=Location with the same Weather Record ID already exists.");
            return;
        }

        LocationModel location = new LocationModel();
        location.setLocationName(locationName);
        location.setWeatherRecordId(weatherRecordId);

        locationService.insertLocation(location);

        // Redirect with success message
        response.sendRedirect("location?message=Location added successfully");
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String locationName = request.getParameter("locationName");
        int weatherRecordId = Integer.parseInt(request.getParameter("weatherRecordId"));

    
        if (locationService.locationWithWeatherRecordExists(locationName, weatherRecordId)) {
            response.sendRedirect("location?error=Location with the same Weather Record ID already exists.");
            return;
        }

        LocationModel location = new LocationModel(locationId, locationName, weatherRecordId);
        locationService.updateLocation(location);

        // Redirect with success message
        response.sendRedirect("location?message=Location updated successfully");
    }

    private void listLocations(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<LocationModel> locations = locationService.getAllLocations();
        request.setAttribute("locationList", locations); // ✅ This must be set
        request.getRequestDispatcher("/WEB-INF/Pages/location.jsp").forward(request, response);
    }
}
