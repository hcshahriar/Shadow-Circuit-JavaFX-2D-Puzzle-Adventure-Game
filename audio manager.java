package shadowcircuit.common;

import javafx.scene.media.AudioClip;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static Map<String, AudioClip> sounds = new HashMap<>();
    
    public static void preloadSounds() {
        loadSound(GameConstants.SOUND_MENU);
        loadSound(GameConstants.SOUND_PUZZLE);
        loadSound(GameConstants.SOUND_VICTORY);
        loadSound(GameConstants.SOUND_FAILURE);
        loadSound(GameConstants.SOUND_MOVE);
    }
    
    private static void loadSound(String path) {
        try {
            AudioClip sound = new AudioClip(AudioManager.class.getResource(path).toString());
            sounds.put(path, sound);
        } catch (Exception e) {
            System.err.println("Error loading sound: " + path);
        }
    }
    
    public static void playSound(String path) {
        AudioClip sound = sounds.get(path);
        if (sound != null) {
            sound.play();
        }
    }
}
