
package com.studentmanagement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        event.getServletContext().setAttribute("springContext", context);

        System.out.println("Spring Context Started Successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        AnnotationConfigApplicationContext context =
                (AnnotationConfigApplicationContext)
                event.getServletContext().getAttribute("springContext");

        if (context != null) {
            context.close();
        }

        System.out.println("Spring Context Closed!");
    }
}
