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
	
	public String validateRegister (String user_email, String user_name, String user_password, String user_role) {
		ArrayList<User> users = getUsers();
		for (User user : users) {
			System.out.println(user.getUser_id()+", "+user.getUser_email());
		}
		
		return null;
	}
}
