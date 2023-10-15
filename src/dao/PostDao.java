package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javafx.scene.control.TextField;
import model.Post;

public interface PostDao {
	void setup() throws SQLException;
	Post getPost(int id, String content, String author, int likes, int shares) throws SQLException;
	Post createPost(int ID, String content, String author, int likes, int shares) throws SQLException;
	boolean removePost(int id) throws SQLException;
	
}