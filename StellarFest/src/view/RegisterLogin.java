package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterLogin {
    private Stage primaryStage;

    public RegisterLogin(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
        VBox registerPane = new VBox(10);
        Scene registerScene = new Scene(registerPane, 700, 700);

        // Create Register components
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();
        Button register = new Button("Register");
        Button loginBtn = new Button("Login Instead");

        // Add components to the register layout
        registerPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, register, loginBtn);

        // Event handling for Register
        register.setOnAction(e -> {
            // Add your registration logic here
            if (passwordField.getText().equals(confirmPasswordField.getText())) {
                System.out.println("Registering user: " + usernameField.getText());
            } else {
                System.out.println("Passwords do not match.");
            }
        });

        // Event handling for Back
        loginBtn.setOnAction(e -> showSignInPage());

        primaryStage.setScene(registerScene);
    }
}