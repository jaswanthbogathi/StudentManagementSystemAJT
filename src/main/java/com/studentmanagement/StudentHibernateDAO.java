
package com.studentmanagement;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentHibernateDAO {

    // ADD STUDENT
    public void addStudent(StudentEntity student) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

            System.out.println("Student Added Using Hibernate!");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    // VIEW ALL STUDENTS
    public List<StudentEntity> getAllStudents() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM StudentEntity", StudentEntity.class
            ).list();

        } catch (Exception e) {

            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    // GET STUDENT BY ID
    public StudentEntity getStudentById(int id) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(StudentEntity.class, id);

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    // UPDATE STUDENT
    public void updateStudent(StudentEntity student) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(student);

            transaction.commit();

            System.out.println("Student Updated Using Hibernate!");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    // DELETE STUDENT
    public void deleteStudent(int id) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            StudentEntity student =
                    session.get(StudentEntity.class, id);

            if (student != null) {
                session.remove(student);
            }

            transaction.commit();

            System.out.println("Student Deleted Using Hibernate!");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
