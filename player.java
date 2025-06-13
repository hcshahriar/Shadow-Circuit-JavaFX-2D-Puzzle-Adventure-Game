package shadowcircuit.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import shadowcircuit.common.GameConstants;
import shadowcircuit.utils.Vector2D;

public class Player extends Entity {
    private Image sprite;
    
    public Player(Vector2D position) {
        super(position);
        this.sprite = new Image(GameConstants.SPRITE_PLAYER);
    }
    
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite, position.x - 16, position.y - 16, 32, 32);
    }
    
    public void respawn(Vector2D spawnPoint) {
        position = spawnPoint.copy();
    }
    
    public boolean isNear(Vector2D point, double distance) {
        return position.distanceTo(point) < distance;
    }
}
