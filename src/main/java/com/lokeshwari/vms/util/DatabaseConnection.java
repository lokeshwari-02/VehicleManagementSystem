package com.lokeshwari.vms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicle_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Lallu@0210";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check if MySQL is running.");
            throw e;
        }
    }
}