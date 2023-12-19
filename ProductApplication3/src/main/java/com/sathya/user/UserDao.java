package com.sathya.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sathya.product.*;

public class UserDao {

    // Method to insert user details into the database
    public int registerUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result=0;
        try {
            connection = DatabaseUtils.createConnection();
            String sql = "INSERT INTO User_Details (UserName, UserPhone, UserMail, UserId, UserPassword) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPhone());
            preparedStatement.setString(3, user.getUserMail());
            preparedStatement.setString(4, user.getUserId());
            preparedStatement.setString(5, user.getUserPassword());

          result=  preparedStatement.executeUpdate();
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

    // Method to retrieve all users from the database
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtils.createConnection();
            String sql = "SELECT * FROM User_Details";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userName = resultSet.getString("UserName");
                String userPhone = resultSet.getString("UserPhone");
                String userMail = resultSet.getString("UserMail");
                String userId = resultSet.getString("UserId");
                String userPassword = resultSet.getString("UserPassword");

                User user = new User(userName, userPhone, userMail, userId, userPassword);
                userList.add(user);
            }
        }finally {
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
        return userList;
    }
    
    public boolean validateUser(String userId, String userPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtils.createConnection();
            String sql = "SELECT * FROM User_Details WHERE UserId = ? AND UserPassword = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);

            resultSet = preparedStatement.executeQuery();

            // Check if a row is returned, indicating a valid user
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
    }
}
