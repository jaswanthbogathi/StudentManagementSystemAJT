package com.studentmanagement;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        StudentDAO studentDAO = context.getBean(StudentDAO.class);

        DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);

        MarksDAO marksDAO = context.getBean(MarksDAO.class);

        System.out.println("Spring Framework Started Successfully!");

        System.out.println("StudentDAO Bean: " + studentDAO);
        System.out.println("DepartmentDAO Bean: " + departmentDAO);
        System.out.println("MarksDAO Bean: " + marksDAO);

        context.close();
    }
}
