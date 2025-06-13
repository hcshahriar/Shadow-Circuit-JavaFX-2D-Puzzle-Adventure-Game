 package shadowcircuit;

import javafx.application.Application;
import javafx.stage.Stage;
import shadowcircuit.ui.ScreenManager;

public class GameApp extends Application {
    public static final String TITLE = "Shadow Circuit";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        
        ScreenManager screenManager = new ScreenManager(primaryStage);
        screenManager.setMainMenu();
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
