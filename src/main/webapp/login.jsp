<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Student Management System - Login</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-box {
            width: 350px;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #555;
        }

        .error {
            color: red;
            text-align: center;
            margin-top: 10px;
        }

    </style>

</head>

<body>

    <div class="login-box">

        <h2>Student Management System</h2>

        <form action="LoginServlet" method="post">

            <label>Username:</label>

            <input type="text"
                   name="username"
                   required>

            <label>Password:</label>

            <input type="password"
                   name="password"
                   required>

            <input type="submit"
                   value="Login">

        </form>

        <% if (request.getParameter("error") != null) { %>

            <div class="error">
                Invalid username or password!
            </div>

        <% } %>

    </div>

</body>
</html>