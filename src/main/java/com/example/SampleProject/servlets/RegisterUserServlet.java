package com.example.SampleProject.servlets;

import com.example.SampleProject.beans.Product;
import com.example.SampleProject.beans.User;
import com.example.SampleProject.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author dungla
 */
@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/register.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String activity = req.getParameter("activity");
        int age = Integer.parseInt(req.getParameter("age"));

        User user = new User(username, password, firstName, lastName, age, activity);

        ApplicationDao dao = new ApplicationDao();

        int row = dao.registerUser(user);

        String infoMessage = null;

        if(row == 0) {
            infoMessage = "Sorry, an error occurred!";
        } else {
            infoMessage = "User registered successfully";
        }
        String page = this.getHTMLString(req.getServletContext().getRealPath("/html/register.html"), infoMessage);
        PrintWriter writer = resp.getWriter();
        writer.write(page);
    }

    public String getHTMLString(String filePath, String message) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, message);
        return page;
    }
}
