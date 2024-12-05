package view;

import controller.UserController;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import util.Session;

public class RegisterLogin {
    private Stage primaryStage;
    private UserController uc;

    public RegisterLogin(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.uc = new UserController();
    }

    public void showSignInPage() {
    	// Create a VBox for the sign in layout
        VBox loginPane = new VBox(10);
        loginPane.setAlignment(Pos.CENTER);

        // Create Sign in components
        Label title = new Label("Sign In");
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
     
        // Buttons
        Button login = new Button("login");
        Label registerLabel = new Label("Don't have an account? ");
        Button registerBtn = new Button("Register");

        // Create Containers for the components
        HBox emailBox = new HBox(10, emailLabel, emailField);
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        HBox register = new HBox(10, registerLabel, registerBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        register.setAlignment(Pos.CENTER);
        
        // Insert all components into the main pane
        loginPane.getChildren().addAll(title, emailBox, passwordBox, login, register);

        // Create a scene for the register components
        Scene loginScene = new Scene(loginPane, 1000, 700);

        // Event handling for Register's Validation
        login.setOnAction(e -> {
            String error = uc.login(emailField.getText(), passwordField.getText());
            if (error != null) {
                errorLabel.setText(error);
                if (!loginPane.getChildren().contains(errorLabel)) {
                	loginPane.getChildren().add(3, errorLabel);
                }
            } 
            else {
                UserProfile up = new UserProfile(primaryStage);
                up.showProfilePage();
            }
        });

        // Event handling for Back
        registerBtn.setOnAction(e -> showRegisterPage());

        primaryStage.setScene(loginScene);
    }

    public void showRegisterPage() {
    	// Create a VBox for the register layout
        VBox registerPane = new VBox(10);
        registerPane.setAlignment(Pos.CENTER);

        // Create Register components
        Label title = new Label("Register");
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        
        // Create a ComboBox for user roles
        Label roleLabel = new Label("Select Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Event Organizer", "Vendor", "Guest");
        
        // Buttons
        Button register = new Button("Register");
        Label loginLabel = new Label("Already have an account? ");
        Button loginBtn = new Button("Login");

        // Create Containers for the components
        HBox emailBox = new HBox(10, emailLabel, emailField);
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        HBox roleBox = new HBox(10, roleLabel, roleComboBox);
        HBox login = new HBox(10, loginLabel, loginBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        usernameBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        roleBox.setAlignment(Pos.CENTER);
        login.setAlignment(Pos.CENTER);
        
        // Insert all components into the main pane
        registerPane.getChildren().addAll(title, emailBox, usernameBox, passwordBox, roleBox, register, login);

        // Create a scene for the register components
        Scene registerScene = new Scene(registerPane, 1000, 700);

        // Event handling for Register's Validation
        register.setOnAction(e -> {
            String error = uc.checkRegisterInput(emailField.getText(), usernameField.getText(), passwordField.getText(), roleComboBox.getValue());
            if (error != null) {
                errorLabel.setText(error);
                if (!registerPane.getChildren().contains(errorLabel)) {
                	registerPane.getChildren().add(5, errorLabel);
                }
            } 
            else {
//                if (registerPane.getChildren().contains(errorLabel)) {
//                    registerPane.getChildren().remove(errorLabel);
//                }
            	showSignInPage();
            }
        });

        // Event handling for Back
        loginBtn.setOnAction(e -> showSignInPage());

        primaryStage.setScene(registerScene);
    }
}