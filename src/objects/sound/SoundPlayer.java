package objects.sound;

public interface SoundPlayer {
    public static final String WAV_DIE = "die.wav";
    public static final String WAV_WIN = "win.wav";
    public static final String WAV_TREASURE = "treasure.wav";
    public static final String WAV_BACKGROUND = "background.wav";

    void startBackgroundMusic(String soundName);

    void stopBackgroundMusic();

    void playSound(String name, final boolean loop);
}
