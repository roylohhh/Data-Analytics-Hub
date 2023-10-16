package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import model.Model;

public class UserDaoImpl implements UserDao{
	private final String TABLE_NAME = "users";
	
	public UserDaoImpl() {
		
	}
	//Get the table users
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
	
	//login
	@Override
	public User getUser(String username, String password) throws SQLException {
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
	
	//register
	@Override
	public User createUser(String username, String password, String firstName, String lastName) throws SQLException{
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
	
	//editprofile
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