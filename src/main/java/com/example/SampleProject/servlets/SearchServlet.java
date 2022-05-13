package com.example.SampleProject.servlets;

import com.example.SampleProject.beans.Product;
import com.example.SampleProject.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.MessageFormat;
import java.util.List;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//collect search string from the form
		String searchString = req.getParameter("search");
		
		req.getSession().setAttribute("search", searchString);
		//call DAO layer and get all products for search criteria
		Connection connection = (Connection) getServletContext().getAttribute("dbconnection");
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(searchString,connection);
		
		//write the products data back to the client browser
		/*String page = getHTMLString(req.getServletContext().getRealPath("/html/searchResults.html"), products);
		resp.getWriter().write(page);*/
		req.setAttribute("products", products);
		req.getRequestDispatcher("/html/searchResults.jsp").forward(req, resp);
		
		
	}
	
	/**
	 * this methods reads the HTML template as a String, replaces placeholders
	 * with the values and returns the entire page as a String
	 * @param filePath
	 * @param products
	 * @return
	 * @throws IOException
	 */
	
	public String getHTMLString(String filePath, List<Product> products) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line="";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		
		reader.close();
		String page = buffer.toString();
		
		page = MessageFormat.format(page, products.get(0).getProductImgPath(),
				products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
				products.get(0).getProductName(),products.get(1).getProductName(),
				products.get(2).getProductName(),0);
		
		return page;
		
		
	}
}
