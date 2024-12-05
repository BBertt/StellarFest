package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.User;

public class UserController {
	Connect connect = Connect.getInstance();
	
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
	
	public void insertUser(ArrayList<User> users, String user_email, String user_name, String user_password, String user_role) {
		String query = "INSERT INTO users (user_email, user_name, user_password, user_role) VALUES (?,?,?,?)";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, user_email);
			ps.setString(2, user_name);
			ps.setString(3, user_password);
			ps.setString(4, user_role);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String registerUser (String user_email, String user_name, String user_password, String user_role) {
		ArrayList<User> users = getUsers();
		
		// Validate email to not be empty
		if (user_email == null || user_email.trim().isEmpty()) return "email cannot be empty!";
		
		// Validate user name to not be empty
		if (user_name == null || user_name.trim().isEmpty()) return "username cannot be empty!";
		
		// Validate email and user name must be unique
		boolean emailUnique = true;
		boolean nameUnique = true;
		for (User user : users) {
			if (user.getUser_email().equalsIgnoreCase(user_email)) emailUnique = false;
			if (user.getUser_name().equalsIgnoreCase(user_name)) nameUnique = false;
		}
		
		if (emailUnique == false) return "email has already been taken.";
		if (nameUnique == false) return "name has already been taken.";
		
		// Validate for password to not be empty and have at least 5 characters.
		if (user_password == null || user_password.trim().isEmpty()) return "password cannot be empty!";
		
		if (user_password.length() < 5) return "password must be at least 5 characters!";
		
		// Validate that role has been chosen
		if (user_role == null) return "role cannot be empty!";
		
		insertUser(users, user_email, user_name, user_password, user_role);
		return null;
	}
}
