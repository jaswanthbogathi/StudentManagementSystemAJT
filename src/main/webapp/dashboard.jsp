
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.studentmanagement.User"%>

<%
    User user = (User) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>

    <title>Student Management Dashboard</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f5f9;
        }

        header {
            background: #172554;
            color: white;
            text-align: center;
            padding: 30px 15px;
        }

        header h1 {
            margin: 0 0 15px;
        }

        .logout {
            display: inline-block;
            background: #dc2626;
            color: white;
            padding: 10px 25px;
            text-decoration: none;
            border-radius: 6px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            padding: 35px 20px;
        }

        .container h2 {
            text-align: center;
            margin-bottom: 35px;
        }

        .menu {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 25px;
        }

        .card {
            background: white;
            padding: 35px 20px;
            text-align: center;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.10);
        }

        .card h3 {
            color: #172554;
        }

        .card p {
            color: #555;
            line-height: 1.6;
        }

        .card a {
            display: inline-block;
            background: #1d4ed8;
            color: white;
            padding: 12px 20px;
            border-radius: 6px;
            text-decoration: none;
            margin-top: 10px;
        }

        .card a:hover {
            background: #1e40af;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #666;
        }

        @media (max-width: 750px) {
            .menu {
                grid-template-columns: 1fr;
            }
        }

    </style>

</head>

<body>

<header>

    <h1>Student Management System</h1>

    <p>Welcome, <%= user.getUsername() %></p>

    <a class="logout" href="LogoutServlet">Logout</a>

</header>

<div class="container">

    <h2>Admin Dashboard</h2>

    <div class="menu">

        <div class="card">
            <h3>Student Management</h3>
            <p>Add, view, update and delete student records.</p>
            <a href="StudentServlet">Manage Students</a>
        </div>

        <div class="card">
            <h3>Department Management</h3>
            <p>Add, view, update and delete departments.</p>
            <a href="DepartmentServlet">Manage Departments</a>
        </div>

        <div class="card">
            <h3>Marks Management</h3>
            <p>Manage and update student marks.</p>
            <a href="MarksServlet">Manage Marks</a>
        </div>

    </div>

</div>

<footer>
    Student Management System | AJT Project
</footer>

</body>
</html>
