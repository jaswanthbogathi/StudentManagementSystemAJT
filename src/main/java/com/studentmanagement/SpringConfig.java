
package com.studentmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // USER DAO BEAN
    @Bean
    public UserDAO userDAO() {
        return new UserDAO();
    }

    // STUDENT DAO BEAN
    @Bean
    public StudentDAO studentDAO() {
        return new StudentDAO();
    }

    // DEPARTMENT DAO BEAN
    @Bean
    public DepartmentDAO departmentDAO() {
        return new DepartmentDAO();
    }

    // MARKS DAO BEAN
    @Bean
    public MarksDAO marksDAO() {
        return new MarksDAO();
    }
}
