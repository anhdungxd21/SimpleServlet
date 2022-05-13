package com.example.SampleProject.listener;

import com.example.SampleProject.dao.DBConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author dungla
 */
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized");
        Connection connection = DBConnection.getConnectionToDatabase();
        sce.getServletContext().setAttribute("dbconnection", connection);
        System.out.println("db connected");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed");
        Connection connection = (Connection) sce.getServletContext().getAttribute("dbconnection");
        try {
            connection.close();
            System.out.println("db close");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
