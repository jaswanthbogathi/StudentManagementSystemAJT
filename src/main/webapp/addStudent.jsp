<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5fa;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 550px;
            margin: 30px auto;
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

        input, select {
            width: 100%;
            padding: 11px;
            margin-top: 7px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input:focus, select:focus {
            outline: none;
            border-color: #2563eb;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 25px;
            background: #1d4ed8;
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
            margin-top: 18px;
            color: #1d4ed8;
            text-decoration: none;
        }

        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Add New Student</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <form action="StudentServlet" method="post">

        <input type="hidden" name="action" value="add">

        <label>Student Name</label>
        <input type="text"
               name="name"
               placeholder="Enter student name"
               minlength="3"
               maxlength="100"
               pattern="[A-Za-z ]{3,100}"
               title="Enter 3 to 100 letters only"
               required>

        <label>Gender</label>
        <select name="gender" required>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>

        <label>Date of Birth</label>
        <input type="date"
               name="dob"
               id="dob"
               required>

        <label>Phone Number</label>
        <input type="tel"
               name="phone"
               placeholder="Enter 10 digit phone number"
               pattern="[0-9]{10}"
               maxlength="10"
               title="Enter exactly 10 digits"
               required>

        <label>Email Address</label>
        <input type="email"
               name="email"
               placeholder="Enter email address"
               maxlength="100"
               required>

        <label>Department ID</label>
        <input type="number"
               name="departmentId"
               placeholder="Enter department ID"
               min="1"
               step="1"
               required>

        <button type="submit">Add Student</button>

    </form>

    <a class="back" href="StudentServlet">Back to Students</a>

</div>

<script>
    document.getElementById("dob").max =
        new Date().toISOString().split("T")[0];
</script>

</body>
</html>
