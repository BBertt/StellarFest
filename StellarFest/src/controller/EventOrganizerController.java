package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import database.Connect;
import model.User;
import util.Session;

public class EventOrganizerController {
	Connect connect = Connect.getInstance();
	
	// Function to add new events to the database.
	public void createEvent(String eventName, String date, String location, String description, String organizerID) {
		String query = "INSERT INTO events (user_id, user_email, user_name, user_password, user_role) VALUES (?,?,?,?,?)";
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
	
	// Function to check create new events input.
	public String checkCreateEventInput(String eventName, String date, String location, String description) {
		if (eventName == null || eventName.trim().isEmpty()) return "Event Name cannot be empty.";
		
		if (date == null || date.trim().isEmpty()) return "Date cannot be empty.";
		
		if (location == null || location.trim().isEmpty()) return "Location cannot be empty.";
		
		if (description == null || description.trim().isEmpty()) return "Description cannot be empty.";
		
		
		// To check that date must be in the future. (Using WIB.)
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZoneId wibZoneId = ZoneId.of("Asia/Jakarta"); // WIB time zone

        try {
            LocalDate inputLocalDate = LocalDate.parse(date, dateFormatter);
            LocalDate currentLocalDate = LocalDate.now(wibZoneId); // Get current date in WIB

            // If the date is not in the future
            if (!inputLocalDate.isAfter(currentLocalDate)) {
            	return "Date must be in the future.";
            }
        } catch (Exception e) {
        	// If the date format is not correct
            return "Invalid date format. Please use yyyy-MM-dd.";
        }
		
		if (location.length() < 5) return "Location must be at least 5 characters.";
		
		if (description.length() > 200) return "Desription cannot be more than 200 characters.";
		
		return null;
	}
}
