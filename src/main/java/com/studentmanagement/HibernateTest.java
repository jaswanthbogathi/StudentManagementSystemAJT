
package com.studentmanagement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {

    public static void main(String[] args) {

        try {

            SessionFactory factory =
                    HibernateUtil.getSessionFactory();

            System.out.println(
                    "Hibernate Started Successfully!"
            );

            try (Session session = factory.openSession()) {

                session.doWork(connection -> {

                    if (connection.isValid(5)) {
                        System.out.println(
                                "MySQL Database Connected Successfully!"
                        );
                    }
                });
            }

        } catch (Exception e) {

            System.out.println("Hibernate Connection Failed!");
            e.printStackTrace();

        } finally {

            HibernateUtil.shutdown();
        }
    }
}
