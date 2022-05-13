package com.example.SampleProject.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class GetServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletConfig config = getServletConfig();
		System.out.println(config.getInitParameter("URL"));
		ServletContext context = getServletContext();
		System.out.println(context.getInitParameter("dbURL"));
		
		String htmlResponse = "<html><h3>Welcome to Servlets!</h3></html>";
		PrintWriter writer = resp.getWriter();
		writer.write(htmlResponse);
		
	}

}
