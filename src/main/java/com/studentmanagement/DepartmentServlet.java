
package com.studentmanagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {

    // Spring-managed DepartmentDAO
    private DepartmentDAO departmentDAO;

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

        departmentDAO = context.getBean(DepartmentDAO.class);

        System.out.println(
                "DepartmentServlet connected with Spring successfully!"
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

            // EDIT DEPARTMENT
            if ("edit".equals(action)) {

                int id = Integer.parseInt(
                        request.getParameter("id")
                );

                if (id <= 0) {
                    response.sendError(400, "Invalid department ID");
                    return;
                }

                Department department =
                        departmentDAO.getDepartmentById(id);

                if (department == null) {
                    response.sendError(404, "Department not found");
                    return;
                }

                request.setAttribute("department", department);

                request.getRequestDispatcher("editDepartment.jsp")
                       .forward(request, response);

                return;
            }

            // DELETE DEPARTMENT
            if ("delete".equals(action)) {

                int id = Integer.parseInt(
                        request.getParameter("id")
                );

                if (id <= 0) {
                    response.sendError(400, "Invalid department ID");
                    return;
                }

                departmentDAO.deleteDepartment(id);

                response.sendRedirect(
                        request.getContextPath() + "/DepartmentServlet"
                );

                return;
            }

        } catch (NumberFormatException e) {

            response.sendError(400, "Invalid department ID");
            return;
        }

        // VIEW ALL DEPARTMENTS
        request.setAttribute(
                "departments",
                departmentDAO.getAllDepartments()
        );

        request.getRequestDispatcher("departments.jsp")
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

        try {

            String departmentName =
                    request.getParameter("departmentName");

            // VALIDATE DEPARTMENT NAME
            if (departmentName == null ||
                !departmentName.trim().matches("[\\p{L} ]{3,100}")) {

                throw new IllegalArgumentException(
                        "Department name must contain 3 to 100 letters."
                );
            }

            departmentName = departmentName.trim();

            // ADD DEPARTMENT
            if ("add".equals(action)) {

                Department department = new Department();

                department.setDepartmentName(departmentName);

                departmentDAO.addDepartment(department);
            }

            // UPDATE DEPARTMENT
            else if ("update".equals(action)) {

                int departmentId = Integer.parseInt(
                        request.getParameter("departmentId")
                );

                if (departmentId <= 0) {
                    throw new IllegalArgumentException(
                            "Invalid department ID."
                    );
                }

                Department department = new Department();

                department.setDepartmentId(departmentId);
                department.setDepartmentName(departmentName);

                departmentDAO.updateDepartment(department);
            }

            else {
                response.sendError(400, "Invalid action");
                return;
            }

            response.sendRedirect(
                    request.getContextPath() + "/DepartmentServlet"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            if ("update".equals(action)) {

                try {
                    int departmentId = Integer.parseInt(
                            request.getParameter("departmentId")
                    );

                    Department department =
                            departmentDAO.getDepartmentById(departmentId);

                    request.setAttribute("department", department);

                    request.getRequestDispatcher("editDepartment.jsp")
                           .forward(request, response);

                } catch (NumberFormatException ex) {

                    response.sendError(400, "Invalid department ID");
                }

            } else {

                request.getRequestDispatcher("addDepartment.jsp")
                       .forward(request, response);
            }
        }
    }
}
