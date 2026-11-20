# 🎓 Student Management System AJT

<p align="center">

**A Java-Based Web Application for Managing Student Academic Information**

</p>

---

## 📖 About the Project

The **Student Management System AJT** is a web-based application developed to manage student-related academic information efficiently. The application provides a centralized platform for maintaining student records, department details, academic marks, and user authentication.

The project is developed as part of the **Advanced Java Technology (AJT)** subject. It demonstrates the practical implementation of Java web development concepts, database connectivity, server-side processing, and web application architecture.

The system aims to reduce manual record management, improve data organization, simplify access to student information, and provide an efficient method for managing academic records.

---



---

## ✨ Features

### 🔐 1. User Authentication

The application provides a login system that allows authorized users to access the application.

Features include:

- User login form.
- Username and password verification.
- Database-based authentication.
- Session creation after successful login.
- Access to application pages after authentication.
- Logout functionality.
- Session termination during logout.

### 👨‍🎓 2. Student Management

The Student Management module manages student information.

It allows the application to maintain student records such as:

- Student ID
- Student name
- Department
- Academic details
- Other student-related information

Student records can be managed through database operations.

### 🏢 3. Department Management

The Department Management module maintains information related to academic departments.

Department information can be stored and organized in the database to maintain relationships between students and their respective departments.

### 📊 4. Marks Management

The Marks Management module manages students' academic performance records.

It helps store and organize marks associated with students and supports academic record management.

### 🗄️ 5. Database Integration

The application uses MySQL as its database management system.

JDBC is used to establish communication between Java application components and the database.

Database operations include:

- Insert records
- Retrieve records
- Update records
- Delete records
- Execute SQL queries

### 🌐 6. Web-Based Interface

The application provides a browser-based interface through which users can interact with the system.

The frontend uses web technologies, while Java-based server-side components handle application logic and database communication.

---

## 🛠️ Technologies Used

| Technology | Description |
|---|---|
| Java | Backend programming language |
| JSP (JavaServer Pages) | Dynamic web page generation |
| Servlets | Handling HTTP requests and responses |
| JDBC | Database connectivity |
| MySQL | Relational database management |
| HTML | Web page structure |
| CSS | Styling and presentation |
| Apache Tomcat | Web application server |
| Apache Maven | Dependency and project management |
| NetBeans IDE | Development environment |
| Spring | Java application framework |
| Hibernate | Object-relational mapping technology |

---

## 🏗️ System Architecture

The application follows a layered web application architecture.

### 1. Presentation Layer

The presentation layer is responsible for displaying the application's user interface.

Technologies used:

- JSP
- HTML
- CSS

Users interact with forms and web pages through their browser.

### 2. Controller Layer

The controller layer handles incoming HTTP requests.

Java Servlets are responsible for:

- Receiving user requests.
- Processing form submissions.
- Validating input.
- Calling appropriate application components.
- Managing sessions.
- Returning responses.

### 3. Business Logic Layer

This layer contains the application logic required to process requests and perform operations related to student, department, marks, and authentication management.

### 4. Data Access Layer

The data access layer communicates with the database.

JDBC is used to:

- Establish database connections.
- Execute SQL statements.
- Retrieve database records.
- Insert, update, and delete data.

### 5. Database Layer

MySQL stores the application's data, including student records, department details, marks, and authentication-related information.

---

## 🔄 Application Workflow

The general application workflow is:

1. The user opens the application in a web browser.
2. The login page is displayed.
3. The user enters login credentials.
4. The login request is sent to the appropriate Servlet.
5. The Servlet processes the request.
6. The application checks the credentials against the database.
7. If authentication is successful, a session is created.
8. The user is redirected to the appropriate application page.
9. The user performs management operations.
10. The application communicates with MySQL through JDBC.
11. The database returns the requested result.
12. The application displays the result to the user.
13. The user can log out to terminate the session.

---

## 🗄️ Database Design

The application uses a MySQL database for storing and managing information.

### Departments Table

The `departments` table stores department-related information.

| Field | Description |
|---|---|
| Department ID | Unique identifier for a department |
| Department Name | Name of the department |

### Students Table

The `students` table stores student-related information.

| Field | Description |
|---|---|
| Student ID | Unique identifier for a student |
| Student Name | Name of the student |
| Department ID | Associated department |
| Other Fields | Additional student details |

### Marks Table

The `marks` table stores academic performance information.

| Field | Description |
|---|---|
| Marks ID | Unique identifier for marks record |
| Student ID | Student associated with the marks |
| Subject / Academic Information | Related academic details |
| Marks | Student marks |

> Note: The exact columns and constraints depend on the database schema implemented in the project.

---

## 📂 Project Structure

The project is organized as a Maven-based Java web application.

```text
StudentManagementSystemAJT/
│
├── pom.xml
│
├── nb-configuration.xml
│
├── README.md
│
└── src/
    └── main/
        ├── java/
        │   └── Application Packages/
        │       ├── Servlet Classes
        │       ├── DAO Classes
        │       ├── Database Connection Classes
        │       └── Model Classes
        │
        ├── resources/
        │
        └── webapp/
            ├── JSP Pages
            ├── HTML Pages
            ├── CSS Files
            ├── Images
            └── WEB-INF/
                └── web.xml
```

### Main Components

**Servlet Classes**

Handle HTTP requests and application operations.

**DAO Classes**

Handle database operations using JDBC.

**Model Classes**

Represent application data using Java objects.

**Database Connection Class**

Establishes a connection between Java and MySQL.

**JSP Pages**

Display dynamic application pages.

---

## ⚙️ Requirements

Before installing and running the project, make sure the following software is installed:

