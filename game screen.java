package shadowcircuit.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import shadowcircuit.common.*;
import shadowcircuit.entities.*;
import shadowcircuit.game.*;
import shadowcircuit.utils.Vector2D;

import java.util.HashSet;
import java.util.Set;

public class GameScreen {
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    
    private Player player;
    private Level currentLevel;
    private Set<KeyCode> activeKeys = new HashSet<>();
    private ScreenManager screenManager;
    private int currentLevelNumber;
    
    public GameScreen(ScreenManager screenManager, int startingLevel) {
        this.screenManager = screenManager;
        this.currentLevelNumber = startingLevel;
        
        canvas = new Canvas(GameApp.WIDTH, GameApp.HEIGHT);
        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        scene = new Scene(root);
        
        setupInputHandlers();
    }
    
    public void startGame() {
        loadLevel(currentLevelNumber);
        
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }
    
    private void loadLevel(int levelNum) {
        currentLevel = LevelLoader.loadLevel(levelNum);
        player = new Player(currentLevel.getPlayerStartPosition());
        GameState.resetLevelState();
    }
    
    private void setupInputHandlers() {
        scene.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
            if (e.getCode() == KeyCode.ESCAPE) {
                screenManager.returnToMenu();
            }
        });
        
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));
    }
    
    private void update() {
       
        Vector2D movement = new Vector2D(0, 0);
        
        if (activeKeys.contains(KeyCode.UP) movement.y -= 1;
        if (activeKeys.contains(KeyCode.DOWN)) movement.y += 1;
        if (activeKeys.contains(KeyCode.LEFT)) movement.x -= 1;
        if (activeKeys.contains(KeyCode.RIGHT)) movement.x += 1;
        
        if (movement.magnitude() > 0) {
            movement.normalize().multiply(GameConstants.PLAYER_SPEED);
            player.move(movement, currentLevel.getCollisionMap());
        }
        
        
        currentLevel.getBots().forEach(bot -> {
            bot.update(currentLevel.getCollisionMap(), player.getPosition());
            
         
            if (bot.collidesWith(player)) {
                GameState.playerCaught();
                AudioManager.playSound(GameConstants.SOUND_FAILURE);
                if (GameState.getLives() <= 0) {
                    gameOver();
                } else {
                    player.respawn(currentLevel.getPlayerStartPosition());
                }
            }
        });
        
       
        if (activeKeys.contains(KeyCode.SPACE)) {
            currentLevel.getTerminals().forEach(terminal -> {
                if (player.isNear(terminal.getPosition(), 30)) {
                    AudioManager.playSound(GameConstants.SOUND_PUZZLE);
                    
                    PuzzleUI puzzleUI = new PuzzleUI(terminal.getPuzzle(), () -> {
                        terminal.setSolved(true);
                        GameState.addScore(GameConstants.SCORE_PER_PUZZLE);
                        if (currentLevel.allTerminalsSolved()) {
                            levelComplete();
                        }
                    });
                    puzzleUI.show();
                }
            });
            activeKeys.remove(KeyCode.SPACE);
        }
        
       
        if (player.isNear(currentLevel.getExitPosition(), 30) && currentLevel.allTerminalsSolved()) {
            levelComplete();
        }
    }
    
    private void render() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // Draw level
        currentLevel.render(gc);
        
        // Draw player
        player.render(gc);
        
        // Draw UI (score, lives, level)
        drawUI();
    }
    
    private void drawUI() {
        gc.setFill(Color.CYAN);
        gc.setFont(Font.font("Consolas", 20));
        gc.fillText("Level: " + currentLevelNumber, 20, 30);
        gc.fillText("Score: " + GameState.getScore(), 20, 60);
        gc.fillText("Lives: " + GameState.getLives(), 20, 90);
    }
    
    private void levelComplete() {
        GameState.addScore(GameConstants.SCORE_PER_LEVEL + 
                         (GameState.getLevelTimeRemaining() * GameConstants.SCORE_TIME_BONUS));
        AudioManager.playSound(GameConstants.SOUND_VICTORY);
        
        if (currentLevelNumber < LevelLoader.getTotalLevels()) {
            currentLevelNumber++;
            loadLevel(currentLevelNumber);
        } else {
            gameComplete();
        }
    }
    
    private void gameComplete() {
        gameLoop.stop();
        
    }
    
    private void gameOver() {
        gameLoop.stop();
        // Show game over screen
    }
    
    public Scene getScene() {
        return scene;
    }
}
