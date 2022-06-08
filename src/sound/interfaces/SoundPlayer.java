package sound.interfaces;

import javax.sound.sampled.Clip;

public interface SoundPlayer {

    void startBackgroundMusic(String soundName);

    void stopBackgroundMusic();

    void playSound(Clip clip, boolean loop);
}
