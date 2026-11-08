
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarksDAO {

    // =========================
    // ADD MARKS
    // =========================
    public boolean addMarks(Marks marks) {

        String sql = "INSERT INTO marks "
                + "(student_id, subject, marks) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, marks.getStudentId());
                ps.setString(2, marks.getSubject());
                ps.setDouble(3, marks.getMarks());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error while adding marks!");
            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // GET ALL MARKS
    // =========================
    public List<Marks> getAllMarks() {

        List<Marks> marksList = new ArrayList<>();

        String sql = "SELECT * FROM marks ORDER BY marks_id";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return marksList;
            }

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Marks marks = new Marks();

                    marks.setMarksId(rs.getInt("marks_id"));
                    marks.setStudentId(rs.getInt("student_id"));
                    marks.setSubject(rs.getString("subject"));
                    marks.setMarks(rs.getDouble("marks"));

                    marksList.add(marks);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while retrieving marks!");
            e.printStackTrace();
        }

        return marksList;
    }


    // =========================
    // GET MARKS BY ID
    // =========================
    public Marks getMarksById(int marksId) {

        if (marksId <= 0) {
            System.out.println("Invalid marks ID!");
            return null;
        }

        String sql =
                "SELECT * FROM marks WHERE marks_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return null;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, marksId);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        Marks marks = new Marks();

                        marks.setMarksId(rs.getInt("marks_id"));
                        marks.setStudentId(rs.getInt("student_id"));
                        marks.setSubject(rs.getString("subject"));
                        marks.setMarks(rs.getDouble("marks"));

                        return marks;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while finding marks!");
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // UPDATE MARKS
    // =========================
    public boolean updateMarks(Marks marks) {

        String sql =
                "UPDATE marks SET "
                + "student_id = ?, subject = ?, marks = ? "
                + "WHERE marks_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, marks.getStudentId());
                ps.setString(2, marks.getSubject());
                ps.setDouble(3, marks.getMarks());
                ps.setInt(4, marks.getMarksId());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error while updating marks!");
            e.printStackTrace();
            return false;
        }
    }


    // =========================
    // DELETE MARKS
    // =========================
    public boolean deleteMarks(int marksId) {

        if (marksId <= 0) {
            System.out.println("Invalid marks ID!");
            return false;
        }

        String sql =
                "DELETE FROM marks WHERE marks_id = ?";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                System.out.println("Database connection unavailable!");
                return false;
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, marksId);

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting marks!");
            e.printStackTrace();
            return false;
        }
    }
}
