package com.example.SampleProject.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dungla
 */
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("name");
        String httpResponse = "<html><h3>Welcome to Servlet!</h3></html>";
        PrintWriter writer = resp.getWriter();
        writer.write(httpResponse + " " + value);
    }
}
