package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Post;

public class PostDaoImpl implements PostDao{
private final String TABLE_NAME = "posts";
	
	public PostDaoImpl() {
		
	}
	
	@Override
	public void setup() throws SQLException{
		try(Connection connection = Database.getConnection();
				Statement statement = connection.createStatement();){
			String sql = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME 
					+ "(id INT NOT NULL,"
					+ "content VARCHAR(40) NOT NULL,"
					+ "author VARCHAR(20) NOT NULL,"
					+ "likes INT NOT NULL,"
					+ "shares INT NOT NULL,"
					+ "PRIMARY KEY (id))");
			statement.executeUpdate(sql);
		}
	}
	
	@Override
	//retrieve Post
	public Post getPost(int id, String content, String author, int likes, int shares) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ? AND content = ? AND author = ? AND likes =? AND shares =?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);){
			stmt.setInt(1, id);
			stmt.setString(2, content);
			stmt.setString(3, author);
			stmt.setInt(4, likes);
			stmt.setInt(5, shares);
			
			try(ResultSet rs = stmt.executeQuery()){
				if (rs.next()) {
					Post post = new Post();
					post.setID(rs.getInt(id));
					post.setContent(rs.getString(content));
					post.setAuthor(rs.getString(author));
					post.setLikes(rs.getInt(likes));
					post.setShares(rs.getInt(shares));
					return post;
				}
				return null;
			}
		}
	}
	
	@Override
	//add Post
	public Post createPost(int id, String content, String author, int likes, int shares) throws SQLException{
		String sql = "INSERT INTO" + TABLE_NAME + "VALUES(?,?)" ;
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, id);
			statement.setString(2, content);
			statement.setString(3, author);
			statement.setInt(4, likes);
			statement.setInt(5, shares);

			statement.executeUpdate();
			return new Post(id, content, author, likes, shares);
		}	
	}
	
	//TODO: remove Post
	@Override
	public boolean removePost(int id) throws SQLException {
	    String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
	    try (Connection connection = Database.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);) {
	        statement.setInt(1, id);
	        
	        int rowsDeleted = statement.executeUpdate();
	        return rowsDeleted > 0;  // Returns true if at least one row (post) is deleted.
	    }
	}

		
		
	
}
