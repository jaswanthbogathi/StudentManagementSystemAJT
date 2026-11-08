
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // =========================
    // ADD STUDENT
    // =========================
    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students "
                + "(name, gender, dob, phone, email, department_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, student.getName());
                ps.setString(2, student.getGender());
                ps.setDate(3, student.getDob());
                ps.setString(4, student.getPhone());
                ps.setString(5, student.getEmail());
                ps.setInt(6, student.getDepartmentId());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error while adding student!");
            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // GET ALL STUDENTS
    // =========================
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students ORDER BY student_id";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return students;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Student student = new Student();

                    student.setStudentId(rs.getInt("student_id"));
                    student.setName(rs.getString("name"));
                    student.setGender(rs.getString("gender"));
                    student.setDob(rs.getDate("dob"));
                    student.setPhone(rs.getString("phone"));
                    student.setEmail(rs.getString("email"));
                    student.setDepartmentId(rs.getInt("department_id"));

                    students.add(student);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while retrieving students!");
            e.printStackTrace();
        }

        return students;
    }


    // =========================
    // GET STUDENT BY ID
    // =========================
    public Student getStudentById(int studentId) {

        if (studentId <= 0) {
            System.out.println("Invalid student ID!");
            return null;
        }

        String sql =
                "SELECT * FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, studentId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        Student student = new Student();

                        student.setStudentId(rs.getInt("student_id"));
                        student.setName(rs.getString("name"));
                        student.setGender(rs.getString("gender"));
                        student.setDob(rs.getDate("dob"));
                        student.setPhone(rs.getString("phone"));
                        student.setEmail(rs.getString("email"));
                        student.setDepartmentId(rs.getInt("department_id"));

                        return student;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while finding student!");
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // UPDATE STUDENT
    // =========================
    public boolean updateStudent(Student student) {

        String sql =
                "UPDATE students SET "
                + "name = ?, gender = ?, dob = ?, "
                + "phone = ?, email = ?, department_id = ? "
                + "WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, student.getName());
                ps.setString(2, student.getGender());
                ps.setDate(3, student.getDob());
                ps.setString(4, student.getPhone());
                ps.setString(5, student.getEmail());
                ps.setInt(6, student.getDepartmentId());
                ps.setInt(7, student.getStudentId());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error while updating student!");
            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // DELETE STUDENT
    // =========================
    public boolean deleteStudent(int studentId) {

        if (studentId <= 0) {
            System.out.println("Invalid student ID!");
            return false;
        }

        String sql =
                "DELETE FROM students WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, studentId);

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting student!");
            e.printStackTrace();
            return false;
        }
    }
}
