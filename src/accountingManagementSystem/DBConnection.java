/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accountingManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author beyza
 */
public class DBConnection {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/accmanagesystem_db?user=root&password=fsmblm";
    private static final String DB_USER = "root"; // MySQL username
    private static final String DB_PASSWORD = "fsmblm"; // MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
