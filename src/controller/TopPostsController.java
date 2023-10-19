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
			try { //if input is not empty
				if(!number.getText().isEmpty()) {
					int numOfPosts = Integer.parseInt(number.getText());
					message.clear(); //clears the appended message 
					List<Post> topNPosts = retrieveTopNPostsByLikes(numOfPosts); //function is called
					Iterator<Post> iterator = topNPosts.iterator(); //iterates over topNPosts
					
					while (iterator.hasNext()) { //append is used so that multiple posts can be displayed to user
						Post post = iterator.next();
						message.appendText(String.format("ID: %d | Content: %s | Author: %s | Likes: %d | Shares: %d | DateTime: %s\n", 
						post.getID(), post.getContent(), post.getAuthor() ,post.getLikes(), post.getShares(), post.getPostDateTime()));
					 }	
				} else { //empty input
					message.setText("Pls enter number of posts");
				}
			} catch (NumberFormatException e) { //something that is not a number is input
		    	message.setText("Pls enter a number");
		    }
		});
		
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public List<Post> retrieveTopNPostsByLikes(int numOfPosts) {
		List<Post> topNPosts = new ArrayList<>(); //new arrayList called topNPosts
		try {
			List<Post> Posts = model.getPostDao().getAllPosts(); //Store all posts in posts list
			Iterator<Post> iterator = Posts.iterator(); //iterator to iterate over posts list
			
			int count = 0;
			while (iterator.hasNext() && count < numOfPosts) { //while the count is less than number user has input
		        Post post = iterator.next(); //iterates and adds post to topNPosts list
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
		stage.setResizable(true);
		stage.setTitle("Retrieve Top Posts");
		stage.show();
	}
}
