package sound.impls;

import enums.ActionResult;
import gameobjects.abstracts.AbstractMovingObject;
import listeners.interfaces.MoveResultListener;
import sound.interfaces.SoundObject;
import sound.interfaces.SoundPlayer;

import javax.sound.sampled.*;
import java.io.IOException;

public class WavPlayer implements MoveResultListener, SoundPlayer {
    public static final String WAV_DIE = "die.wav";
    public static final String WAV_WIN = "win.wav";
    public static final String WAV_TREASURE = "treasure.wav";
    public static final String WAV_BACKGROUND = "background.wav";
    public static final String SOUND_PATH = "/sound/sounds/";

    private Clip backgroundClip;

    public WavPlayer() {
        AudioInputStream ais = null;
        try {
            backgroundClip = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(this.getClass().getResource(WavPlayer.SOUND_PATH + WAV_BACKGROUND));
            backgroundClip.open(ais);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyActionResult(ActionResult actionResult, final AbstractMovingObject movingObject) {
        if (!(movingObject instanceof SoundObject)) return;

        SoundObject soundObject = (SoundObject) movingObject;

        Clip clip = soundObject.getSoundClip(actionResult);

        playSound(clip, false);
    }

    @Override
    public void playSound(final Clip clip, final boolean loop) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (clip == null) return;

                clip.setFramePosition(0);

                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                    clip.start();
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void startBackgroundMusic(String soundName) {
        playSound(backgroundClip, true);
    }

    @Override
    public void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }
}
