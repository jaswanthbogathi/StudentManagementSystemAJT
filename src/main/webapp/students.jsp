<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Student List</title>
</head>

<body>

<h2>Student Management</h2>

<br>

<a href="addStudent.jsp">Add New Student</a>

<br><br>

<table border="1" cellpadding="10">

    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Gender</th>
        <th>DOB</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Department ID</th>
        <th>Action</th>
    </tr>

    <c:forEach var="student" items="${students}">

        <tr>

            <td>${student.studentId}</td>

            <td>${student.name}</td>

            <td>${student.gender}</td>

            <td>${student.dob}</td>

            <td>${student.phone}</td>

            <td>${student.email}</td>

            <td>${student.departmentId}</td>

            <td>

                <a href="StudentServlet?action=edit&studentId=${student.studentId}">
                    Edit
                </a>

                &nbsp; | &nbsp;

                <a href="StudentServlet?action=delete&studentId=${student.studentId}"
                   onclick="return confirm('Are you sure you want to delete this student?');">
                    Delete
                </a>

            </td>

        </tr>

    </c:forEach>

</table>

</body>

</html>