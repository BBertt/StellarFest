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
	
	// Function to add new events to the database. (akan memanggil createEvent() dari EventController).
	public void createEvent(String eventName, String date, String location, String description, String organizerID) {
		EventController ec = new EventController();
		ec.createEvent(eventName, date, location, description, organizerID);
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
		
		createEvent(eventName, date, location, description, Session.getUser().getUser_id());
		
		return null;
	}
}
