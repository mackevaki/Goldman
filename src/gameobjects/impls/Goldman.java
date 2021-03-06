package gameobjects.impls;

import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import gameobjects.abstracts.AbstractGameObject;
import gameobjects.abstracts.AbstractSoundObject;
import sound.impls.WavPlayer;
import sound.interfaces.SoundObject;

import javax.sound.sampled.Clip;

/**
 * класс отвечает за работу объекта GOLDMAN - главный персонаж игры
 */
public class Goldman extends AbstractSoundObject implements SoundObject {

    private int totalScore = 0; // количество собранных игроком очков
    private int turnsNumber = 0; // количество сделанных ходов

    private transient Clip moveClip;
    private transient Clip treasureClip;
    private transient Clip winClip;
    private transient Clip treeClip;


    public Goldman(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.GOLDMAN);
        super.setIcon(getImageIcon("/images/goldman_up.png"));

        movingImages.put(MovingDirection.LEFT, getImageIcon("/images/goldman_left.png"));
        movingImages.put(MovingDirection.RIGHT, getImageIcon("/images/goldman_right.png"));
        movingImages.put(MovingDirection.DOWN, getImageIcon("/images/goldman_down.png"));
        movingImages.put(MovingDirection.UP, getImageIcon("/images/goldman_up.png"));
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addTotalScore(int totalScore) {
        this.totalScore += totalScore;
    }

    public int getTurnsNumber() {
        return turnsNumber;
    }

    public void setTurnsNumber(int turnsNumber) {
        this.turnsNumber = turnsNumber;
    }


    /**
     * @param gameObject - объект, с которым будем взаимодействовать
     * @return
     */
    @Override
    public ActionResult doAction(AbstractGameObject gameObject) {
        turnsNumber++;

        if (gameObject == null) { // край карты
            return ActionResult.NO_ACTION;
        }

        switch (gameObject.getType()) {
            case MONSTER -> {
                return ActionResult.DIE;
            }
            case TREASURE -> {
                totalScore += ((Treasure)gameObject).getScore();
                return ActionResult.COLLECT_TREASURE;
            }
            case EXIT -> {
                totalScore *= 2;
                return ActionResult.WIN;
            }
            case TREE -> {
                return ActionResult.HIDE_IN_TREE;
            }
        }

        return super.doAction(gameObject);
    }

    @Override
    public Clip getSoundClip(ActionResult actionResult) {
//        if (moveClip ==null) {
//            moveClip = openClip(WavPlayer.WAV_MOVE);
//        }

        if (treasureClip == null) {
            treasureClip = openClip(WavPlayer.WAV_TREASURE);
        }

        if (winClip == null) {
            winClip = openClip(WavPlayer.WAV_WIN);
        }

        if (treeClip == null) {
            treeClip = openClip(WavPlayer.WAV_TREE);
        }

        switch (actionResult) {
            case DIE -> {
                return super.getDieClip();
            }
            case WIN -> {
                return winClip;
            }
            case COLLECT_TREASURE -> {
                return treasureClip;
            }
            case HIDE_IN_TREE -> {
                return treeClip;
            }
        }

        return null;
    }
}
