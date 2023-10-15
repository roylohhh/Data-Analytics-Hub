package model;

public class Post {
	private int ID;
	private String content;
	private String author;
	private int likes;
	private int shares;
	
	//constructor
	public Post(int ID, String content, String author, int likes, int shares) {
		this.ID = ID;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
	}
	
	public Post() {
		
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
}