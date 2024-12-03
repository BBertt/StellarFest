package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	
//	Connect connect = Connect.getInstance();
	Scene scene;
	BorderPane borderPane;
	Button signInBtn;
	Button registerBtn;
	VBox centerContent;
	Stage primaryStage;
	
	public void init() {
		borderPane = new BorderPane();
		scene = new Scene(borderPane, 700, 700);
		centerContent = new VBox(10);
		
		signInBtn = new Button("Sign In");
		registerBtn = new Button("Register");
	}
	
	public void initComponent() {
		centerContent.getChildren().addAll(signInBtn, registerBtn);
		borderPane.setCenter(centerContent);
	}
	
	public void setEventHandling() {
		signInBtn.setOnAction(e -> showSignInPage());
        registerBtn.setOnAction(e -> showRegisterPage());
	}
	
	private void showSignInPage() {
        VBox signInPane = new VBox(10);
        Scene signInScene = new Scene(signInPane, 700, 700);

        // Create Sign In components
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signIn = new Button("Sign In");
        Button backToMain = new Button("Back");

        // Add components to the sign-in layout
        signInPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, signIn, backToMain);

        // Event handling for Sign In
        signIn.setOnAction(e -> {
            // Add your sign-in logic here
            System.out.println("Signing in with: " + usernameField.getText());
        });

        // Event handling for Back
        backToMain.setOnAction(e -> primaryStage.setScene(scene));

        primaryStage.setScene(signInScene);
    }

    private void showRegisterPage() {
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
        Button backToMain = new Button("Back");

        // Add components to the register layout
        registerPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, register, backToMain);

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
        backToMain.setOnAction(e -> primaryStage.setScene(scene));

        primaryStage.setScene(registerScene);
    }

	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		init();
		initComponent();
		setEventHandling();
		primaryStage.setScene(scene);
		primaryStage.setTitle("StellarFest");
		primaryStage.show();
	}
	
	public static void main (String[] args) {
		launch(args);
	}

}
