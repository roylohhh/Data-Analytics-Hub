package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

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

public class ExportPostController {
	@FXML
	private TextField id;
	@FXML
	private Button exportPost;
	@FXML
	private Button close;
	@FXML
	private Label message;
	
	private Model model;
	private Stage stage;
	private Stage parentStage;
	
	public ExportPostController(Stage parentStage, Model model) {
		this.stage = new Stage();
		this.parentStage = parentStage;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		exportPost.setOnAction(event ->{
			if(!id.getText().isEmpty()) { //if id is input proceed
				try { //id parsed into integer
					int ID = Integer.parseInt(id.getText());
					Post post = model.getPostDao().getPost(ID);
					if(post != null) { //use bufferedwriter to write post into post.csv
						try(BufferedWriter bufw = new BufferedWriter(new FileWriter("post.csv"))){
							bufw.write("ID,content,author,likes,shares,date-time");
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); //dateTimeformatter used 
							bufw.newLine();
							bufw.write(post.getID() + "," + post.getContent() + "," + post.getAuthor() + "," + post.getLikes() + "," + post.getShares() + "," + formatter.format(post.getPostDateTime()));
							bufw.newLine();	
							message.setText("Post successfully exported");
							message.setTextFill(Color.GREEN);
						} catch(IOException e) {
							e.printStackTrace();
						}						
					} else { //no post with inputted id found
						message.setText("Post not found");
						message.setTextFill(Color.RED);
					}
				} catch (NumberFormatException e) { //if integer is not input
					message.setText("Invalid ID");
					message.setTextFill(Color.RED);
				} catch(SQLException e) {
					message.setText(e.getMessage());
					message.setTextFill(Color.RED);
				}
			} else { //nothing is input
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
		stage.setResizable(true);
		stage.setTitle("Retrieve Post");
		stage.show();
	}
}
