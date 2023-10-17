package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import model.Model;

public class VIPUserDaoImpl implements VIPUserDao{
	private final String TABLE_NAME = "VIPusers";
	
	/*
	Basically the features are mostly similar as UserDaoImpl, but we are going to
	check whether the user is part of the VIP table/ create user and put them in Vip table
	 */
	public VIPUserDaoImpl() {
		
	}
	
	@Override
	public void setup() throws SQLException{
		try(Connection connection = Database.getConnection();
				Statement statement = connection.createStatement();){
			String sql = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME 
					+ "(username VARCHAR(10) NOT NULL,"
					+ "password VARCHAR(8) NOT NULL,"
					+ "firstName VARCHAR(20) NOT NULL,"
					+ "lastName VARCHAR(20) NOT NULL,"
					+ "PRIMARY KEY (username))");
			statement.executeUpdate(sql);
		}
	}
	
	//I will implement this in LoginController
	@Override
	public User getVIPUser(String username, String password) throws SQLException{
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);){
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			try(ResultSet rs = stmt.executeQuery()){
				if (rs.next()) {
					User user = new User();
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setFirstName(rs.getString("firstName"));
					user.setLastName(rs.getString("lastName"));
					return user;
				}
				return null;
			}
		}
	}
	
	//I will implement this in HomeController
	@Override
	public User createVIPUser(String username, String password, String firstName, String lastName) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?)" ;
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, firstName);
			statement.setString(4, lastName);

			statement.executeUpdate();
			return new User(username, password, firstName, lastName);
		}
	}
	
	//edits VIP profile
	public void editProfile(User user, String currentUsername) throws SQLException{
		String sql = "UPDATE " + TABLE_NAME + " SET username = ?, password = ?, firstName = ?, lastName = ?" + " WHERE username =?";
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(4, user.getLastName());
			statement.setString(5, currentUsername);
			
			statement.executeUpdate();
		}
	}
	
}
