package model;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import dao.PostDao;
import dao.PostDaoImpl;

public class Model {
	private UserDao userDao;
	private User currentUser;
	private PostDao postDao;
	
	public Model() {
		userDao = new UserDaoImpl();
		postDao = new PostDaoImpl();
	}
	
	public void setup() throws SQLException{
		userDao.setup();
	}
	
	//User Dao
	public UserDao getUserDao() {
		return userDao;
	}
	
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}
	
	//Post Dao
	public PostDao getPostDao() {
		return postDao;
	}
	

	
	
}