package sound.impls;

import gameobjects.abstracts.AbstractMovingObject;
import enums.ActionResult;
import listeners.interfaces.MoveResultListener;
import sound.interfaces.SoundObject;
import sound.interfaces.SoundPlayer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class WavPlayer implements MoveResultListener, SoundPlayer {

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

        if (actionResult.equals(ActionResult.DIE) || actionResult.equals(ActionResult.WIN)) stopBackgroundMusic();

        SoundObject soundObject = (SoundObject) movingObject;

        playSound(soundObject.getSoundName(actionResult), false, moveClip, true);
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

    /**
     * @param name - sound's name
     * @param loop - necessity to play sound on repeat
     * @param stopPrev - necessity to stop previous sound before playing current sound
     */
    private void playSound(String name, final boolean loop, final Clip clip, boolean stopPrev) {
        try {
            if (name == null) {
                return;
            }

            URL sound = this.getClass().getResource("/sound/sounds/" + name);

            final AudioInputStream ais = AudioSystem.getAudioInputStream(sound);

            // запускаем звук в параллельном потоке, чтобы, когда звук проигрывается,
            // мы могли дальше передвигать нашего персонажа
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
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
}
