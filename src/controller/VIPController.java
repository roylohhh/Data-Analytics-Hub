package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;

public class VIPController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private Label currentUser; ///displays current user
	@FXML
	private MenuItem editProfile; // Corresponds to the Menu item "viewProfile" in HomeView.fxml
	@FXML
	private MenuItem logout; //logout
	@FXML
	private MenuItem updateProfile; // Corresponds to the Menu item "updateProfile" in HomeView.fxml
	@FXML
	private MenuItem addPosts; //Corresponds to the Menu item "addPosts" in HomeView.fxml
	@FXML
	private MenuItem retrievePosts; //Corresponds to the Menu item "retrievePosts" in HomeView.fxml
	@FXML
	private MenuItem removePosts; //Corresponds to the Menu item "deletePosts" in HomeView.fxml
	@FXML
	private MenuItem retrieveTopLikedPosts; //Corresponds to the Menu item "retrieveTopLikedPosts" in HomeView.fxml
	@FXML
	private MenuItem exportPostByID;//Corresponds to the Menu item "exportPostByID" in HomeView.fxml
	
	public VIPController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	public void initialize() {
		//Display welcome message
		currentUser.setText("Welcome VIP user " + model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());	
		
		//edit profile
		editProfile.setOnAction(event -> handleEditVIPProfile());
		//add post
		addPosts.setOnAction(event -> handleAddPosts());
		//retrieve posts
		retrievePosts.setOnAction(event -> handleRetrievePosts());
		//remove post
		removePosts.setOnAction(event -> handleDeletePosts());
		//TODO: retrieve top N posts with most likes
		
		//TODO: Export post to file based on post ID
		
		//logout
		logout.setOnAction(event ->{
			stage.close();
			parentStage.show();
		});
	}
	
	
	//Edit profile
	private void handleEditVIPProfile() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditProfileView.fxml"));
			EditVIPProfileController editVIPProfileController = new EditVIPProfileController(stage, model);
			loader.setController(editVIPProfileController);
			VBox root = loader.load();
			editVIPProfileController.showStage(root);
			
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
	
	//Delete Posts
	private void handleDeletePosts() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DeletePostView.fxml"));
			DeletePostController deletePostController = new DeletePostController(stage, model);
			loader.setController(deletePostController);
			VBox root = loader.load();
			
			deletePostController.showStage(root);
			
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





