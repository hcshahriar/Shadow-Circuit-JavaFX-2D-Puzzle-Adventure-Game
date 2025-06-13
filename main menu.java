package shadowcircuit.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shadowcircuit.common.AudioManager;

public class MainMenu {
    private Scene scene;
    private VBox layout;
    
    public MainMenu(ScreenManager screenManager) {
        // Create UI elements
        ImageView logo = new ImageView(new Image(GameConstants.SPRITE_LOGO));
        logo.setFitWidth(400);
        logo.setPreserveRatio(true);
        
        Text title = new Text("Shadow Circuit");
        title.setFont(Font.font("Consolas", 48));
        title.setFill(Color.CYAN);
        
        Button startButton = createMenuButton("Start Game", () -> {
            AudioManager.playSound(GameConstants.SOUND_MENU);
            screenManager.startGame(1);
        });
        
        Button exitButton = createMenuButton("Exit", () -> {
            AudioManager.playSound(GameConstants.SOUND_MENU);
            System.exit(0);
        });
        
        
        layout = new VBox(20, logo, title, startButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #000000;");
        
        scene = new Scene(layout, GameApp.WIDTH, GameApp.HEIGHT);
    }
    
    private Button createMenuButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setFont(Font.font("Consolas", 24));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #222222; -fx-border-color: #00FFFF; -fx-border-width: 2;");
        button.setOnAction(e -> action.run());
        return button;
    }
    
    public Scene getScene() {
        return scene;
    }
}
