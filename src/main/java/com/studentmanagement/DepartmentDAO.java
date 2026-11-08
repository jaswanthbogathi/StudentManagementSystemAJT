
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    // =========================
    // ADD DEPARTMENT
    // =========================
    public void addDepartment(Department department) {

        String sql =
                "INSERT INTO departments (department_name) VALUES (?)";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                throw new SQLException("Database connection failed");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, department.getDepartmentName());

                ps.executeUpdate();

                System.out.println("Department Added Successfully!");
            }

        } catch (SQLException e) {
            System.err.println("Error adding department: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // =========================
    // VIEW ALL DEPARTMENTS
    // =========================
    public List<Department> getAllDepartments() {

        List<Department> departments = new ArrayList<>();

        String sql = "SELECT * FROM departments ORDER BY department_id";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                throw new SQLException("Database connection failed");
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Department department = new Department();

                    department.setDepartmentId(
                            rs.getInt("department_id")
                    );

                    department.setDepartmentName(
                            rs.getString("department_name")
                    );

                    departments.add(department);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error fetching departments: " + e.getMessage());
            e.printStackTrace();
        }

        return departments;
    }


    // =========================
    // GET DEPARTMENT BY ID
    // =========================
    public Department getDepartmentById(int id) {

        String sql =
                "SELECT * FROM departments WHERE department_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                throw new SQLException("Database connection failed");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        Department department = new Department();

                        department.setDepartmentId(
                                rs.getInt("department_id")
                        );

                        department.setDepartmentName(
                                rs.getString("department_name")
                        );

                        return department;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error finding department: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // UPDATE DEPARTMENT
    // =========================
    public void updateDepartment(Department department) {

        String sql =
                "UPDATE departments SET department_name = ? " +
                "WHERE department_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                throw new SQLException("Database connection failed");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, department.getDepartmentName());

                ps.setInt(2, department.getDepartmentId());

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Department Updated Successfully!");
                } else {
                    System.out.println("Department Not Found!");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error updating department: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // =========================
    // DELETE DEPARTMENT
    // =========================
    public void deleteDepartment(int id) {

        String sql =
                "DELETE FROM departments WHERE department_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                throw new SQLException("Database connection failed");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Department Deleted Successfully!");
                } else {
                    System.out.println("Department Not Found!");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error deleting department: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
