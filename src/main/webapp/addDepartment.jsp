
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Department</title>

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
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
            color: #333;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 15px;
        }

        input:focus {
            outline: none;
            border-color: #2563eb;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 25px;
            background: #15803d;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #166534;
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
            margin-bottom: 15px;
            font-weight: bold;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Add New Department</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <form action="DepartmentServlet" method="post">

        <input type="hidden" name="action" value="add">

        <label>Department Name</label>

        <input type="text"
               name="departmentName"
               placeholder="Enter department name"
               minlength="3"
               maxlength="100"
               pattern="[A-Za-z ]{3,100}"
               title="Enter 3 to 100 letters only"
               required>

        <button type="submit">Add Department</button>

    </form>

    <a href="DepartmentServlet" class="back">
        Back to Department Management
    </a>

</div>

</body>
</html>
