package com.sathya.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

public class ProductDao {
	
	public int insertProduct(Product product) throws ServletException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    int result = 0;

	    try {
	        connection = DatabaseUtils.createConnection();
	        String sql = "INSERT INTO Product_Details (proId, proName, proCost, proBrand, proMfgDate, proExpDate, proMadeIn, proImage) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, product.getProId());
	        preparedStatement.setString(2, product.getProName());
	        preparedStatement.setDouble(3, product.getProCost());
	        preparedStatement.setString(4, product.getProBrand());
	        preparedStatement.setDate(5, product.getProMfgDate());
	        preparedStatement.setDate(6, product.getProExpDate());
	        preparedStatement.setString(7, product.getProMadeIn());
	        preparedStatement.setBytes(8, product.getProImage());

	        result = preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        throw new ServletException("Database operation failed", e);
	    } finally {
	        // Close resources in a single try-catch block
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	    }
	    return result;
	}
	
	// Method to retrieve all products from the database
	public List<Product> displayListOfProducts() throws ServletException {
	    List<Product> productList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DatabaseUtils.createConnection();
	        String sql = "SELECT * FROM Product_Details";
	        preparedStatement = connection.prepareStatement(sql);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            String proId = resultSet.getString("proId");
	            String proName = resultSet.getString("proName");
	            double proCost = resultSet.getDouble("proCost");
	            String proBrand = resultSet.getString("proBrand");
	            Date proMfgDate = resultSet.getDate("proMfgDate");
	            Date proExpDate = resultSet.getDate("proExpDate");
	            String proMadeIn = resultSet.getString("proMadeIn");

	            byte[] proImage = resultSet.getBytes("proImage");

	            Product product = new Product(proId, proName, proCost, proBrand, proMfgDate, proExpDate, proMadeIn, proImage);
	            productList.add(product);
	        }
	    } catch (SQLException e) {
	        throw new ServletException("Database operation failed", e);
	    } finally {
	        // Close resources in a single try-catch block
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	    }
	    return productList;
	}
	
	public void deleteProduct(String proId) throws ServletException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = DatabaseUtils.createConnection();
	        String sql = "DELETE FROM Product_Details WHERE proId = ?";
	        
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, proId);

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Product with ID " + proId + " deleted successfully.");
	        } else {
	            System.out.println("Product with ID " + proId + " not found.");
	        }
	    } catch (SQLException e) {
	        System.err.println("Error deleting product with ID " + proId);
	        e.printStackTrace();
	        throw new ServletException("Database operation failed", e);
	    } finally {
	        // Close resources in a single try-catch block
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	    }
	}

	public Product getProductById(String proId) throws ServletException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Product product = null;

	    try {
	        connection = DatabaseUtils.createConnection();
	        String sql = "SELECT * FROM Product_Details WHERE proId=?";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, proId);
	        resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            product = new Product();

	            product.setProId(resultSet.getString("proId"));
	            product.setProName(resultSet.getString("proName"));
	            product.setProCost(resultSet.getDouble("proCost"));
	            product.setProBrand(resultSet.getString("proBrand"));
	            product.setProMfgDate(resultSet.getDate("proMfgDate"));
	            product.setProExpDate(resultSet.getDate("proExpDate"));
	            product.setProMadeIn(resultSet.getString("proMadeIn"));
	            product.setProImage(resultSet.getBytes("proImage"));
	        }
	    } catch (SQLException e) {
	        throw new ServletException("Database operation failed", e);
	    } finally {
	        // Close resources in a single try-catch block
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return product;
	}

	public int updateProduct(Product updatedProduct) {
	    int result = 0;
	        try(Connection connection = DatabaseUtils.createConnection())
	        {
	             // SQL query to update product details
	            String sql = "UPDATE Product_Details SET proName=?, proCost=?, proBrand=?, " +
	                    "proMfgDate=?, proExpDate=?, proMadeIn=?, proImage=? WHERE proId=?";
	            
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                // Set the parameters for the update statement
	            preparedStatement.setString(1, updatedProduct.getProName());
	            preparedStatement.setDouble(2, updatedProduct.getProCost());
	            preparedStatement.setString(3, updatedProduct.getProBrand());
	            preparedStatement.setDate(4, updatedProduct.getProMfgDate());
	            preparedStatement.setDate(5, updatedProduct.getProExpDate());
	            preparedStatement.setString(6, updatedProduct.getProMadeIn());
		        preparedStatement.setBytes(7, updatedProduct.getProImage());
	            preparedStatement.setString(8, updatedProduct.getProId());

	                // Execute the update
	                result = preparedStatement.executeUpdate();
	            }
	         catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return result;
	    }
}
	

