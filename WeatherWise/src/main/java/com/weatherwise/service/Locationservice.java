package com.weatherwise.service;

import com.weatherwise.model.LocationModel;
import com.weatherwise.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Locationservice {

    // Check if location with same name and weather record ID exists
    public boolean locationWithWeatherRecordExists(String locationName, int weatherRecordId) throws SQLException, Exception {
        String sql = "SELECT COUNT(*) FROM location WHERE location_name = ? AND weather_record_id = ?";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, locationName);
            stmt.setInt(2, weatherRecordId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // true if duplicate found
                }
            }
        }
        return false;
    }

    // Insert location with duplicate check
    public void insertLocation(LocationModel location) throws Exception {
        if (locationWithWeatherRecordExists(location.getLocationName(), location.getWeatherRecordId())) {
            throw new SQLException("Location with the same Weather Record ID already exists.");
        }

        String sql = "INSERT INTO location (location_name, weather_record_id) VALUES (?, ?)";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, location.getLocationName());
            stmt.setInt(2, location.getWeatherRecordId());
            stmt.executeUpdate();
        }
    }

    // Get all locations
    public List<LocationModel> getAllLocations() throws Exception {
        List<LocationModel> locations = new ArrayList<>();
        String sql = "SELECT * FROM location";

        try (Connection conn = DbConfig.getdbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LocationModel location = new LocationModel();
                location.setLocationId(rs.getInt("location_id"));
                location.setLocationName(rs.getString("location_name"));
                location.setWeatherRecordId(rs.getInt("weather_record_id"));
                locations.add(location);
            }
        }

        return locations;
    }

    // Get location by ID
    public LocationModel getLocationById(int locationId) throws Exception {
        String sql = "SELECT * FROM location WHERE location_id = ?";
        LocationModel location = null;

        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    location = new LocationModel();
                    location.setLocationId(rs.getInt("location_id"));
                    location.setLocationName(rs.getString("location_name"));
                    location.setWeatherRecordId(rs.getInt("weather_record_id"));
                }
            }
        }

        return location;
    }

    // Update location
    public void updateLocation(LocationModel location) throws Exception {
        String sql = "UPDATE location SET location_name = ?, weather_record_id = ? WHERE location_id = ?";

        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, location.getLocationName());
            stmt.setInt(2, location.getWeatherRecordId());
            stmt.setInt(3, location.getLocationId());
            stmt.executeUpdate();
        }
    }

    // Delete location
    public void deleteLocation(int locationId) throws Exception {
        String sql = "DELETE FROM location WHERE location_id = ?";

        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, locationId);
            stmt.executeUpdate();
        }
    }

    // Get location by name (for search)
    public LocationModel getLocationByName(String locationName) throws Exception {
        String sql = "SELECT * FROM location WHERE location_name = ?";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, locationName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LocationModel location = new LocationModel();
                    location.setLocationId(rs.getInt("location_id"));
                    location.setLocationName(rs.getString("location_name"));
                    location.setWeatherRecordId(rs.getInt("weather_record_id"));
                    return location;
                }
            }
        }
        return null;
    }

}
