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

public class LoginPage implements EventHandler<ActionEvent>{
	// Create a VBox for the login layout
    private VBox root;

    // Create Login components
    private Label title;
    private Label emailLabel;
    private TextField emailField;
    private Label passwordLabel;
    private PasswordField passwordField;
    private Label errorLabel;

    // Buttons
    private Button login;
    private Label registerLabel;
    private Button registerBtn;

    // Create Containers for the components
    private HBox emailBox;
    private HBox passwordBox;
    private HBox register;
    
    public Scene scene;
    
    private UserController uc;
    
    private void init() {
    	root = new VBox(10);
        
        title = new Label("Login");
        emailLabel = new Label("Email:");
        emailField = new TextField();
        passwordLabel = new Label("Password:");
        passwordField = new PasswordField();
        errorLabel = new Label();
        
        login = new Button("Login");
        registerLabel = new Label("Already have an account? ");
        registerBtn = new Button("Login");
     
        emailBox = new HBox(10);
        passwordBox = new HBox(10);
        register = new HBox(10);
        
        uc = new UserController();
        
        scene = new Scene(root, 1000, 700);
    }
    
    private void setPosition () {
    	root.setAlignment(Pos.CENTER);
    	
        emailBox.getChildren().addAll(emailLabel, emailField);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);
        register.getChildren().addAll(registerLabel, registerBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        register.setAlignment(Pos.CENTER);
        
        // Insert all components into the root component
        root.getChildren().addAll(title, emailBox, passwordBox, login, register);
    }
    
    private void setStyle () {
    	errorLabel.setTextFill(Color.RED);
    }
    
 // Event handling for Register's Validation
    private void events () {
    	login.setOnAction(e -> handle(e));
    	registerBtn.setOnAction(e -> handle(e));
    }
	
	public LoginPage() {
		init();
		setPosition();
		setStyle();
		events();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == login) {
			String error = uc.login(emailField.getText(), passwordField.getText());
            if (error != null) {
                errorLabel.setText(error);
                if (!root.getChildren().contains(errorLabel)) {
                	root.getChildren().add(3, errorLabel);
                }
            } 
            else {
            	Main.redirect(new ProfilePage().scene);
            }
		}
		else if (e.getSource() == registerBtn) {
			Main.redirect(new RegisterPage().scene);
		}
	}

}
