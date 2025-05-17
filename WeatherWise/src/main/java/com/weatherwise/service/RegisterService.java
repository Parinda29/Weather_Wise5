package com.weatherwise.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.weatherwise.config.DbConfig;
import com.weatherwise.model.UsersModel;

public class RegisterService {

    private Connection dbConn;

    public RegisterService() {
        try {
            this.dbConn = DbConfig.getdbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Boolean addUsers(UsersModel userModel) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        try {
            // Check if username or email already exists
            String checkQuery = "SELECT COUNT(*) FROM user WHERE user_name = ? OR email = ?";
            PreparedStatement checkStmt = dbConn.prepareStatement(checkQuery);
            checkStmt.setString(1, userModel.getUsername());
            checkStmt.setString(2, userModel.getEmail());

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Duplicate user: Username or email already exists.");
                return false;
            }

            // Insert new user
            String insertQuery = "INSERT INTO user (full_name, email, contact, location, gender, user_name, password, role_id, image) "
                               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = dbConn.prepareStatement(insertQuery);
            stmt.setString(1, userModel.getFullName());
            stmt.setString(2, userModel.getEmail());
            stmt.setString(3, userModel.getContact());
            stmt.setString(4, userModel.getLocation());
            stmt.setString(5, userModel.getGender());
            stmt.setString(6, userModel.getUsername());
            stmt.setString(7, userModel.getPassword());
            stmt.setInt(8, userModel.getRoleId());
            stmt.setString(9, userModel.getImage());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error during user registration: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
