package com.sathya.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseUtils {
	public static Connection createConnection()
	{	Connection connection = null; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/venkey","root","root");
		}
		catch(ClassNotFoundException | SQLException e)
		{	e.printStackTrace();
		}
		return connection;

	}

}
