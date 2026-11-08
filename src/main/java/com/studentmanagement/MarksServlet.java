
package com.studentmanagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;

@WebServlet("/MarksServlet")
public class MarksServlet extends HttpServlet {

    // Spring-managed MarksDAO
    private MarksDAO marksDAO;

    // Initialize Spring DAO
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

        marksDAO = context.getBean(MarksDAO.class);

        System.out.println(
                "MarksServlet connected with Spring successfully!"
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

            // EDIT MARKS
            if ("edit".equals(action)) {

                int marksId = Integer.parseInt(
                        request.getParameter("marksId")
                );

                if (marksId <= 0) {
                    response.sendError(400, "Invalid marks ID");
                    return;
                }

                Marks marks = marksDAO.getMarksById(marksId);

                if (marks == null) {
                    response.sendError(404, "Marks record not found");
                    return;
                }

                request.setAttribute("marks", marks);

                request.getRequestDispatcher("editMarks.jsp")
                       .forward(request, response);

                return;
            }

            // DELETE MARKS
            if ("delete".equals(action)) {

                int marksId = Integer.parseInt(
                        request.getParameter("marksId")
                );

                if (marksId <= 0) {
                    response.sendError(400, "Invalid marks ID");
                    return;
                }

                marksDAO.deleteMarks(marksId);

                response.sendRedirect(
                        request.getContextPath() + "/MarksServlet"
                );

                return;
            }

        } catch (NumberFormatException e) {

            response.sendError(400, "Invalid marks ID");
            return;
        }

        // VIEW ALL MARKS
        List<Marks> marksList = marksDAO.getAllMarks();

        request.setAttribute("marksList", marksList);

        request.getRequestDispatcher("marks.jsp")
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

            int marksId = 0;

            if (isUpdate) {

                marksId = Integer.parseInt(
                        request.getParameter("marksId")
                );

                if (marksId <= 0) {
                    throw new IllegalArgumentException(
                            "Invalid marks ID."
                    );
                }
            }

            // STUDENT ID VALIDATION
            int studentId;

            try {
                studentId = Integer.parseInt(
                        request.getParameter("studentId")
                );
            } catch (NumberFormatException | NullPointerException e) {

                throw new IllegalArgumentException(
                        "Please enter a valid student ID."
                );
            }

            if (studentId <= 0) {
                throw new IllegalArgumentException(
                        "Student ID must be greater than zero."
                );
            }

            // SUBJECT VALIDATION
            String subject = request.getParameter("subject");

            if (subject == null ||
                !subject.trim().matches("[\\p{L}0-9 ]{2,100}")) {

                throw new IllegalArgumentException(
                        "Subject must contain 2 to 100 letters or numbers."
                );
            }

            // MARKS VALIDATION
            double marksValue;

            try {
                marksValue = Double.parseDouble(
                        request.getParameter("marks")
                );
            } catch (NumberFormatException | NullPointerException e) {

                throw new IllegalArgumentException(
                        "Please enter valid marks."
                );
            }

            if (!Double.isFinite(marksValue) ||
                marksValue < 0 || marksValue > 100) {

                throw new IllegalArgumentException(
                        "Marks must be between 0 and 100."
                );
            }

            // CREATE MARKS OBJECT
            Marks marks = new Marks();

            if (isUpdate) {
                marks.setMarksId(marksId);
            }

            marks.setStudentId(studentId);
            marks.setSubject(subject.trim());
            marks.setMarks(marksValue);

            // UPDATE MARKS
            if (isUpdate) {

                marksDAO.updateMarks(marks);

            }

            // ADD MARKS
            else if ("add".equals(action)) {

                marksDAO.addMarks(marks);

            }

            else {
                response.sendError(400, "Invalid action");
                return;
            }

            response.sendRedirect(
                    request.getContextPath() + "/MarksServlet"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            if (isUpdate) {

                Marks marks = new Marks();

                marks.setMarksId(
                        parseIntOrZero(request.getParameter("marksId"))
                );

                marks.setStudentId(
                        parseIntOrZero(request.getParameter("studentId"))
                );

                marks.setSubject(request.getParameter("subject"));

                marks.setMarks(
                        parseDoubleOrZero(request.getParameter("marks"))
                );

                request.setAttribute("marks", marks);

                request.getRequestDispatcher("editMarks.jsp")
                       .forward(request, response);

            } else {

                request.getRequestDispatcher("addMarks.jsp")
                       .forward(request, response);
            }
        }
    }

    // Helper method
    private int parseIntOrZero(String value) {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    // Helper method
    private double parseDoubleOrZero(String value) {

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }
}
