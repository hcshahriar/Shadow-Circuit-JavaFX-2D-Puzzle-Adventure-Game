package shadowcircuit.game;

import javafx.scene.canvas.GraphicsContext;
import shadowcircuit.entities.SecurityBot;
import shadowcircuit.utils.Vector2D;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private Vector2D playerStart;
    private Vector2D exitPosition;
    private boolean[][] collisionMap;
    private List<PuzzleTerminal> terminals = new ArrayList<>();
    private List<SecurityBot> bots = new ArrayList<>();
    
    public Level(Vector2D playerStart, Vector2D exitPosition, boolean[][] collisionMap) {
        this.playerStart = playerStart;
        this.exitPosition = exitPosition;
        this.collisionMap = collisionMap;
    }
    
    public void addTerminal(PuzzleTerminal terminal) {
        terminals.add(terminal);
    }
    
    public void addBot(SecurityBot bot) {
        bots.add(bot);
    }
    
    public boolean allTerminalsSolved() {
        return terminals.stream().allMatch(PuzzleTerminal::isSolved);
    }
    
    public void render(GraphicsContext gc) {
        
        for (int y = 0; y < collisionMap.length; y++) {
            for (int x = 0; x < collisionMap[0].length; x++) {
                if (collisionMap[y][x]) {
                    gc.setFill(Color.DARKBLUE);
                    gc.fillRect(x * GameConstants.TILE_SIZE, y * GameConstants.TILE_SIZE, 
                              GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
                } else {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x * GameConstants.TILE_SIZE, y * GameConstants.TILE_SIZE, 
                              GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
                }
            }
        }
        
      
        gc.setFill(Color.GREEN);
        gc.fillOval(exitPosition.x - 15, exitPosition.y - 15, 30, 30);
        
        
        terminals.forEach(t -> t.render(gc));
        
       
        bots.forEach(b -> b.render(gc));
    }
    
   
    public Vector2D getPlayerStartPosition() { return playerStart; }
    public Vector2D getExitPosition() { return exitPosition; }
    public boolean[][] getCollisionMap() { return collisionMap; }
    public List<PuzzleTerminal> getTerminals() { return terminals; }
    public List<SecurityBot> getBots() { return bots; }
}
