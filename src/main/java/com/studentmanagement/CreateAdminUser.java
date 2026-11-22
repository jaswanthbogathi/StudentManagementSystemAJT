
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreateAdminUser {

    public static void main(String[] args) throws Exception {

        String username = System.getenv("ADMIN_USERNAME");
        String password = System.getenv("ADMIN_PASSWORD");

        if (username == null || username.isBlank()
                || password == null || password.length() < 12) {
            throw new IllegalArgumentException(
                "Set ADMIN_USERNAME and ADMIN_PASSWORD (12+ chars)"
            );
        }

        String sql = "INSERT INTO users "
                + "(username, password, role) "
                + "VALUES (?, ?, 'ADMIN')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, PasswordUtil.hash(password));

            ps.executeUpdate();
            System.out.println("Admin created successfully");
        }
    }
}
