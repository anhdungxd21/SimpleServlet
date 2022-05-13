package com.example.SampleProject.dao;

import com.example.SampleProject.beans.Product;
import com.example.SampleProject.beans.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dungla
 */
public class ApplicationDao {

    public List<Product> searchProducts(String searchString, Connection connection) {
        Product product = null;
        List<Product> products = new ArrayList<>();

        try {

            String sql = "select * from products where product_name like '%" + searchString + "%';";

            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery(sql);

            while(set.next()) {
                product = new Product();
                product.setProductId(set.getInt("product_id"));
                product.setProductImgPath(set.getString("image_path"));
                product.setProductName(set.getString("product_name"));
                products.add(product);
            }
        }catch (SQLException e ) {
            e.printStackTrace();
        }
        return products;
    }

    public int registerUser(User user) {
        int rowsAffected = 0;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getActivity());

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}
