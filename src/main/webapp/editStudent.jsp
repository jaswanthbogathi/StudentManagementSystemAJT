
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentmanagement.Student" %>

<%
    Student student = (Student) request.getAttribute("student");

    if (student == null) {
        response.sendRedirect("StudentServlet");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>

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

    <h2>Edit Student Details</h2>

    <% if (request.getAttribute("error") != null) { %>
        <p class="error">
            <%= request.getAttribute("error") %>
        </p>
    <% } %>

    <form action="StudentServlet" method="post">

        <input type="hidden" name="action" value="update">

        <input type="hidden"
               name="studentId"
               value="<%= student.getStudentId() %>">

        <label>Student Name</label>
        <input type="text"
               name="name"
               value="<%= student.getName() %>"
               minlength="3"
               maxlength="100"
               pattern="[A-Za-z ]{3,100}"
               title="Enter 3 to 100 letters only"
               required>

        <label>Gender</label>
        <select name="gender" required>
            <option value="">Select Gender</option>

            <option value="Male"
                <%= "Male".equals(student.getGender()) ? "selected" : "" %>>
                Male
            </option>

            <option value="Female"
                <%= "Female".equals(student.getGender()) ? "selected" : "" %>>
                Female
            </option>

            <option value="Other"
                <%= "Other".equals(student.getGender()) ? "selected" : "" %>>
                Other
            </option>
        </select>

        <label>Date of Birth</label>
        <input type="date"
               name="dob"
               id="dob"
               value="<%= student.getDob() %>"
               required>

        <label>Phone Number</label>
        <input type="tel"
               name="phone"
               value="<%= student.getPhone() %>"
               pattern="[0-9]{10}"
               maxlength="10"
               title="Enter exactly 10 digits"
               required>

        <label>Email Address</label>
        <input type="email"
               name="email"
               value="<%= student.getEmail() %>"
               maxlength="100"
               required>

        <label>Department ID</label>
        <input type="number"
               name="departmentId"
               value="<%= student.getDepartmentId() %>"
               min="1"
               step="1"
               required>

        <button type="submit">Update Student</button>

    </form>

    <a class="back" href="StudentServlet">Back to Students</a>

</div>

<script>
    document.getElementById("dob").max =
        new Date().toISOString().split("T")[0];
</script>

</body>
</html>
