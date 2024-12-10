package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.EventOrganizer;
import model.Guest;
import model.User;
import model.Vendor;
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
		String user_id = null, user_email = null, user_name = null, user_password = null, user_role = null;
		User user = null;
		
		String query = "SELECT * FROM users WHERE user_email = ?";
		PreparedStatement ps1 = connect.prepareStatement(query);
		try {
			ps1.setString(1, email);
			connect.rs = ps1.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (connect.rs.next()) {
				user_id = connect.rs.getString("user_id");
	            user_email = connect.rs.getString("user_email");
	            user_name = connect.rs.getString("user_name");
	            user_password = connect.rs.getString("user_password");
	            user_role = connect.rs.getString("user_role");
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// If the user is admin, then only return the user's attributes.
        if (user_role.equals("Admin")) {
        	return new User(user_id, user_email, user_name, user_password, user_role);
        }
        // If the user is Event Organizer, then return the user's attributes and eventorganizers attributes.
        else if (user_role.equals("Event Organizer")) {
        	query = "SELECT * FROM eventorganizers WHERE user_id = ?";
        	PreparedStatement ps2 = connect.prepareStatement(query);
        	try {
				ps2.setString(1, user_id);
				connect.rs = ps2.executeQuery();
				
				ArrayList<String> events_created = new ArrayList<String>();
	        	while (connect.rs.next()) {
	        		events_created.add(connect.rs.getString("events_created"));
	        	}
	        	user = new EventOrganizer(user_id, user_email, user_name, user_password, user_role, events_created);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        // If the user is Vendor, then return the user's attributes and Vendor attributes.
        else if (user_role.equals("Vendor")) {
        	query = "SELECT * FROM vendors WHERE user_id = ?";
        	PreparedStatement ps3 = connect.prepareStatement(query);
        	try {
				ps3.setString(1, user_id);
				connect.rs = ps3.executeQuery();
				
	        	ArrayList<String> accepted_invitations = new ArrayList<String>();
	        	while (connect.rs.next()) {
	        		accepted_invitations.add(connect.rs.getString("accepted_invitations"));
	        	}
	        	user = new Vendor(user_id, user_email, user_name, user_password, user_role, accepted_invitations);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        // If the user is Guest, then return the user's attributes and Guest attributes.
        else if (user_role.equals("Guest")) {
        	query = "SELECT * FROM guests WHERE user_id = ?";
       
        	try (PreparedStatement ps = connect.prepareStatement(query)){
				ps.setString(1, user_id);
				connect.rs = ps.executeQuery();
				
	        	ArrayList<String> accepted_invitations = new ArrayList<String>();
	        	while (connect.rs.next()) {
	        		accepted_invitations.add(connect.rs.getString("accepted_invitations"));
	        	}
	        	user = new Guest(user_id, user_email, user_name, user_password, user_role, accepted_invitations);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
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
	
	// Function to change the user's details. (happens after checkRegisterInput.) (oldPassword is not needed.)
	public void changeProfile (String email, String name, String newPassword) {
		String query;
		User user = Session.getUser();
		String user_id = user.getUser_id();
		
		if (email != null) {
			query = "UPDATE users SET user_email = ? WHERE user_id = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			
			try {
				ps.setString(1, email);
				ps.setString(2, user_id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// update the user session details
			user.setUser_email(email);
			Session.setUser(user);
		}
		if (name != null) {
			query = "UPDATE users SET user_name = ? WHERE user_id = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			
			try {
				ps.setString(1, name);
				ps.setString(2, user_id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// update the user session details
			user.setUser_name(name);
			Session.setUser(user);
		}
		if (newPassword != null) {
			query = "UPDATE users SET user_password = ? WHERE user_id = ?";
			PreparedStatement ps = connect.prepareStatement(query);
			
			try {
				ps.setString(1, newPassword);
				ps.setString(2, user_id);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// update the user session details
			user.setUser_password(newPassword);
			Session.setUser(user);
		}
		
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
		
		if (!users.isEmpty()) {
			for (User user : users) {
				if (user.getUser_email().equalsIgnoreCase(email)) emailUnique = false;
				if (user.getUser_name().equalsIgnoreCase(name)) nameUnique = false;
			}
		}
		
		if (emailUnique == false) return "email has already been taken.";
		if (nameUnique == false) return "name has already been taken.";
		
		// Validate for password to not be empty and have at least 5 characters.
		if (password == null || password.trim().isEmpty()) return "password cannot be empty!";
		
		if (password.length() < 5) return "password must be at least 5 characters!";
		
		// Validate that role has been chosen
		if (role == null) return "role cannot be empty!";
		
		// Getting the string id of the latest user and adding 1 for the next user.
		int user_id;
		if (users.isEmpty()) user_id = 1;
		else {
			user_id = Integer.parseInt(users.get(users.size()-1).getUser_id()) + 1;
		}
		
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
		ArrayList<User> users = getUsers();
		User user = Session.getUser();
		
		// To check that at least one field is changed.
		boolean isChanged = false;
		
		boolean emailUnique = true;
		boolean nameUnique = true;
		
		// Validate email to not be empty
		if (email == null || email.trim().isEmpty()) return "Email cannot be empty!";
		
		// Validate name to not be empty
		if (name == null || name.trim().isEmpty()) return "Name cannot be empty!";
		
		// Validate current password not to be empty
		if (oldPassword == null || oldPassword.trim().isEmpty()) return "Current Password cannot be empty.";
		
		// Validate current password to be valid
		if (!oldPassword.equals(user.getUser_password())) return "Current Password does not match.";
		
		// Change the user details for each fields that are different from current details.
		if (!(email == null || email.trim().isEmpty()) && !(email.equals(user.getUser_email()))) {
			for (User u : users) {
				if (u.getUser_email().equalsIgnoreCase(email)) {
					emailUnique = false; 
					break;
				}
			}
			
			if (emailUnique == false) return "Email has already been taken.";
			
			// update the user email details.
			changeProfile(email, null, null);
			isChanged = true;
		}
		if (!(name == null || name.trim().isEmpty()) && !(name.equals(user.getUser_name()))) {
			for (User u : users) {
				if (u.getUser_name().equalsIgnoreCase(name)) {
					nameUnique = false;
					break;
				}
			}
			
			if (nameUnique == false) return "Name has already been taken.";
			
			// update the user name details.
			changeProfile(null, name, null);
			isChanged = true;
		}
		if (!(newPassword == null || newPassword.trim().isEmpty())) {
			if (newPassword.length() < 5) return "New password must be at least 5 characters!";
			
			// update the user password details.
			if (!newPassword.equals(oldPassword)) {
				changeProfile(null, null, newPassword);
				isChanged = true;
			}
		}
		
		if (isChanged == false) return "Minimum of 1 field needs to be changed.";
		
		return null;
	}
}
