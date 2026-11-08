
package com.studentmanagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.context.ApplicationContext;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    // Spring-managed StudentDAO
    private StudentDAO studentDAO;

    // Get StudentDAO from Spring Context
    @Override
    public void init() throws ServletException {

        ApplicationContext context =
                (ApplicationContext) getServletContext()
                        .getAttribute("springContext");

        if (context == null) {
            throw new ServletException(
                    "Spring Context is not initialized."
            );
        }

        studentDAO = context.getBean(StudentDAO.class);

        System.out.println(
                "StudentServlet connected with Spring successfully!"
        );
    }

    // =========================
    // GET REQUEST
    // =========================
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            // EDIT STUDENT
            if ("edit".equals(action)) {

                int studentId = Integer.parseInt(
                        request.getParameter("studentId")
                );

                if (studentId <= 0) {
                    response.sendError(400, "Invalid student ID");
                    return;
                }

                Student student =
                        studentDAO.getStudentById(studentId);

                if (student == null) {
                    response.sendError(404, "Student not found");
                    return;
                }

                request.setAttribute("student", student);

                request.getRequestDispatcher("editStudent.jsp")
                       .forward(request, response);

                return;
            }

            // DELETE STUDENT
            if ("delete".equals(action)) {

                int studentId = Integer.parseInt(
                        request.getParameter("studentId")
                );

                if (studentId <= 0) {
                    response.sendError(400, "Invalid student ID");
                    return;
                }

                studentDAO.deleteStudent(studentId);

                response.sendRedirect(
                        request.getContextPath() + "/StudentServlet"
                );

                return;
            }

        } catch (NumberFormatException e) {

            response.sendError(400, "Invalid student ID");
            return;
        }

        // VIEW ALL STUDENTS
        List<Student> students =
                studentDAO.getAllStudents();

        request.setAttribute("students", students);

        request.getRequestDispatcher("students.jsp")
               .forward(request, response);
    }

    // =========================
    // POST REQUEST
    // =========================
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        boolean isUpdate = "update".equals(action);

        try {

            int studentId = 0;

            if (isUpdate) {

                studentId = Integer.parseInt(
                        request.getParameter("studentId")
                );

                if (studentId <= 0) {
                    throw new IllegalArgumentException(
                            "Invalid student ID."
                    );
                }
            }

            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String dobValue = request.getParameter("dob");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String departmentValue =
                    request.getParameter("departmentId");

            // NAME VALIDATION
            if (name == null ||
                !name.trim().matches("[\\p{L} ]{3,100}")) {

                throw new IllegalArgumentException(
                        "Name must contain 3 to 100 letters."
                );
            }

            // GENDER VALIDATION
            if (gender == null || gender.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Please select a gender."
                );
            }

            // DATE OF BIRTH VALIDATION
            LocalDate dobDate;

            try {
                dobDate = LocalDate.parse(dobValue);
            } catch (DateTimeParseException | NullPointerException e) {

                throw new IllegalArgumentException(
                        "Please enter a valid date of birth."
                );
            }

            if (dobDate.isAfter(LocalDate.now())) {

                throw new IllegalArgumentException(
                        "Date of birth cannot be in the future."
                );
            }

            Date dob = Date.valueOf(dobDate);

            // PHONE VALIDATION
            if (phone == null ||
                !phone.matches("[0-9]{10}")) {

                throw new IllegalArgumentException(
                        "Phone number must contain exactly 10 digits."
                );
            }

            // EMAIL VALIDATION
            if (email == null ||
                email.length() > 100 ||
                !email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
                )) {

                throw new IllegalArgumentException(
                        "Please enter a valid email address."
                );
            }

            // DEPARTMENT ID VALIDATION
            int departmentId;

            try {
                departmentId = Integer.parseInt(departmentValue);
            } catch (NumberFormatException | NullPointerException e) {

                throw new IllegalArgumentException(
                        "Please enter a valid department ID."
                );
            }

            if (departmentId <= 0) {

                throw new IllegalArgumentException(
                        "Department ID must be greater than zero."
                );
            }

            // CREATE STUDENT OBJECT
            Student student = new Student();

            if (isUpdate) {
                student.setStudentId(studentId);
            }

            student.setName(name.trim());
            student.setGender(gender);
            student.setDob(dob);
            student.setPhone(phone);
            student.setEmail(email.trim());
            student.setDepartmentId(departmentId);

            // ADD OR UPDATE USING SPRING DAO
            if (isUpdate) {

                studentDAO.updateStudent(student);

            } else {

                studentDAO.addStudent(student);
            }

            response.sendRedirect(
                    request.getContextPath() + "/StudentServlet"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            if (isUpdate) {

                request.getRequestDispatcher("editStudent.jsp")
                       .forward(request, response);

            } else {

                request.getRequestDispatcher("addStudent.jsp")
                       .forward(request, response);
            }
        }
    }
}
