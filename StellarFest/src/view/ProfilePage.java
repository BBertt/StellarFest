package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.User;
import util.Session;

public class ProfilePage implements EventHandler<ActionEvent>{
	// Create a VBox for the profile layout
    private VBox root;

    // Create profile components
    private Label title;
    private Label emailLabel;
    private TextField emailField;
    private Label usernameLabel;
    private TextField usernameField;
    private Label oldPasswordLabel;
    private PasswordField oldPasswordField;
    private Label newPasswordLabel;
    private PasswordField newPasswordField;
    private Label errorLabel;
    
    // Buttons
    private Label submitLabel;
    private Button submitBtn;
    private Button homeBtn;

    // Create Containers for the components
    private HBox emailBox;
    private HBox usernameBox;
    private HBox oldPasswordBox;
    private HBox newPasswordBox;
    private HBox submit;
    
    public Scene scene;
    
    private UserController uc;
    
    private void init() {
    	root = new VBox(10);
        
        title = new Label("User Profile");
        emailLabel = new Label("Email:");
        emailField = new TextField();
        usernameLabel = new Label("Username:");
        usernameField = new TextField();
        oldPasswordLabel = new Label("Old Password:");
        oldPasswordField = new PasswordField();
        newPasswordLabel = new Label("New Password:");
        newPasswordField = new PasswordField();
        errorLabel = new Label();
        
        submitLabel = new Label("Are you sure you want to change? ");
        submitBtn = new Button("Submit");
        homeBtn = new Button("Home");
     
        emailBox = new HBox(10);
        usernameBox = new HBox(10);
        oldPasswordBox = new HBox(10);
        newPasswordBox = new HBox(10);
        submit = new HBox(10);
        
        uc = new UserController();
        
        scene = new Scene(root, 1000, 700);
    }
    
    private void setPosition () {
    	root.setAlignment(Pos.CENTER);
    	
        emailBox.getChildren().addAll(emailLabel, emailField);
        usernameBox.getChildren().addAll(usernameLabel, usernameField);
        oldPasswordBox.getChildren().addAll(oldPasswordLabel, oldPasswordField);
        newPasswordBox.getChildren().addAll(newPasswordLabel, newPasswordField);
        submit.getChildren().addAll(submitLabel, submitBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        usernameBox.setAlignment(Pos.CENTER);
        oldPasswordBox.setAlignment(Pos.CENTER);
        newPasswordBox.setAlignment(Pos.CENTER);
        submit.setAlignment(Pos.CENTER);
        
        // Insert all components into the root component
        root.getChildren().addAll(title, emailBox, usernameBox, oldPasswordBox, newPasswordBox, submit, homeBtn);
        
        // Retrieve the current users details & Set the data for the fields
        User user = Session.getUser();
        emailField.setText(user.getUser_email());
        usernameField.setText(user.getUser_name());
    }
    
    private void setStyle () {
    	errorLabel.setTextFill(Color.RED);
    	title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
    }
    
    private void events () {
    	submitBtn.setOnAction(e -> handle(e));
    	homeBtn.setOnAction(e -> handle(e));
    }

	public ProfilePage() {
		init();
		setPosition();
		setStyle();
		events();
		Main.redirect(scene);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == submitBtn) {
			String error = uc.checkChangeProfileInput(emailField.getText(), usernameField.getText(), oldPasswordField.getText(), newPasswordField.getText());
			
			if (error != null) {
                errorLabel.setText(error);
                if (!root.getChildren().contains(errorLabel)) {
                	root.getChildren().add(5, errorLabel);
                }
            } 
            else {
                if (root.getChildren().contains(errorLabel)) {
                    root.getChildren().remove(errorLabel);
                }
            }
		}
		else if (e.getSource() == homeBtn) {
			if (Session.getUser().getUser_role().equals("Event Organizer")) {
				new EventOrganizerPage();
			}
//			else if (Session.getUser().getUser_role().equals("Vendor")) {
//				new VendorPage();
//			}
//			else if (Session.getUser().getUser_role().equals("Guest")) {
//				new GuestPage();
//			}
		}
		
	}

}
