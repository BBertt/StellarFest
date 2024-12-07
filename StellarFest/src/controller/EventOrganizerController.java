package controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import database.Connect;

public class EventOrganizerController {
	Connect connect = Connect.getInstance();
	
	// Function to create new events.
	public String createEvent(String eventName, String date, String location, String description, String organizerID) {
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
            return "Invalid date format. Please use yyyy-MM-dd.";
        }
		
		if (location.length() < 5) return "Location must be at least 5 characters.";
		
		if (description.length() > 200) return "Desription cannot be more than 200 characters.";
		
		return null;
	}
}
