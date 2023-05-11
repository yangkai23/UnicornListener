package com.parker.unicornListener.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
	private static final String selectAll = "SELECT * FROM servlettest.userdetails";
	private static final String selectByEmail = "SELECT * FROM servlettest.userdetails where email=?";
	private static final String databaseUrl = "jdbc:mysql://localhost:3306?user=root&password=Optimus@2399";
	private static User userContainer;
	private static String email;

	private UserRepository() {

	}

	public static boolean isValidUser(String email, String password) {
		try {
			connection = DriverManager.getConnection(databaseUrl);
			preparedStatement = connection.prepareStatement(selectByEmail);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			/* resultSet.setFetchDirection(ResultSet.TYPE_SCROLL_INSENSITIVE); */
			UserRepository.email = email;
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
			e.printStackTrace();
		}

		return false;
	}

	public static Optional<User> getUser() {
		return Optional.ofNullable(userContainer);
	}

}
