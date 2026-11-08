
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Student Marks</title>

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

    <h2>Add Student Marks</h2>

    <c:if test="${not empty error}">
        <p class="error">
            <c:out value="${error}"/>
        </p>
    </c:if>

    <form action="${pageContext.request.contextPath}/MarksServlet"
          method="post">

        <input type="hidden" name="action" value="add">

        <label>Student ID</label>
        <input type="number"
               name="studentId"
               placeholder="Enter student ID"
               min="1"
               step="1"
               required>

        <label>Subject Name</label>
        <input type="text"
               name="subject"
               placeholder="Enter subject name"
               minlength="2"
               maxlength="100"
               pattern="[A-Za-z0-9 ]{2,100}"
               title="Enter at least 2 letters or numbers"
               required>

        <label>Marks</label>
        <input type="number"
               name="marks"
               placeholder="Enter marks (0 - 100)"
               min="0"
               max="100"
               step="0.01"
               required>

        <button type="submit">Add Marks</button>

    </form>

    <a href="${pageContext.request.contextPath}/MarksServlet"
       class="back">
        Back to Marks List
    </a>

</div>

</body>
</html>
