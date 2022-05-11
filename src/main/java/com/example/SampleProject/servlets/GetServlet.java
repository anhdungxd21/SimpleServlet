package com.example.SampleProject.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dungla
 */
@WebServlet(urlPatterns = "/getServlet",
            initParams = @WebInitParam(name = "URL", value = "http://weatherservice.com"))
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String url = config.getInitParameter("URL");
        System.out.println(url);

        ServletContext context = getServletContext();
        System.out.println(context.getInitParameter("dbURL"));
        String value = req.getParameter("name");
        String httpResponse = "<html><h3>Welcome to Servlet!</h3></html>";
        PrintWriter writer = resp.getWriter();
        writer.write(httpResponse + " " + value);
    }
}
