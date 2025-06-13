package shadowcircuit.game;

import javafx.scene.canvas.GraphicsContext;
import shadowcircuit.utils.Vector2D;

public class PuzzleTerminal {
    private Vector2D position;
    private Puzzle puzzle;
    private boolean solved;
    
    public PuzzleTerminal(Vector2D position, Puzzle puzzle) {
        this.position = position;
        this.puzzle = puzzle;
        this.solved = false;
    }
    
    public void render(GraphicsContext gc) {
        if (solved) {
            gc.setFill(Color.GREEN);
        } else {
            gc.setFill(Color.RED);
        }
        gc.fillRect(position.x - 15, position.y - 15, 30, 30);
        gc.setFill(Color.WHITE);
        gc.fillText("P", position.x - 5, position.y + 5);
    }
    
    
    public Vector2D getPosition() { return position; }
    public Puzzle getPuzzle() { return puzzle; }
    public boolean isSolved() { return solved; }
    public void setSolved(boolean solved) { this.solved = solved; }
}
