package com.parker.unicornListener.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.GenericServlet;

import com.parker.unicornListener.dto.User;

public final class UserRepository {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
//	private static final String selectAll = "SELECT * FROM servlettest.userdetails";
	private static final String selectByEmail = "SELECT * FROM servlettest.userdetails where email=?";
	private static final String databaseUrl = "jdbc:mysql://localhost:3306?user=root&password=Optimus@2399";
	private static User userContainer;
	private static final String insert = "insert into servlettest.userdetails (email,password,firstName,lastName) values(?,?,?,?)";

	private UserRepository() {

	}

	public static boolean isValidUser(String email, String password, GenericServlet servlet) {
		try {
			servlet.log("Inside Repository --------------------------------------------------------------------------");
			connection = DriverManager.getConnection(databaseUrl);
			servlet.log("Schema : " + connection.getSchema());
			servlet.log("Driver Name : " + connection.getMetaData().getDriverName());
			preparedStatement = connection.prepareStatement(selectByEmail);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
//			loginServlet.log("Cursor Name : " + resultSet.getCursorName());
			/* resultSet.setFetchDirection(ResultSet.TYPE_SCROLL_INSENSITIVE); */
			User user = User.getInstance();
			if (resultSet.next()) {
				if (password.contentEquals(resultSet.getString(3))) {
					user.setEmail(resultSet.getString(2));
					user.setPassword(resultSet.getString(3));
					user.setFirstName(resultSet.getString(4));
					user.setLastName(resultSet.getString(5));
					userContainer = user;
					return true;
				} else
					return false;
			}
			return false;

		} catch (SQLException e) {
			servlet.log(e.getMessage(), e);
			servlet.log("SQL Error Code : " + e.getErrorCode());
			servlet.log(
					"End of  Repository --------------------------------------------------------------------------");
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				servlet.log(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		servlet.log("End of  Repository --------------------------------------------------------------------------");
		return false;
	}

	public static Optional<User> getUser() {
		return Optional.of(userContainer);
	}

	public static Optional<User> insertUser(String email, String password, String firstName, String lastName,
			GenericServlet servlet) {
		User user = new User();
		PreparedStatement preparedStatement1 = null;
		servlet.log("inside insert User..................");
		try {
			connection = DriverManager.getConnection(databaseUrl);
			preparedStatement1 = connection.prepareStatement(insert);
			preparedStatement1.setString(1, email);
			preparedStatement1.setString(2, password);
			preparedStatement1.setString(3, firstName);
			preparedStatement1.setString(4, lastName);
			servlet.log("Values has set for Place Holder");
			servlet.log("user getting Inserted");
			if (exists(email)) {
				int impactedRows = preparedStatement1.executeUpdate();
				servlet.log("Impacted Rows : " + impactedRows);
				if (impactedRows != 0) {
					servlet.log("User Inserted Successfully..............");
					user = new User();
					user.setFirstName(firstName);
					user.setEmail(email);
					user.setLastName(lastName);
					user.setPassword(password);
				}
			}
		} catch (SQLException e) {
			servlet.log(e.getMessage(), e);
			e.printStackTrace();
		} finally {
			try {
				preparedStatement1.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				servlet.log(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return Optional.ofNullable(user);
	}

	public static boolean exists(String email) {
		try {
			connection = DriverManager.getConnection(databaseUrl);
			preparedStatement = connection.prepareStatement(selectByEmail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return preparedStatement.getResultSet() == null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
