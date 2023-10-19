package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Post;

public class RetrievePostController {
	@FXML
	private TextField id;
	@FXML
	private TextArea message;
	@FXML
	private Button retrievePost;
	@FXML
	private Button close;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	public RetrievePostController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		retrievePost.setOnAction(event -> {
			if(!id.getText().isEmpty()) {	
				try { 
					int ID = Integer.parseInt(id.getText());
					Post post = model.getPostDao().getPost(ID);
					if(post != null) { //formatter used to ensure there is a dd/MM/yyyy HH:mm format
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
						String DateTime = post.getPostDateTime().format(formatter);
						message.setText(String.format("ID: %d | Content: %s | Author: %s | Likes: %d | Shares: %d | DateTime: %s", 
								post.getID(), post.getContent(), post.getAuthor() ,post.getLikes(), post.getShares(), DateTime));
					} else { //id input does not exist in posts table
						message.setText("Post not found");
					}
					
				} catch(NumberFormatException e) { //if something other than integer is input
					message.setText("Invalid ID");
				} catch(SQLException e) {
					message.setText(e.getMessage());
				}
			} else { //no input
				message.setText("Empty ID");
			}
		});
		
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 400);
		stage.setScene(scene);
		stage.setResizable(true);
		stage.setTitle("Retrieve Post");
		stage.show();
	}
}
