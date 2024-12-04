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
import javafx.stage.Stage;

public class RegisterLogin {
    private Stage primaryStage;
    private UserController uc;

    public RegisterLogin(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.uc = new UserController();
    }

    public void showSignInPage() {
        VBox signInPane = new VBox(10);
        Scene signInScene = new Scene(signInPane, 700, 700);

        // Create Sign In components
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signIn = new Button("Sign In");
        Button registerBtn = new Button("Register Instead");

        // Add components to the sign-in layout
        signInPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, signIn, registerBtn);

        // Event handling for Sign In
        signIn.setOnAction(e -> {
            // Add your sign-in logic here
            System.out.println("Signing in with: " + usernameField.getText());
        });
        
        registerBtn.setOnAction(e-> showRegisterPage());

        primaryStage.setScene(signInScene);
    }

    public void showRegisterPage() {
    	// Create a VBox for the register layout
        VBox registerPane = new VBox(10);
        registerPane.setAlignment(Pos.CENTER);

        // Create Register components
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        
        // Create a ComboBox for user roles
        Label roleLabel = new Label("Select Role:");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Event Organizer", "Vendor", "Guest");
        
        Button register = new Button("Register");
        Button loginBtn = new Button("Login Instead");

        // Add components to the register layout
        HBox emailBox = new HBox(10, emailLabel, emailField);
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        HBox roleBox = new HBox(10, roleLabel, roleComboBox);
        HBox btns = new HBox(10, register, loginBtn);
        
        // Center all the components
        emailBox.setAlignment(Pos.CENTER);
        usernameBox.setAlignment(Pos.CENTER);
        passwordBox.setAlignment(Pos.CENTER);
        roleBox.setAlignment(Pos.CENTER);
        btns.setAlignment(Pos.CENTER);
        
        // Insert all components into the main pane
        registerPane.getChildren().addAll(emailBox, usernameBox, passwordBox, roleBox, btns);

        // Create a scene for the register components
        Scene registerScene = new Scene(registerPane, 1000, 700);

        // Event handling for Register
        register.setOnAction(e -> {
            String error = uc.validateRegister(emailField.getText(), usernameField.getText(), passwordField.getText(), roleComboBox.getValue());
            if (error != null) {
            	System.out.println(error);
            }
        });

        // Event handling for Back
        loginBtn.setOnAction(e -> showSignInPage());

        primaryStage.setScene(registerScene);
    }
}