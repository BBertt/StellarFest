package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
		Button root = new Button();
		Scene scene = new Scene(root, 1280, 720);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello There");
		primaryStage.show();
	}
	
	public static void main (String[] args) {
		launch(args);
	}

}
