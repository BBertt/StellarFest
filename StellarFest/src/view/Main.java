package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;
    
    public static void redirect(Scene scene) {
    	primaryStage.setScene(scene);
    	primaryStage.centerOnScreen();
    	primaryStage.setResizable(false);
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("StellarFest");
        new RegisterPage();
    }
}