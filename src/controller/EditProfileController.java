package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class EditProfileController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;	
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private Button editProfile;
	@FXML
	private Label status;
	@FXML
	private Button close;
	
	private Model model;
	private Stage parentStage;
	private Stage stage;
	
	public EditProfileController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		editProfile.setOnAction(event ->{
			if(!username.getText().isEmpty() && !password.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty()) {
				User user; 
				try {
					user = model.getCurrentUser();
					String currentUsername = user.getUsername();
					user.setUsername(username.getText());
					user.setPassword(password.getText());
					user.setFirstName(firstName.getText());
					user.setLastName(lastName.getText());
					
					model.getUserDao().editProfile(user, currentUsername);
					status.setText("User details updated");
					status.setTextFill(Color.GREEN);
				} catch(SQLException e) {
					status.setText(e.getMessage());
					status.setTextFill(Color.RED);					
				} 					
			} else {
				status.setText("Empty username or password");
				status.setTextFill(Color.RED);
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
		stage.setTitle("Sign up");
		stage.show();
	}
	
}
