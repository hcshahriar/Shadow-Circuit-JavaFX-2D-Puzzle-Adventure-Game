package shadowcircuit.common;

public class GameConstants {
    // Game settings
    public static final int TILE_SIZE = 32;
    public static final int PLAYER_SPEED = 3;
    public static final int BOT_SPEED = 2;
    
    // Scoring
    public static final int SCORE_PER_LEVEL = 1000;
    public static final int SCORE_PER_PUZZLE = 500;
    public static final int SCORE_TIME_BONUS = 10; 
    
    // Audio paths
    public static final String SOUND_MENU = "/sounds/menu_select.wav";
    public static final String SOUND_PUZZLE = "/sounds/puzzle_start.wav";
    public static final String SOUND_VICTORY = "/sounds/level_complete.wav";
    public static final String SOUND_FAILURE = "/sounds/game_over.wav";
    public static final String SOUND_MOVE = "/sounds/move.wav";
    
    // Sprite paths
    public static final String SPRITE_PLAYER = "/sprites/player.png";
    public static final String SPRITE_BOT = "/sprites/bot.png";
    public static final String SPRITE_TERMINAL = "/sprites/terminal.png";
}
