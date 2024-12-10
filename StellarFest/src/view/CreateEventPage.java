package view;

import controller.EventOrganizerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.Session;

public class CreateEventPage implements EventHandler<ActionEvent>{
	public Scene scene;
	private VBox root;
	
	private Label nameLabel;
	private Label dateLabel;
	private Label locationLabel;
	private Label descLabel;
	private TextField nameField;
	private TextField dateField;
	private TextField locationField;
	private TextField descField;
	
	private HBox nameBox;
	private HBox dateBox;
	private HBox locationBox;
	private HBox descBox;
	
	private Label errorLabel;
	
	private Button submitBtn;
	
	private EventOrganizerController eoc;
	
	private void init() {
		root = new VBox(10);
		
		nameLabel = new Label("Name:");
		dateLabel = new Label("Date:");
		locationLabel = new Label("Location:");
		descLabel = new Label("Description:");
		nameField = new TextField();
		dateField = new TextField();
		locationField = new TextField();
		descField = new TextField();
		
		nameBox = new HBox(10);
		dateBox = new HBox(10);
		locationBox = new HBox(10);
		descBox = new HBox(10);
		
		errorLabel = new Label("");
		
		submitBtn = new Button("Submit");
		
		scene = new Scene(root, 1000, 700);
		
		eoc = new EventOrganizerController();
	}
	
	private void setPosition() {
		root.setAlignment(Pos.CENTER);
		
		nameBox.getChildren().addAll(nameLabel, nameField);
		dateBox.getChildren().addAll(dateLabel, dateField);
		locationBox.getChildren().addAll(locationLabel, locationField);
		descBox.getChildren().addAll(descLabel, descField);
		
		nameBox.setAlignment(Pos.CENTER);
		dateBox.setAlignment(Pos.CENTER);
		locationBox.setAlignment(Pos.CENTER);
		descBox.setAlignment(Pos.CENTER);
		
		root.getChildren().addAll(nameBox, dateBox, locationBox, descBox, submitBtn);
	}
	
	private void setStyle() {
		errorLabel.setTextFill(Color.RED);
	}
	
	private void events() {
		submitBtn.setOnAction(e -> handle(e));
	}
	public CreateEventPage() {
		init();
		setPosition();
		setStyle();
		events();
		
		Main.redirect(scene);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			String error = eoc.checkCreateEventInput(nameField.getText(), dateField.getText(), locationField.getText(), descField.getText());
			
			if (error != null) {
                errorLabel.setText(error);
                if (!root.getChildren().contains(errorLabel)) {
                	root.getChildren().add(4, errorLabel);
                }
            } 
            else {
                if (root.getChildren().contains(errorLabel)) {
                    root.getChildren().remove(errorLabel);
                }
            }
		}
		
	}

}
