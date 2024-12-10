package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EventOrganizerPage implements EventHandler<ActionEvent>{
	public Scene scene;
	
	private VBox root;
	private HBox buttonsLayout;
	
	private Label title;
	
	private Button viewEvents;
	private Button addVendors;
	private Button addGuests;
	private Button editEventName;
	private Button createEvent;
	private Button logout;
	
	private void init() {
		root = new VBox(10);
		buttonsLayout = new HBox(10);
		
		title = new Label("Home");
		
		viewEvents = new Button("View Events");
		addVendors = new Button("Add Vendors");
		addGuests = new Button("Add Guests");
		editEventName = new Button("Edit Event Name");
		createEvent = new Button("Create Event");
		logout = new Button("Logout");
		
		scene = new Scene(root, 1000, 700);
	}
	
	private void setPosition() {
		root.setAlignment(Pos.CENTER);
		
		buttonsLayout.getChildren().addAll(viewEvents, addVendors, addGuests, editEventName, createEvent);
		buttonsLayout.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(title, buttonsLayout, logout);
	}
	
	private void setStyle() {
		
	}
	
	private void events() {
		viewEvents.setOnAction(e -> handle(e));
		addVendors.setOnAction(e -> handle(e));
		addGuests.setOnAction(e -> handle(e));
		editEventName.setOnAction(e -> handle(e));
		createEvent.setOnAction(e -> handle(e));
	}

	public EventOrganizerPage() {
		init();
		setPosition();
		setStyle();
		events();
		
		Main.redirect(scene);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == createEvent) {
			new CreateEventPage();
		}
	}

}
