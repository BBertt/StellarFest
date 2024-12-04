package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    Scene scene;
    BorderPane borderPane;
    RegisterLogin registerLogin;

    public void init(Stage primaryStage) {
        borderPane = new BorderPane();
        scene = new Scene(borderPane, 700, 700);
        
        registerLogin = new RegisterLogin(primaryStage);
    }

    public void initComponent() {
    }

    public void setEventHandling() {
    }

    @Override
    public void start(Stage primaryStage) {
        init(primaryStage);
        initComponent();
        setEventHandling();
        primaryStage.setScene(scene);
        primaryStage.setTitle("StellarFest");
        primaryStage.show();
        registerLogin.showRegisterPage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}