package dao;

import java.sql.SQLException;
import model.User;

public interface VIPUserDao{
	void setup() throws SQLException;
	User createVIPUser(String username, String password, String firstName, String lastName) throws SQLException;
	User getVIPUser(String username, String password) throws SQLException;
	void editProfile(User user, String currentUsername) throws SQLException;
}
