package shadowcircuit.common;

public class GameState {
    private static int score = 0;
    private static int lives = 3;
    private static long levelStartTime;
    
    public static void resetLevelState() {
        levelStartTime = System.currentTimeMillis();
    }
    
    public static void addScore(int points) {
        score += points;
    }
    
    public static void playerCaught() {
        lives--;
    }
    
    public static int getLives() {
        return lives;
    }
    
    public static int getScore() {
        return score;
    }
    
    public static int getLevelTimeRemaining() {
        long elapsed = (System.currentTimeMillis() - levelStartTime) / 1000;
        return Math.max(0, 120 - (int)elapsed); // 2 minutes per level
    }
    
    public static void resetGame() {
        score = 0;
        lives = 3;
        resetLevelState();
    }
}
