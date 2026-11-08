
package com.studentmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentSwingApp extends JFrame {

    private JTextField nameField, genderField, phoneField, emailField;
    private JTable table;
    private DefaultTableModel model;
    private int selectedStudentId = -1;

    private final String URL =
            "jdbc:mysql://localhost:3307/student_management";
    private final String USER = "root";
    private final String PASSWORD = "";

    public StudentSwingApp() {

        setTitle("Student Management System - Swing");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // FORM PANEL
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(20);
        genderField = new JTextField(20);
        phoneField = new JTextField(20);
        emailField = new JTextField(20);

        addField(formPanel, gbc, 0, "Student Name:", nameField);
        addField(formPanel, gbc, 1, "Gender:", genderField);
        addField(formPanel, gbc, 2, "Phone:", phoneField);
        addField(formPanel, gbc, 3, "Email:", emailField);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton refreshButton = new JButton("Refresh");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(clearButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // TABLE
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Gender", "Phone", "Email"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // BUTTON ACTIONS
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        refreshButton.addActionListener(e -> loadStudents());
        clearButton.addActionListener(e -> clearForm());

        // SELECT TABLE ROW
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });

        loadStudents();
    }

    private void addField(JPanel panel, GridBagConstraints gbc,
                          int row, String label, JTextField field) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ADD STUDENT
    private void addStudent() {

        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter student name!");
            return;
        }

        String sql = "INSERT INTO students "
                + "(name, gender, phone, email) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nameField.getText().trim());
            ps.setString(2, genderField.getText().trim());
            ps.setString(3, phoneField.getText().trim());
            ps.setString(4, emailField.getText().trim());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Student Added Successfully!");

            clearForm();
            loadStudents();

        } catch (SQLException ex) {
            showError(ex);
        }
    }

    // VIEW STUDENTS
    private void loadStudents() {

        model.setRowCount(0);

        String sql = "SELECT student_id, name, gender, phone, email "
                + "FROM students ORDER BY student_id";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("phone"),
                    rs.getString("email")
                });
            }

        } catch (SQLException ex) {
            showError(ex);
        }
    }

    // SELECT TABLE ROW
    private void fillFormFromTable() {

        int row = table.getSelectedRow();

        if (row >= 0) {
            selectedStudentId = Integer.parseInt(
                    model.getValueAt(row, 0).toString());

            nameField.setText(model.getValueAt(row, 1).toString());
            genderField.setText(model.getValueAt(row, 2) == null
                    ? "" : model.getValueAt(row, 2).toString());
            phoneField.setText(model.getValueAt(row, 3) == null
                    ? "" : model.getValueAt(row, 3).toString());
            emailField.setText(model.getValueAt(row, 4) == null
                    ? "" : model.getValueAt(row, 4).toString());
        }
    }

    // UPDATE STUDENT
    private void updateStudent() {

        if (selectedStudentId == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student from the table!");
            return;
        }

        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter student name!");
            return;
        }

        String sql = "UPDATE students SET name=?, gender=?, phone=?, "
                + "email=? WHERE student_id=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nameField.getText().trim());
            ps.setString(2, genderField.getText().trim());
            ps.setString(3, phoneField.getText().trim());
            ps.setString(4, emailField.getText().trim());
            ps.setInt(5, selectedStudentId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Student Updated Successfully!");

            clearForm();
            loadStudents();

        } catch (SQLException ex) {
            showError(ex);
        }
    }

    // DELETE STUDENT
    private void deleteStudent() {

        if (selectedStudentId == -1) {
            JOptionPane.showMessageDialog(this,
                    "Please select a student from the table!");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, selectedStudentId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Student Deleted Successfully!");

            clearForm();
            loadStudents();

        } catch (SQLException ex) {
            showError(ex);
        }
    }

    // CLEAR FORM
    private void clearForm() {

        nameField.setText("");
        genderField.setText("");
        phoneField.setText("");
        emailField.setText("");

        selectedStudentId = -1;
        table.clearSelection();
    }

    private void showError(SQLException ex) {
        JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StudentSwingApp().setVisible(true);
        });
    }
}
