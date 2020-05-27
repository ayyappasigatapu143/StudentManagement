package com.student.management.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.student.management.models.User;
import com.sun.rowset.CachedRowSetImpl;

public class Test {
public static void main(String[] args) {
	String user = "root";/* properties.getProperty("jdbc.user"); */
	String password = "root";/* properties.getProperty("jdbc.password"); */
	String driverClass = "com.mysql.jdbc.Driver";/* properties.getProperty("jdbc.driverClass"); */
	String url = "jdbc:mysql://localhost:3306/students";/* properties.getProperty("jdbc.jdbcUrl"); */
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet res = null;
	CachedRowSetImpl rowSet = null;
	String uname="ayyappa.sigatapu";
	String pass="Venki@12345";
  String sql ="select * from student where user_name='"+uname+"' and password='"+pass+"' ";
	try {
		Class.forName(driverClass);
		connection = DriverManager.getConnection(url, user, password);
		statement = connection.prepareStatement(sql);
		res = statement.executeQuery(sql);
		
		
	
			   User users =new User();
			   while(res.next()) {
				   users.setUserId(res.getInt(1));
				   users.setFirstName(res.getString(1));
				   users.setLastName(res.getString(2));
				   users.setUsername(res.getString(3));
				   users.setPassword(res.getString(4));
				   users.setPhone(res.getString(5));
				   users.setAddress(res.getString(6));
		
			   }
				   	  
			   System.out.println(users.getUsername()+":"+users.getPassword());
		

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (res != null) {
				res.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
}
