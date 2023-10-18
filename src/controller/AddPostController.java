package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Post;

public class AddPostController {
	@FXML
	private TextField id;
	@FXML
	private TextArea content;
	@FXML
	private TextField author;
	@FXML
	private TextField likes;
	@FXML
	private TextField shares;
	@FXML
	private Button addPost;
	@FXML
	private Button close;
	@FXML
	private Label status;
	
	private Stage stage;
	private Stage parentStage;
	private Model model;
	
	public AddPostController(Stage parentStage, Model model ) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		addPost.setOnAction(event -> {
			if(!id.getText().isEmpty() && !content.getText().isEmpty() && !author.getText().isEmpty() && !likes.getText().isEmpty() && !shares.getText().isEmpty()) {
				Post post;
				int ID = Integer.parseInt(id.getText());
				int Likes = Integer.parseInt(likes.getText());
				int Shares = Integer.parseInt(shares.getText());
				try {
					post = model.getPostDao().createPost(ID, content.getText(), author.getText(), Likes, Shares);
					if(post != null) {
						status.setText("Post added");
						status.setTextFill(Color.GREEN);
					} else {
						status.setText("Post cannot be created");
						status.setTextFill(Color.RED);
					}
				} catch(SQLException e) {
					status.setText("Please use a different post ID");
					status.setTextFill(Color.RED);	
				}
			} else {
					status.setText("Empty fields!");
					status.setTextFill(Color.RED);
				}
		});
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 600);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Add Post");
		stage.show();
	}
	
}
