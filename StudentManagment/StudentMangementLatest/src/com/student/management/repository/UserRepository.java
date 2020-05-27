package com.student.management.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.student.management.models.RoomDetails;
import com.student.management.models.User;
import com.student.management.util.DBConstants;
import com.student.management.util.DBUtil;

public class UserRepository {

	public String createUser(User user) {

		try {
			User userExists = checkUserExists(user);
			if (userExists != null) {
				return "USER_ALREADY_EXISTS";
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "insert into student(first_name,last_name,user_name,password,phone,comment) values(?,?,?,?,?,?)";
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(query);
			if (user != null) {
				preparedStatement.setString(1, user.getFirstName());
				preparedStatement.setString(2, user.getLastName());
				preparedStatement.setString(3, user.getUsername());
				preparedStatement.setString(4, user.getPassword());
				preparedStatement.setString(5, user.getPhone());
				preparedStatement.setString(6, user.getAddress());
				int count = preparedStatement.executeUpdate();
				preparedStatement.close();
				connection.close();
				if (count > 0) {
					return "SUCCESS";
				} else {
					return "ERROR";
				}

			} else {
				return "ERROR";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User checkUserExists(User user) throws SQLException {
		String query = "select * from student where user_name='" + user.getUsername() + "'";
		ResultSet res = DBUtil.mySQLResult(query);
		if (res.next()) {
			User users = new User();
			while (res.next()) {
				users.setUserId(res.getInt(1));
				users.setFirstName(res.getString(1));
				users.setLastName(res.getString(2));
				users.setUsername(res.getString(3));
				users.setPassword(res.getString(4));
				users.setPhone(res.getString(5));
				users.setAddress(res.getString(6));
			}
			return users;
		}
		return null;
	}

	public User checkValidUser(User userObj) {
		Properties properties = DBUtil.getProperties();
		String user = properties.getProperty("jdbc.user");
		String password = properties.getProperty("jdbc.password");
		String driverClass = properties.getProperty("jdbc.driverClass");
		String url = properties.getProperty("jdbc.jdbcUrl");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		String sql = "select * from student where user_name='" + userObj.getUsername() + "' and password='"
				+ userObj.getPassword() + "' ";
		User users = new User();
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.prepareStatement(sql);
			res = statement.executeQuery(sql);

			while (res.next()) {
				users.setUserId(res.getInt(1));
				users.setFirstName(res.getString(2));
				users.setLastName(res.getString(3));
				users.setUsername(res.getString(4));
				users.setPassword(res.getString(5));
				users.setPhone(res.getString(6));
				users.setAddress(res.getString(7));
				users.setRole(res.getString(8));
			}

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
		return users;
	}

	public List<RoomDetails> getRoomDetails() {
		String query = "select * from rooms";
		ResultSet res = DBUtil.mySQLResult(query);
		List<RoomDetails> roomsList = new ArrayList<RoomDetails>();
		try {
			if (res.next()) {
				while (res.next()) {
					RoomDetails details = new RoomDetails();
					details.setRoomId(res.getInt(1));
					details.setRoomType(res.getString(2));
					details.setRoomLocation(res.getString(3));
					details.setRoomStatus(res.getString(4));
					details.setMonthlyCharges(res.getString(5));
					details.setPaymentStatus(res.getString(6));
					details.setUserId(res.getInt(7));
					details.setUsername(res.getString(8));
					roomsList.add(details);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roomsList;
	}

	public String addNewRoom(RoomDetails roomDetails) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			String query = "insert into rooms(room_id,room_type,room_location,room_status,monthly_charges,payment_status,user_id,user_name)\r\n"
					+ "values(?,?,?,?,?,?,?,?)";
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(query);
			if (roomDetails != null) {
				preparedStatement.setInt(1, Integer.valueOf(roomDetails.getRoomId()));
				preparedStatement.setString(2, roomDetails.getRoomType());
				preparedStatement.setString(3, roomDetails.getRoomLocation());
				preparedStatement.setString(4, roomDetails.getRoomStatus());
				preparedStatement.setString(5, roomDetails.getMonthlyCharges());
				preparedStatement.setString(6, roomDetails.getPaymentStatus());
				preparedStatement.setInt(7, Integer.valueOf(5));
				preparedStatement.setString(8, "GIRI");
				int count = preparedStatement.executeUpdate();
				preparedStatement.close();
				connection.close();
				if (count > 0) {
					return "SUCCESS";
				} else {
					return "ERROR";
				}

			} else {
				return "ERROR";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<RoomDetails> deleteRoom(RoomDetails roomDetails) {
		String sql = "DELETE FROM rooms WHERE room_id='" + roomDetails.getRoomId() + "'";
		try {
			Connection con = DBUtil.mySQLConnection();
			Statement statement = con.createStatement();

			boolean result = statement.execute(sql);
			statement.close();
			con.close();
			if (result == false || result == true) {
				return getRoomDetails();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public RoomDetails getRoomDetailsByRoomId(RoomDetails roomDetails) {
		String query = "select * from rooms";
		ResultSet res = DBUtil.mySQLResult(query);
		List<RoomDetails> roomsList = new ArrayList<RoomDetails>();
		try {
			if (res.next()) {
				while (res.next()) {
					RoomDetails details = new RoomDetails();
					details.setRoomId(res.getInt(1));
					details.setRoomType(res.getString(2));
					details.setRoomLocation(res.getString(3));
					details.setRoomStatus(res.getString(4));
					details.setMonthlyCharges(res.getString(5));
					details.setPaymentStatus(res.getString(6));
					details.setUserId(res.getInt(7));
					details.setUsername(res.getString(8));
					roomsList.add(details);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roomsList.get(0);

	}

	public String editRoomDetails(RoomDetails roomDetails) {
		String sql = "	update rooms set room_id=?,room_type=?,\r\n"
				+ "    room_location=?,room_status=?,monthly_charges=?,payment_status=? \r\n" + "    where room_id=?";
		try {
			Connection con = DBUtil.mySQLConnection();
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, roomDetails.getRoomId());
			ps.setString(2, roomDetails.getRoomType());
			ps.setString(3, roomDetails.getRoomLocation());
			ps.setString(4, roomDetails.getRoomStatus());
			ps.setString(5, roomDetails.getMonthlyCharges());
			ps.setString(6, roomDetails.getPaymentStatus());
			ps.setInt(7, roomDetails.getRoomId());
			int result = ps.executeUpdate();
			ps.close();
			con.close();
			if (result > 0) {
				return "SUCCESS";
			} else {
				return "ERROR";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<RoomDetails> getRoomDetailsAvailable() {
		String user = DBConstants.USERNAME;
		String password = DBConstants.PASSWORD;
		String driverClass = DBConstants.DRIVER_CLASS;
		String url = DBConstants.DB_URL;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		String sql = "select * from rooms where room_status='AVAILABLE'";
		User users = new User();

		List<RoomDetails> roomsList = new ArrayList<RoomDetails>();
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.prepareStatement(sql);
			res = statement.executeQuery(sql);

			while (res.next()) {
				RoomDetails details = new RoomDetails();
				details.setRoomId(res.getInt(1));
				details.setRoomType(res.getString(2));
				details.setRoomLocation(res.getString(3));
				details.setRoomStatus(res.getString(4));
				details.setMonthlyCharges(res.getString(5));
				details.setPaymentStatus(res.getString(6));
				details.setUserId(res.getInt(7));
				details.setUsername(res.getString(8));
				roomsList.add(details);
			}

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
		return roomsList;
	}

	public User getUserDetails(String username) {
		String user = DBConstants.USERNAME;
		String password = DBConstants.PASSWORD;
		String driverClass = DBConstants.DRIVER_CLASS;
		String url = DBConstants.DB_URL;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet res = null;
		String sql = "select * from student where user_name='" + username + "' ";
		User users = new User();
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.prepareStatement(sql);
			res = statement.executeQuery(sql);

			while (res.next()) {
				users.setUserId(res.getInt(1));
				users.setFirstName(res.getString(2));
				users.setLastName(res.getString(3));
				users.setUsername(res.getString(4));
				users.setPassword(res.getString(5));
				users.setPhone(res.getString(6));
				users.setAddress(res.getString(7));
				users.setRole(res.getString(8));
			}

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
		return users;
	}
}
