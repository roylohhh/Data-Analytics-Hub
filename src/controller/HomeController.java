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
import model.User;

public class HomeController {
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
	@FXML
	private Label DisplayVIP;
	@FXML
	private Button upgradeToVIP;
	
	public HomeController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	public void initialize() {
		//Display welcome message
		currentUser.setText("Welcome " + model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());
		//Ask user whether they want to be a VIP
		
		DisplayVIP.setText("Would you like to subscribe to the application for a monthly fee of $0?");
		
		//upgrade to VIP
		upgradeToVIP.setOnAction(event ->{
			User user = model.getCurrentUser();
			try {
				model.getVIPUserDao().createVIPUser(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
			} catch (SQLException e) {
				DisplayVIP.setText(e.getMessage());
				DisplayVIP.setText("Please log out and log in again to access VIP functionalities.");
			}
		});
		
		
		
		
		//edit profile
		editProfile.setOnAction(event -> handleEditProfile());
		//add posts
		addPosts.setOnAction(event -> handleAddPosts());
		//retrieve posts
		retrievePosts.setOnAction(event -> handleRetrievePosts());
		//remove post
		removePosts.setOnAction(event -> handleDeletePosts());
		//TODO: retrieve top N posts with most likes
		
		//Export post to file based on post ID
		exportPostByID.setOnAction(event ->handleExportPost());
		
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
	
	//TODO: Retrieve top posts by likes
	
	//Export post by id
	private void handleExportPost() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ExportPostView.fxml"));
			ExportPostController exportPostController = new ExportPostController(stage, model);
			loader.setController(exportPostController);
			VBox root = loader.load();
			
			exportPostController.showStage(root);
			
		} catch(IOException e) {
			e.getMessage();
		}	
	}
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Homepage");
		stage.show();
	}
}





