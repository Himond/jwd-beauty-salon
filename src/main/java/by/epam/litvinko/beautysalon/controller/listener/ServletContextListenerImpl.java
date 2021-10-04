package by.epam.litvinko.beautysalon.controller.listener;
import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DatabaseConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DatabaseConnectionPool.getInstance().destroyPool();
    }
}