package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
					+ "postDateTime VARCHAR(20) NOT NULL,"
					+ "PRIMARY KEY (id))");
			statement.executeUpdate(sql);
		}
	}
	
	@Override
	//retrieve Post
	public Post getPost(int id) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		try (Connection connection = Database.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql);){
			stmt.setInt(1, id);
			
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					Post post = new Post();
					post.setID(rs.getInt("id"));
					post.setContent(rs.getString("content"));
					post.setAuthor(rs.getString("author"));
					post.setLikes(rs.getInt("likes"));
					post.setShares(rs.getInt("shares"));
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //formats dateTime into dd/MM/yyyy HH:mm
					post.setPostDateTime(LocalDateTime.parse(rs.getString("postDateTime"),formatter));
					return post;
				}
			}
		}
		return null;
	}
	
	@Override
	//add Post
	public Post createPost(int id, String content, String author, int likes, int shares) throws SQLException{
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?,?)" ;
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, id);
			statement.setString(2, content);
			statement.setString(3, author);
			statement.setInt(4, likes);
			statement.setInt(5, shares);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //formats dateTime into dd/MM/yyyy HH:mm
			statement.setString(6, LocalDateTime.now().format(formatter)); 
			

			statement.executeUpdate();
			return new Post(id, content, author, likes, shares, LocalDateTime.now());
		}	
	}
	
	@Override
	//remove Post
	public void deletePost(int id) throws SQLException{
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}
	
	@Override
	//TODO: get top posts
	public List<Post> getAllPosts() {
		String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY likes DESC";
		List<Post> posts = new ArrayList<>();
		try (Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);){
			try(ResultSet rs = statement.executeQuery()){
				while(rs.next()) {
					Post post = new Post();
					post.setID(rs.getInt("id"));
					post.setContent(rs.getString("content"));
					post.setAuthor(rs.getString("author"));
					post.setLikes(rs.getInt("likes"));
					post.setShares(rs.getInt("shares"));
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //formats dateTime into dd/MM/yyyy HH:mm
					post.setPostDateTime(LocalDateTime.parse(rs.getString("postDateTime"),formatter));
					posts.add(post);
				}
			} 
			
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return posts;
	}
	

		
}
