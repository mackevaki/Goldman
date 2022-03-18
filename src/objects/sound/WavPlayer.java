package objects.sound;

import abstracts.AbstractMovingObject;
import enums.ActionResult;
import objects.listeners.MoveResultListener;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class WavPlayer implements MoveResultListener, SoundPlayer {
    public static final String WAV_DIE = "die.wav";
    public static final String WAV_WIN = "win.wav";
    public static final String WAV_TREASURE = "treasure.wav";
    public static final String WAV_BACKGROUND = "background.wav";

    private Clip backgroundClip;
    private Clip moveClip;

    public WavPlayer() {
        try {
            backgroundClip = AudioSystem.getClip();
            moveClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyActionResult(ActionResult actionResult, final AbstractMovingObject movingObject) {
        if (!(movingObject instanceof SoundObject)) return;

        SoundObject soundObject = (SoundObject) movingObject;

        playSound(soundObject.getSoundName(actionResult), false);
    }

    /**
     *
     * @param name - sound's name
     * @param loop - necessity to play sound on repeat
     * @param stopPrev - necessity to stop previous sound before playing current sound
     */
    public void playSound(String name, final boolean loop, final Clip clip, boolean stopPrev) {
        try {
            if (name == null) {
                return;
            }

            URL sound = this.getClass().getResource("/sounds/" + name);

            final AudioInputStream ais = AudioSystem.getAudioInputStream(sound);

            // запускаем звук в параллельном потоке, чтобы, когда звук проигрывается,
            // мы могли дальше передвигать нашего персонажа
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();

                        if (stopPrev && clip != null) {
                            clip.stop();
                            clip.close();
                        }

                        clip.open(ais);

                        if (loop) {
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                        } else {
                            clip.start();
                        }


                    } catch (LineUnavailableException | IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (ais != null) {
                            try {
                                ais.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void playSound(String name, final boolean loop) {
        try {
            playSound(name, loop, AudioSystem.getClip(), false);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startBackgroundMusic(String soundName) {
        playSound(soundName, true, backgroundClip, false);
    }

    @Override
    public void stopBackgroundMusic() {
        backgroundClip.stop();
        backgroundClip.close();
    }
}
