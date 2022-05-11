package com.example.SampleProject.servlets;

import com.example.SampleProject.dao.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author dungla
 */
public class HomeServlet extends HttpServlet {
    private Connection connection = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doGet method");
        req.getRequestDispatcher("/html/index.html").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("in init method");
        connection = DBConnection.getConnectionToDatabase();
    }

    @Override
    public void destroy() {
        System.out.println("in destroy method");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
