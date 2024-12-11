package view;

import controller.UserController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RegisterPage implements EventHandler<ActionEvent>{
	// Create a VBox for the register layout
    private VBox root;

    // Create Register components
    private Label title;
    private Label emailLabel;
    private TextField emailField;
    private Label usernameLabel;
    private TextField usernameField;
    private Label passwordLabel;
    private PasswordField passwordField;
    private Label errorLabel;
    
    // Create a ComboBox for user roles
    private Label roleLabel;
    private ComboBox<String> roleComboBox;
    
    // Buttons
    private Button register;
    private Label loginLabel;
    private Button loginBtn;

    // Create Containers for the components
    private HBox emailBox;
    private HBox usernameBox;
    private HBox passwordBox;
    private HBox roleBox;
    private HBox login;
    
    public Scene scene;
    
    private UserController uc;
    
    private void init() {
    	root = new VBox(10);
        
        title = new Label("Register");
        emailLabel = new Label("Email:");
        emailField = new TextField();
        usernameLabel = new Label("Username:");
        usernameField = new TextField();
        passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        errorLabel = new Label();
        
        roleLabel = new Label("Select Role:");
        roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Event Organizer", "Vendor", "Guest");
        
        register = new Button("Register");
        loginLabel = new Label("Already have an account? ");
        loginBtn = new Button("Login");
     
        emailBox = new HBox(10);
        usernameBox = new HBox(10);
        passwordBox = new HBox(10);
        roleBox = new HBox(10);
        login = new HBox(10);
        
        uc = new UserController();
        
        scene = new Scene(root, 1000, 700);
    }
    
    private void setPosition () {
    	root.setAlignment(Pos.CENTER);
    	
        emailBox.getChildren().addAll(emailLabel, emailField);
        usernameBox.getChildren().addAll(usernameLabel, usernameField);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        roleBox.getChildren().addAll(roleLabel, roleComboBox);
        login.getChildren().addAll(loginLabel, loginBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        usernameBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        roleBox.setAlignment(Pos.CENTER);
        login.setAlignment(Pos.CENTER);
        
        // Insert all components into the root component
        root.getChildren().addAll(title, emailBox, usernameBox, passwordBox, roleBox, register, login);
    }
    
    private void setStyle () {
    	errorLabel.setTextFill(Color.RED);
    	title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
    }
    
 // Event handling for Register's Validation
    private void events () {
    	register.setOnAction(e -> handle(e));
    	loginBtn.setOnAction(e -> handle(e));
    }
	
	public RegisterPage () {
        init();
        setPosition();
        setStyle();
        events();
        Main.redirect(scene);
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == register) {
			String error = uc.checkRegisterInput(emailField.getText(), usernameField.getText(), passwordField.getText(), roleComboBox.getValue());
            if (error != null) {
                errorLabel.setText(error);
                if (!root.getChildren().contains(errorLabel)) {
                	root.getChildren().add(5, errorLabel);
                }
            } 
            else {
//                if (registerPane.getChildren().contains(errorLabel)) {
//                    registerPane.getChildren().remove(errorLabel);
//                }
            	new RegisterPage();
            }
		}
		else if (e.getSource() == loginBtn) {
			Main.redirect(new LoginPage().scene);
		}
	}
}
