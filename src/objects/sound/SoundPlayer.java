package objects.sound;

public interface SoundPlayer {
    void startBackgroundMusic(String soundName);

    void stopBackgroundMusic();

    void playSound(String name, final boolean loop);
}
