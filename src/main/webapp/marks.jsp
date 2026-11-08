<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <title>Marks List</title>
</head>

<body>

<h2>Marks Management</h2>

<br>

<a href="addMarks.jsp">Add New Marks</a>

<br><br>

<table border="1" cellpadding="10">

    <tr>
        <th>Marks ID</th>
        <th>Student ID</th>
        <th>Subject</th>
        <th>Marks</th>
        <th>Action</th>
    </tr>

    <c:forEach var="marks" items="${marksList}">

        <tr>

            <td>${marks.marksId}</td>

            <td>${marks.studentId}</td>

            <td>${marks.subject}</td>

            <td>${marks.marks}</td>

            <td>

                <a href="MarksServlet?action=edit&marksId=${marks.marksId}">
                    Edit
                </a>

                &nbsp; | &nbsp;

                <a href="MarksServlet?action=delete&marksId=${marks.marksId}"
                   onclick="return confirm('Are you sure you want to delete these marks?');">
                    Delete
                </a>

            </td>

        </tr>

    </c:forEach>

</table>

</body>

</html>