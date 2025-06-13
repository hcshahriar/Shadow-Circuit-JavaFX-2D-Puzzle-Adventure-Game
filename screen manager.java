package shadowcircuit.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import shadowcircuit.GameApp;

public class ScreenManager {
    private Stage primaryStage;
    private Scene mainMenu;
    private Scene gameScreen;
    
    public ScreenManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void setMainMenu() {
        if (mainMenu == null) {
            mainMenu = new MainMenu(this).getScene();
        }
        primaryStage.setScene(mainMenu);
    }
    
    public void startGame(int startingLevel) {
        GameScreen game = new GameScreen(this, startingLevel);
        gameScreen = game.getScene();
        primaryStage.setScene(gameScreen);
        game.startGame();
    }
    
    public void returnToMenu() {
        primaryStage.setScene(mainMenu);
    }
    
    public void resize(int width, int height) {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
    }
}
