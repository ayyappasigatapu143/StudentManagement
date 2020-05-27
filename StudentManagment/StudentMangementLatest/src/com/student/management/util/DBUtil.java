package com.student.management.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.sun.rowset.CachedRowSetImpl;

public class DBUtil {

	public static Connection getConnection() {

		// reading database properties from the applcation.properties file
		Properties properties = getProperties();
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		String url = properties.getProperty("jdbc.jdbcUrl");
		Connection connection = null;

		try {

			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static CachedRowSetImpl mySQLResult(String sql) {
		// reading database properties from the applcation.properties file
		Properties properties = getProperties();
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		String url = properties.getProperty("jdbc.jdbcUrl");
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		CachedRowSetImpl rowSet = null;

		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			rowSet = new CachedRowSetImpl();
			rowSet.populate(rs);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
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

		return rowSet;
	}

	public static Connection mySQLConnection() {
		// reading database properties from the applcation.properties file
		Properties properties = getProperties();
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		String url = properties.getProperty("jdbc.jdbcUrl");

		Connection connection = null;

		try {

			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static ResultSet getUserResultSet(String sql) {
		// reading database properties from the applcation.properties file
		Properties properties = getProperties();
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		String url = properties.getProperty("jdbc.jdbcUrl");
		ResultSet rs = null;

		try {
			Class.forName(driverClass);
			Connection conn = DriverManager.getConnection(url, user, password);
			PreparedStatement stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static Properties getProperties() {
		InputStream  in = DBUtil.class.getClassLoader().getResourceAsStream("application.properties");
		
		Properties properties = new Properties();

		try {
			
				properties.load(in);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	catch (IOException e) {
		e.printStackTrace();
	}
		

		return properties;
	}

}
