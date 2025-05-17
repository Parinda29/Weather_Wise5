package com.weatherwise.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

	private static final String DB_NAME = "weather_wise";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	/**
	 * Establishes a connection to the database.
	 *
	 * @return Connection object for the database
	 * @throws SQLException           if a database access error occurs
	 * @throws ClassNotFoundException if the JDBC driver class is not found
	 */
	public static Connection getdbConnection() throws SQLException, ClassNotFoundException {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	        System.out.println("Database connection successful!");
	        return conn;
	    } catch (SQLException | ClassNotFoundException e) {
	        System.err.println("Failed to establish database connection: " + e.getMessage());
	        throw e;
	    }
	}
}
