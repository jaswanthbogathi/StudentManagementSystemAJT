
package com.studentmanagement;

public class StudentHibernateTest {

    public static void main(String[] args) {

        StudentHibernateDAO dao = new StudentHibernateDAO();

        try {
            // Create a temporary student
            StudentEntity student = new StudentEntity();

            student.setName("Delete Test Student");
            student.setGender("Male");
            student.setEmail("delete_test@gmail.com");
            student.setPhone("9999999999");
            student.setDepartmentId(null);

            // Add temporary student
            dao.addStudent(student);

            int testId = student.getStudentId();

            System.out.println("Temporary Student Added. ID: " + testId);

            // Delete temporary student
            dao.deleteStudent(testId);

            System.out.println("Temporary Student Deleted Successfully!");

            // Verify deletion
            StudentEntity check = dao.getStudentById(testId);

            if (check == null) {
                System.out.println("Delete Verified Successfully!");
            } else {
                System.out.println("Delete Verification Failed!");
            }

            System.out.println("\nHibernate Delete Test Completed!");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
