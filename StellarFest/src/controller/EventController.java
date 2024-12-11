package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;
import model.Event;
import model.User;

public class EventController {
	Connect connect = Connect.getInstance();
	
	// Function to get all the events (used to help with creating id & for ViewEventsPage's table).
	public ArrayList<Event> getEvents() {
		ArrayList<Event> temp = new ArrayList<Event>();
		String query = "SELECT * FROM events";
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			connect.rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (connect.rs.next()) {
				String event_id = connect.rs.getString("event_id");
				String event_name = connect.rs.getString("event_name");
				String event_date = connect.rs.getString("event_date");
				String event_location = connect.rs.getString("event_location");
				String event_description = connect.rs.getString("event_description");
				String organizer_id = connect.rs.getString("organizer_id");
				temp.add(new Event(event_id, event_name, event_date, event_location, event_description, organizer_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	// Function to create new Id.
	public String createID() {
		ArrayList<Event> events = getEvents();
		
		int id;
		if (events.isEmpty()) id = 1;
		else {
			id = Integer.parseInt(events.get(events.size()-1).getEvent_id()) + 1;
		}
		
		return String.valueOf(id);
	}

	// Function to add new Events to the database.
	public void createEvent(String eventName, String date, String location, String description, String organizerID) {
		String event_id = createID();
		String query = "INSERT INTO events (event_id, event_name, event_date, event_location, event_description, organizer_id)"
				+ " VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, event_id);
			ps.setString(2, eventName);
			ps.setString(3, date);
			ps.setString(4, location);
			ps.setString(5, description);
			ps.setString(6, organizerID);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
