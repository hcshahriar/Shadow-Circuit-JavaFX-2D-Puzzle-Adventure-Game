 package shadowcircuit.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import shadowcircuit.common.GameConstants;
import shadowcircuit.utils.Vector2D;

import java.util.List;

public class SecurityBot extends Entity {
    private Image sprite;
    private Vector2D patrolStart;
    private Vector2D patrolEnd;
    private boolean movingToEnd = true;
    
    public SecurityBot(Vector2D position, Vector2D patrolStart, Vector2D patrolEnd) {
        super(position);
        this.patrolStart = patrolStart;
        this.patrolEnd = patrolEnd;
        this.sprite = new Image(GameConstants.SPRITE_BOT);
    }
    
    public void update(boolean[][] collisionMap, Vector2D playerPosition) {
        
        Vector2D target = movingToEnd ? patrolEnd : patrolStart;
        Vector2D direction = target.subtract(position).normalize();
        
        if (position.distanceTo(target) < 5) {
            movingToEnd = !movingToEnd;
        } else {
            move(direction.multiply(GameConstants.BOT_SPEED), collisionMap);
        }
    }
    
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(sprite, position.x - 16, position.y - 16, 32, 32);
    }
}
