package model;
import java.time.LocalDateTime;

public class Post {
	private int ID;
	private String content;
	private String author;
	private int likes;
	private int shares;
	private LocalDateTime postDateTime;
	
	//constructor
	public Post(int ID, String content, String author, int likes, int shares, LocalDateTime postDateTime) {
		this.ID = ID;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.postDateTime = postDateTime;
	}
	
	//receive the current dateTime
	public Post() {
		this.postDateTime = LocalDateTime.now();
	}
	
	//mutators performing common operations 
	
	public int getID() {
		return ID;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public int getLikes() {
		return likes;
	}
	
	public int getShares() {
		return shares;
	}
	
	public LocalDateTime getPostDateTime() {
		return postDateTime;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public void setShares(int shares) {
		this.shares = shares;
	}
	
	public void setPostDateTime(LocalDateTime postDateTime) {
		this.postDateTime = postDateTime;
	}

}