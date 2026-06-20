package com.training.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcInsertDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/java_training_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "testpass1";

    public static void main(String[] args) {
        String sql = "INSERT INTO students(name, department) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "Rahim");
            ps.setString(2, "Computer");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student inserted successfully.");
            }

        } catch (Exception ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }
}
