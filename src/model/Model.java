package model;

import java.sql.SQLException;

import dao.UserDao;
import dao.UserDaoImpl;
import dao.VIPUserDao;
import dao.VIPUserDaoImpl;
import dao.PostDao;
import dao.PostDaoImpl;

public class Model {
	private UserDao userDao;
	private User currentUser;
	private PostDao postDao;
	private VIPUserDao vipUserDao;
	
	public Model() {
		userDao = new UserDaoImpl();
		postDao = new PostDaoImpl();
		vipUserDao = new VIPUserDaoImpl();
	}
	
	public void setup() throws SQLException{
		userDao.setup();
		postDao.setup();
		vipUserDao.setup();
	}
	
	//User Dao
	public UserDao getUserDao() {
		return userDao;
	}
	
	//Current user
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
	
	//VIP user DAO
	public VIPUserDao getVIPUserDao() {
		return vipUserDao;
	}
	

	
	
}