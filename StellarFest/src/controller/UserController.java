package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.User;
import util.Session;

public class UserController {
	Connect connect = Connect.getInstance();
	
	// Function to get All users from the database. (Used to help checkRegisterInput.)
	public ArrayList<User> getUsers() {
		ArrayList<User> temp = new ArrayList<User>();
		String query = "SELECT * FROM users";
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			connect.rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (connect.rs.next()) {
				String user_id = connect.rs.getString("user_id");
				String user_email = connect.rs.getString("user_email");
				String user_name = connect.rs.getString("user_name");
				String user_password = connect.rs.getString("user_password");
				String user_role = connect.rs.getString("user_role");
				temp.add(new User(user_id, user_email, user_name, user_password, user_role));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	// Function to get a single user by Email (Used to help login.)
	public User getUserByEmail(String email) {
		User user = null;
		String query = "SELECT * FROM users WHERE user_email = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, email);
			connect.rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (connect.rs.next()) {
				String user_id = connect.rs.getString("user_id");
	            String user_email = connect.rs.getString("user_email");
	            String user_name = connect.rs.getString("user_name");
	            String user_password = connect.rs.getString("user_password");
	            String user_role = connect.rs.getString("user_role");
	            user = new User(user_id, user_email, user_name, user_password, user_role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	// Function to get a single user by User name
	public User getUserByUsername(String name) {
		User user = null;
		String query = "SELECT * FROM users WHERE user_name = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			connect.rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (connect.rs.next()) {
				String user_id = connect.rs.getString("user_id");
	            String user_email = connect.rs.getString("user_email");
	            String user_name = connect.rs.getString("user_name");
	            String user_password = connect.rs.getString("user_password");
	            String user_role = connect.rs.getString("user_role");
	            user = new User(user_id, user_email, user_name, user_password, user_role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	// Function to insert a new user to the database and create new user session. (id is also passed since id must be in string.)
	public void register (String user_id, String email, String name, String password, String role) {
		String query = "INSERT INTO users (user_id, user_email, user_name, user_password, user_role) VALUES (?,?,?,?,?)";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, user_id);
			ps.setString(2, email);
			ps.setString(3, name);
			ps.setString(4, password);
			ps.setString(5, role);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Session.setUser(new User(user_id, email, name, password, role));
	}
	
	// Function to validate register input. (role is also added so that it can be validated as well.)
	public String checkRegisterInput (String email, String name, String password, String role) {
		ArrayList<User> users = getUsers();
		
		// Validate email to not be empty
		if (email == null || email.trim().isEmpty()) return "email cannot be empty!";
		
		// Validate user name to not be empty
		if (name == null || name.trim().isEmpty()) return "username cannot be empty!";
		
		// Validate email and user name must be unique
		boolean emailUnique = true;
		boolean nameUnique = true;
		for (User user : users) {
			if (user.getUser_email().equalsIgnoreCase(email)) emailUnique = false;
			if (user.getUser_name().equalsIgnoreCase(name)) nameUnique = false;
		}
		
		if (emailUnique == false) return "email has already been taken.";
		if (nameUnique == false) return "name has already been taken.";
		
		// Validate for password to not be empty and have at least 5 characters.
		if (password == null || password.trim().isEmpty()) return "password cannot be empty!";
		
		if (password.length() < 5) return "password must be at least 5 characters!";
		
		// Validate that role has been chosen
		if (role == null) return "role cannot be empty!";
		
		// Getting the string id of the latest user and adding 1 for the next user.
		int user_id = Integer.parseInt(users.get(users.size()-1).getUser_id()) + 1;
		register(String.valueOf(user_id), email, name, password, role);
		
		return null;
	}
		
	// Function to create a user session and validate login credentials.
	public String login (String email, String password) {
		// Validate email to not be empty
		if (email == null || email.trim().isEmpty()) return "email cannot be empty!";
		
		// Validate for password to not be empty and have at least 5 characters.
		if (password == null || password.trim().isEmpty()) return "password cannot be empty!";
		if (password.length() < 5) return "password must be at least 5 characters!";
		
		// Validate if email exist or not.
		User user = getUserByEmail(email);
		if (user == null) return "email does not exist!";
		
		// Validate if password matches or not.
		if (!user.getUser_password().equals(password)) return "Wrong password.";
		
		// Create the user session.
		Session.setUser(user);
		
		return null;
	}
	
	// Function to change user's details.
	public String checkChangeProfileInput(String email, String name, String oldPassword, String newPassword) {
		
	}
}
