package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;
import model.Post;

public class TopPostsController {
	@FXML
	private TextField number;
	@FXML
	private TextArea message;
	@FXML
	private Button retrievePosts;
	@FXML
	private Button close;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	public TopPostsController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		retrievePosts.setOnAction(event ->{
			try {
				if(!number.getText().isEmpty()) {
					int numOfPosts = Integer.parseInt(number.getText());
					message.clear();
					List<Post> topNPosts = retrieveTopNPostsByLikes(numOfPosts);
					Iterator<Post> iterator = topNPosts.iterator();
					
					while (iterator.hasNext()) {
						Post post = iterator.next();
						message.appendText(String.format("ID: %d | Content: %s | Author: %s | Likes: %d | Shares: %d | DateTime: %s\n", 
						post.getID(), post.getContent(), post.getAuthor() ,post.getLikes(), post.getShares(), post.getPostDateTime()));
					 }					
				} else {
					message.setText("Pls enter number of posts");
				}
			} catch (NumberFormatException e) {
		    	message.setText("Pls enter a number");
		    }
		});
		
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public List<Post> retrieveTopNPostsByLikes(int numOfPosts) {
		List<Post> topNPosts = new ArrayList<>();
		try {
			List<Post> Posts = model.getPostDao().getAllPosts();
			Iterator<Post> iterator = Posts.iterator();
			
			int count = 0;
			while (iterator.hasNext() && count < numOfPosts) {
		        Post post = iterator.next();
		        topNPosts.add(post);
		        count++;
		    } 
		} catch (SQLException e) {
			message.setText(e.getMessage());
		}
	
	    return topNPosts;
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 400);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Retrieve Top Posts");
		stage.show();
	}
}
