package com.example.SampleProject.servlets;

import com.example.SampleProject.beans.Product;
import com.example.SampleProject.dao.ApplicationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.MessageFormat;
import java.util.List;

/**
 * @author dungla
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchString = req.getParameter("search");

        ApplicationDao dao = new ApplicationDao();
        List<Product> products = dao.searchProducts(searchString);

        String page = this.getHTMLString(req.getServletContext().getRealPath("/html/search.html"),products);

        PrintWriter writer = resp.getWriter();
        writer.write(page);
    }

    public String getHTMLString(String filePath, List<Product> products) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, products.get(0).getProductImgPath(),products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
                products.get(0).getProductName(),products.get(1).getProductName(),products.get(2).getProductName(),0);
        return page;
    }
}
