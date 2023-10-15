package controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

public class HomeController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem editProfile; // // Corresponds to the Menu item "editProfile" in HomeView.fxml
	@FXML 
	private MenuItem addPost; // Corresponds to the Menu Item "addPost" in HomeView.fxml
	@FXML 
	private MenuItem retrievePost; // Corresponds to the Menu Item "retrievePost" in HomeView.fxml
	@FXML 
	private MenuItem deletPost; //// Corresponds to the Menu Item "deletePost" in HomeView.fxml
	
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	
	
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
	}
}

