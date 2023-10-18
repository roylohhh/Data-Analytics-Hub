package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Model;
import model.Post;

public class VIPController {
	private Model model;
	private Stage stage;
	private Stage parentStage;
	@FXML
	private Label currentUser; ///displays current user
	@FXML
	private Label message;
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
	@FXML
	private PieChart pieChart;
	
	public VIPController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	// Add your code to complete the functionality of the program
	
	public void initialize() {
		//Display welcome message
		currentUser.setText("Welcome VIP user " + model.getCurrentUser().getFirstName() + " " + model.getCurrentUser().getLastName());	
		
		//TODO: Pie chart
		ObservableList<PieChart.Data>pieChartData = FXCollections.observableArrayList();
		try {
			List<Post> posts = model.getPostDao().getAllPosts();
			int shares0to99 = 0;
			int shares100to999 = 0;
			int sharesMoreThan1000 = 0;
			
			for(Post post : posts) {
				int shares = post.getShares();
				
				if(shares >=0 && shares <= 99) {
					shares0to99++;
				} else if(shares >=100 && shares <=999) {
					shares100to999++;
				} else if(shares >= 1000) {
					sharesMoreThan1000++;
				}
			}
			
		pieChartData.add(new PieChart.Data("#shares 0-99", shares0to99));
		pieChartData.add(new PieChart.Data("#shares 100-999", shares100to999));
		pieChartData.add(new PieChart.Data("#shares >1000", sharesMoreThan1000));
		
		pieChart.setData(pieChartData);
		pieChart.setTitle("Shares statistics");
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Functionalities
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
		try(BufferedReader bufr = new BufferedReader(new FileReader("posts.csv"))){
			String line;
			//bufr.readLine() will skip the header in posts.csv
			bufr.readLine(); 
			// while function to read lines that are not empty
			// all values retrieved will be in String format, hence parse is used to convert string values into right formats
			while ((line = bufr.readLine()) != null) {
				String[] values = line.split(",");
				int ID = Integer.parseInt(values[0]);
				String content = values[1];
				String author = values[2];
				int likes = Integer.parseInt(values[3]);
				int shares = Integer.parseInt(values[4]);
				
				try {
					Post post = model.getPostDao().createPost(ID, content, author, likes, shares);
						message.setText("Posts have been imported successfully");
						message.setTextFill(Color.GREEN);
				} catch(SQLException e) {
				/*
				 Basically if i were to bulk import 5 posts and 2 posts already exist, 
				 the catch message will be called but the 2 posts will be added regardless
				 Hence i decided to put two similar messages for better user experience
				 */
					message.setText("Posts have been imported successfully");
					message.setTextFill(Color.GREEN);
				}
			} 
		} catch (IOException e) {
			message.setText(e.getMessage());
		}
	}
	
	
	public void showStage(Pane root) {
		Scene scene = new Scene(root, 600, 500);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("VIP Homepage");
		stage.show();
	}
}





