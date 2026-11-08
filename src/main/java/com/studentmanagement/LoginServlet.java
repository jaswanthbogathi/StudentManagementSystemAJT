
package com.studentmanagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

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

        userDAO = context.getBean(UserDAO.class);

        System.out.println(
                "LoginServlet connected with Spring successfully!"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()
                || password == null || password.isEmpty()) {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp?error=1"
            );
            return;
        }

        User user = userDAO.login(username.trim(), password);

        if (user != null) {

            HttpSession oldSession = request.getSession(false);

            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(15 * 60);

            response.sendRedirect(
                    request.getContextPath() + "/dashboard.jsp"
            );

        } else {

            response.sendRedirect(
                    request.getContextPath() + "/login.jsp?error=1"
            );
        }
    }
}