- Java Development Kit (JDK)
- NetBeans IDE
- Apache Tomcat Server
- MySQL Server
- XAMPP (optional, for managing MySQL and phpMyAdmin)
- Git
- Maven

---

## 🚀 Installation and Setup

### Step 1: Clone the Repository

Open the terminal or command prompt and execute:

```bash
git clone https://github.com/jaswanthbogathi/StudentManagementSystemAJT.git
```

Navigate to the project directory:

```bash
cd StudentManagementSystemAJT
```

### Step 2: Open the Project in NetBeans

1. Open NetBeans IDE.
2. Select **File → Open Project**.
3. Navigate to the cloned repository folder.
4. Select `StudentManagementSystemAJT`.
5. Click **Open Project**.

### Step 3: Configure MySQL

1. Start MySQL using XAMPP or your installed MySQL server.
2. Open phpMyAdmin.
3. Create the database required by the application.
4. Create the required tables.
5. Insert initial data if necessary.

### Step 4: Configure Database Connectivity

Locate the database connection class in the project.

Configure the following database properties according to your MySQL installation:

- Database URL
- Database name
- MySQL username
- MySQL password
- MySQL port

Example configuration:

```java
String url = "jdbc:mysql://localhost:3306/student_management";
String username = "root";
String password = "";
```

> Update the port, database name, username, and password according to your local MySQL configuration.

### Step 5: Configure Apache Tomcat

1. Open NetBeans.
2. Go to the Services tab.
3. Add Apache Tomcat if it is not already configured.
4. Configure the Tomcat installation directory.
5. Select Tomcat as the project's server.

### Step 6: Build the Project

In NetBeans:

1. Right-click the project.
2. Select **Clean and Build**.
3. Wait until Maven downloads dependencies and the build completes successfully.

Alternatively, use the terminal:

```bash
mvn clean install
```

### Step 7: Run the Application

1. Right-click the project.
2. Select **Run**.
3. Wait for the server to start.
4. Open the application in your browser.

The application will generally be available at a URL similar to:

```text
http://localhost:8080/StudentManagementSystemAJT/
```

The exact URL depends on your Tomcat configuration and application context path.

---

## 🔑 Login System

The login system verifies the user's credentials against the database.

### Login Process

1. The user enters a username and password.
2. The login form submits the data to a Servlet.
3. The Servlet receives the request.
4. The database is queried to verify the credentials.
5. If the credentials are valid, the user is authenticated.
6. A session is created.
7. The user is redirected to the dashboard.
8. If the credentials are invalid, an error message is displayed.

### Session Management

The project demonstrates session management using Java web technologies.

Sessions help maintain the authenticated user's state across multiple requests.

The logout process invalidates the session and prevents further access to protected pages without authentication.

---

## 🔌 JDBC Connectivity

Java Database Connectivity (JDBC) is used to connect the Java application with MySQL.

The general JDBC process includes:

1. Loading or registering the database driver.
2. Establishing a database connection.
3. Creating a SQL statement or prepared statement.
4. Executing SQL queries.
5. Processing the result set.
6. Closing database resources.

Example JDBC workflow:

```java
Connection con = DriverManager.getConnection(
    databaseURL,
    username,
    password
);
```

JDBC enables the application to perform database operations efficiently.

---

## 📚 Advanced Java Concepts Implemented

This project demonstrates practical implementation of the following Advanced Java concepts:

- Java Web Applications
- Servlets
- JSP
- JDBC
- Database Connectivity
- HTTP Request and Response Handling
- Session Management
- Authentication
- CRUD Operations
- Maven Dependency Management
- Apache Tomcat Deployment
- MVC-style application organization
- Relational Database Management

---

## 🧪 Testing

The application can be tested using the following scenarios:

| Test Case | Expected Result |
|---|---|
| Valid Login | User successfully logs in |
| Invalid Login | Error message is displayed |
| Logout | Session is terminated |
| Database Connection | Application connects to MySQL |
| Add Student | Student record is inserted |
| Retrieve Student | Student information is displayed |
| Update Student | Existing information is modified |
| Delete Student | Selected record is removed |
| Department Management | Department records are managed |
| Marks Management | Academic records are managed |

---

## 📸 Screenshots

Screenshots can be added to this section to demonstrate the application interface.

### Login Page

```md
![Login Page](screenshots/login.png)
```

### Dashboard

```md
![Dashboard](screenshots/dashboard.png)
```

### Student Management

```md
![Student Management](screenshots/student-management.png)
```

### Department Management

```md
![Department Management](screenshots/department-management.png)
```

### Marks Management

```md
![Marks Management](screenshots/marks-management.png)
```

> Create a `screenshots` folder in the repository and place your project screenshots inside it.

---

## 🔮 Future Enhancements

The following features can be added in future versions:

- Admin and student role-based access control.
- Student profile management.
- Attendance management.
- Fee management.
- Course management.
- Advanced search and filtering.
- Report generation.
- Export student data to PDF or Excel.
- Improved UI design and responsiveness.
- Password encryption.
- Validation and error handling improvements.
- Dashboard analytics.
- Online deployment.

---

## 🎓 Academic Information

**Project Name:** Student Management System AJT

**Subject:** Advanced Java Technology

**Course:** B.Tech – Computer Engineering

**Project Type:** Java Web Application

**Purpose:** Academic project demonstrating Java web technologies and database integration.

---

## 👨‍💻 Author

**Jaswanth Reddy Bogathi**

**Bhanuprasad Reddy Bandlapalli**

**Yarraguntla Gnaneswar Reddy**

B.Tech – Computer Engineering

---

## 🔗 GitHub Repository

[Student Management System AJT](https://github.com/jaswanthbogathi/StudentManagementSystemAJT)





