
package com.studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (url == null || url.isBlank()
                || user == null || user.isBlank()
                || password == null) {
            throw new IllegalStateException(
                "Database environment variables are missing"
            );
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                url, user, password
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(
                "Database connection failed", e
            );
        }
    }
}
