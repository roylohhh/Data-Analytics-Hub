package dao;

import java.sql.SQLException;
import java.util.List;

import model.Post;

public interface PostDao {
	void setup() throws SQLException;
	Post getPost(int id) throws SQLException;
	Post createPost(int ID, String content, String author, int likes, int shares) throws SQLException;
	void deletePost(int id) throws SQLException;
	List<Post> getAllPosts() throws SQLException;
}