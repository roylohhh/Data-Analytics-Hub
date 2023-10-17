package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Post;

public class DeletePostController {
	@FXML
	private TextField id;
	@FXML
	private Label message;
	@FXML
	private Button deletePost;
	@FXML
	private Button close;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	public DeletePostController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		deletePost.setOnAction(event ->{
			if(!id.getText().isEmpty()) {
				try {
					int ID = Integer.parseInt(id.getText());
					model.getPostDao().deletePost(ID);
					message.setText("Post successfully deleted");
					message.setTextFill(Color.GREEN);
				} catch(NumberFormatException e) {
					message.setText("Invalid ID");
					message.setTextFill(Color.RED);
				} catch(SQLException e) {
					message.setText(e.getMessage());
				}
			} else {
				message.setText("Empty ID");
				message.setTextFill(Color.RED);
			}
		});
		
		close.setOnAction(event -> {
			stage.close();
			parentStage.show();
		});
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Retrieve Post");
		stage.show();
	}
	
}
