package shadowcircuit.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shadowcircuit.common.AudioManager;
import shadowcircuit.common.GameConstants;
import shadowcircuit.game.Puzzle;

public class PuzzleUI {
    private Stage dialog;
    private Puzzle puzzle;
    private Runnable onSuccess;
    
    public PuzzleUI(Puzzle puzzle, Runnable onSuccess) {
        this.puzzle = puzzle;
        this.onSuccess = onSuccess;
        
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Security Terminal");
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text puzzleText = new Text(puzzle.getDescription());
        grid.add(puzzleText, 0, 0, 2, 1);
        
        
        Button solveButton = new Button("Solve");
        solveButton.setOnAction(e -> {
            if (puzzle.attemptSolution("")) { 
                AudioManager.playSound(GameConstants.SOUND_VICTORY);
                onSuccess.run();
                dialog.close();
            } else {
                AudioManager.playSound(GameConstants.SOUND_FAILURE);
            }
        });
        
        grid.add(solveButton, 0, 1);
        
        Scene scene = new Scene(grid, 400, 300);
        dialog.setScene(scene);
    }
    
    public void show() {
        dialog.show();
    }
}
