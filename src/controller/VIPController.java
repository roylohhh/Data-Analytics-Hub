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
	private MenuItem editProfile; 
	@FXML
	private MenuItem logout; //logout
	@FXML
	private MenuItem updateProfile; 
	@FXML
	private MenuItem addPosts; 
	@FXML
	private MenuItem retrievePosts; 
	@FXML
	private MenuItem removePosts;
	@FXML
	private MenuItem retrieveTopLikedPosts; 
	@FXML
	private MenuItem exportPostByID;
	@FXML
	private MenuItem bulkImport; 
	
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
		//retrieve top N posts with most likes
		retrieveTopLikedPosts.setOnAction(event -> handleTopLikedPosts());
		//Export post to file based on post ID
		exportPostByID.setOnAction(event ->handleExportPost());	
		//Bulk export posts
		bulkImport.setOnAction(event -> handleBulkImport());
		
		//logout
		logout.setOnAction(event ->{
			stage.close();
			parentStage.show();
		});
	}
	
	
	//Edit VIP profile
	//shares the same fxml file as regular user, but different controller
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
	
	//Retrieve top posts by likes
	private void handleTopLikedPosts() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TopPostsView.fxml"));
			TopPostsController topPostsController = new TopPostsController(stage, model);
			loader.setController(topPostsController);
			VBox root = loader.load();
			
			topPostsController.showStage(root);
			
		} catch(IOException e) {
			e.getMessage();
		}
	}
	
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
	
	//Bulk export
	private void handleBulkImport() {
	//TODO
	}
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("VIP Homepage");
		stage.show();
	}
}





