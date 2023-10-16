package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

public class HomeController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private MenuItem editProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem logout;
	@FXML
	private MenuItem updateProfile; // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private MenuItem addPosts; //Corresponds to the Menu item "addPosts" in HomeView.fxml
	@FXML
	private MenuItem retrievePosts; //Corresponds to the Menu item "retrievePosts" in HomeView.fxml
	@FXML
	private MenuItem removePosts;
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	public void initialize() {
		//edit profile
		editProfile.setOnAction(event -> handleEditProfile());
		//add posts
		addPosts.setOnAction(event -> handleAddPosts());
		//retrieve posts
		retrievePosts.setOnAction(event -> handleRetrievePosts());
		//remove post
		
		//retrieve top N posts with most likes
		
		//Export post to file based on post ID
		
		//logout
		logout.setOnAction(event ->{
			stage.close();
			parentStage.show();
		});
	}
	
	//Edit profile
	private void handleEditProfile() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
			EditProfileController editProfileController = new EditProfileController(stage, model);
			loader.setController(editProfileController);
			VBox root = loader.load();
			
			editProfileController.showStage(root);
			
		} catch(IOException e) {
			e.getMessage();
		}		
	}
	
	//Add posts
	private void handleAddPosts() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPostView.fxml"));
			AddPostController addPostController = new AddPostController(stage, model);
			loader.setController(addPostController);
			VBox root = loader.load();
			
			addPostController.showStage(root);
			
		} catch(IOException e) {
			e.getMessage();
		}
	}
	
	//Retrieve Posts
	private void handleRetrievePosts() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RetrievePostView.fxml"));
			RetrievePostController retrievePostController = new RetrievePostController(stage, model);
			loader.setController(retrievePostController);
			VBox root = loader.load();
			
			retrievePostController.showStage(root);
			
		} catch(IOException e) {
			e.getMessage();
		}		
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Home");
		stage.show();
	}
}





