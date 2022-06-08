package gameobjects.abstracts;

import sound.impls.WavPlayer;
import sound.interfaces.SoundObject;

import javax.sound.sampled.*;
import java.io.IOException;

public abstract class AbstractSoundObject extends AbstractMovingObject implements SoundObject {

    private transient Clip dieClip;

    public Clip getDieClip() {
        if (dieClip == null) {
            setDieClip();
        }

        return dieClip;
    }

    protected Clip openClip(String soundName) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(this.getClass().getResource(WavPlayer.SOUND_PATH + soundName));
            clip.open(ais);
            return clip;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

        private void setDieClip() {
        dieClip = openClip(WavPlayer.WAV_DIE);
    }

}
