
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentmanagement.Department" %>

<%
    Department department =
        (Department) request.getAttribute("department");

    if (department == null) {
        response.sendRedirect("DepartmentServlet");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Department</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5fa;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 500px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #172554;
        }

        label {
            display: block;
            margin-top: 20px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 25px;
            background: #2563eb;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #1e40af;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #1d4ed8;
            text-decoration: none;
        }

        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Edit Department</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <form action="DepartmentServlet" method="post">

        <input type="hidden" name="action" value="update">

        <input type="hidden"
               name="departmentId"
               value="<%= department.getDepartmentId() %>">

        <label>Department Name</label>

        <input type="text"
               name="departmentName"
               value="<%= department.getDepartmentName() %>"
               minlength="3"
               maxlength="100"
               pattern="[A-Za-z ]{3,100}"
               required>

        <button type="submit">Update Department</button>

    </form>

    <a href="DepartmentServlet" class="back">
        Back to Department Management
    </a>

</div>

</body>
</html>
