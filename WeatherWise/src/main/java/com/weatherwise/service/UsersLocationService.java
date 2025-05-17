package com.weatherwise.service;

import com.weatherwise.config.DbConfig;
import com.weatherwise.model.Users_LocationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersLocationService {

    public boolean assignLocationToUser(Users_LocationModel ul) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO user_location (user_id, location_id) VALUES (?, ?)";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ul.getUserId());
            stmt.setInt(2, ul.getLocationId());

            return stmt.executeUpdate() > 0;
        }
    }

    public List<Users_LocationModel> getAllUserLocations() throws SQLException, ClassNotFoundException {
        List<Users_LocationModel> list = new ArrayList<>();
        String query = "SELECT * FROM user_location";
        try (Connection conn = DbConfig.getdbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Users_LocationModel ul = new Users_LocationModel(
                    rs.getInt("user_id"),
                    rs.getInt("location_id")
                );
                list.add(ul);
            }
        }
        return list;
    }

    public boolean deleteUserLocation(int userId, int locationId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM user_location WHERE user_id = ? AND location_id = ?";
        try (Connection conn = DbConfig.getdbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, locationId);
            return stmt.executeUpdate() > 0;
        }
    }
}
