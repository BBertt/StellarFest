package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.User;
import util.Session;

public class UserProfile {
	private Stage primaryStage;

    public UserProfile(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void showProfilePage() {
    	// Create VBox for main pane of user profile.
    	VBox profilePane = new VBox(10);
    	profilePane.setAlignment(Pos.CENTER);
    	
    	// Create components for user profile.
    	Label title = new Label("User Profile");
    	Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label oldPasswordLabel = new Label("Old Password:");
        PasswordField oldPasswordField = new PasswordField();
        Label newPasswordLabel = new Label("New Password:");
        PasswordField newPasswordField = new PasswordField();
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        
        // Retrieve the current users details & Set the data for the fields
        User user = Session.getUser();
        emailField.setText(user.getUser_email());
        usernameField.setText(user.getUser_name());
        
        // Buttons
        Label submitLabel = new Label("Are you sure you want to change? ");
        Button submitBtn = new Button("Submit");
        
        // Create Containers for the components
        HBox emailBox = new HBox(10, emailLabel, emailField);
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);
        HBox oldPasswordBox = new HBox(10, oldPasswordLabel, oldPasswordField);
        HBox newPasswordBox = new HBox(10, newPasswordLabel, newPasswordField);
        HBox submit = new HBox(10, submitLabel, submitBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        usernameBox.setAlignment(Pos.CENTER);
        oldPasswordBox.setAlignment(Pos.CENTER);
        newPasswordBox.setAlignment(Pos.CENTER);
        submit.setAlignment(Pos.CENTER);
        
        // Insert all components into the main pane
        profilePane.getChildren().addAll(title, emailBox, usernameBox, oldPasswordBox, newPasswordBox, submit);

        // Create a scene for the register components
        Scene profileScene = new Scene(profilePane, 1000, 700);
        
        // Event handling when Submit Button is pressed.
        submitBtn.setOnAction(e -> {
        	String error;
        	
        	if (error != null) {
                errorLabel.setText(error);
                if (!profilePane.getChildren().contains(errorLabel)) {
                	profilePane.getChildren().add(5, errorLabel);
                }
            } 
            else {
                if (profilePane.getChildren().contains(errorLabel)) {
                	profilePane.getChildren().remove(errorLabel);
                }
            }
        });
       
        primaryStage.setScene(profileScene);
        
    }

}
