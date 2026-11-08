
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Department Management</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5fa;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 1000px;
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

        .error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
            font-weight: bold;
        }

        .top-buttons {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
            flex-wrap: wrap;
            gap: 10px;
        }

        .btn {
            display: inline-block;
            padding: 10px 16px;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 14px;
            border: none;
            cursor: pointer;
        }

        .add-btn {
            background: #15803d;
        }

        .dashboard-btn {
            background: #475569;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            padding: 14px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background: #1e3a8a;
            color: white;
        }

        tr:hover {
            background: #f1f5f9;
        }

        .edit-btn {
            background: #2563eb;
            margin-right: 5px;
        }

        .delete-btn {
            background: #dc2626;
        }

        .empty {
            text-align: center;
            color: #666;
            padding: 20px;
        }

        @media (max-width: 600px) {
            .container {
                padding: 15px;
                overflow-x: auto;
            }

            table {
                min-width: 500px;
            }
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Department Management</h2>

    <!-- VALIDATION ERROR -->
    <c:if test="${not empty error}">
        <p class="error">
            <c:out value="${error}"/>
        </p>
    </c:if>

    <!-- TOP BUTTONS -->
    <div class="top-buttons">

        <a href="addDepartment.jsp" class="btn add-btn">
            + Add Department
        </a>

        <a href="dashboard.jsp" class="btn dashboard-btn">
            Back to Dashboard
        </a>

    </div>

    <!-- DEPARTMENT TABLE -->
    <table>

        <thead>
            <tr>
                <th>Department ID</th>
                <th>Department Name</th>
                <th>Actions</th>
            </tr>
        </thead>

        <tbody>

            <c:choose>

                <c:when test="${empty departments}">
                    <tr>
                        <td colspan="3" class="empty">
                            No departments found.
                        </td>
                    </tr>
                </c:when>

                <c:otherwise>

                    <c:forEach var="department" items="${departments}">

                        <tr>

                            <td>
                                <c:out value="${department.departmentId}"/>
                            </td>

                            <td>
                                <c:out value="${department.departmentName}"/>
                            </td>

                            <td>

                                <!-- EDIT BUTTON -->
                                <a class="btn edit-btn"
                                   href="${pageContext.request.contextPath}/DepartmentServlet?action=edit&id=${department.departmentId}">
                                    Edit
                                </a>

                                <!-- DELETE BUTTON -->
                                <a class="btn delete-btn"
                                   href="${pageContext.request.contextPath}/DepartmentServlet?action=delete&id=${department.departmentId}"
                                   onclick="return confirm('Are you sure you want to delete this department?');">
                                    Delete
                                </a>

                            </td>

                        </tr>

                    </c:forEach>

                </c:otherwise>

            </c:choose>

        </tbody>

    </table>

</div>

</body>
</html>
