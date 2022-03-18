package objects;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import objects.sound.SoundObject;
import objects.sound.WavPlayer;

/**
 * отвечает за работу объекта MONSTER
 */
public class Monster extends AbstractMovingObject implements SoundObject {

    public Monster(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.MONSTER);
        super.setIcon(getImageIcon("/images/monster_left.jpg")); // по умолчанию будет использоваться эта кнопка
    }

    @Override
    public void changeIcon(MovingDirection direction) {
        switch (direction) {
            case LEFT -> super.setIcon(getImageIcon("/images/monster_left.jpg"));
            case RIGHT -> super.setIcon(getImageIcon("/images/monster_right.jpg"));
            case DOWN -> super.setIcon(getImageIcon("/images/monster_down.jpg"));
            case UP -> super.setIcon(getImageIcon("/images/monster_up.jpg"));
        }
    }

    @Override
    public ActionResult doAction(AbstractGameObject gameObject) {
        if (gameObject == null) { // край карты
            return ActionResult.NO_ACTION;
        }

        switch (gameObject.getType()) {
            case TREASURE, MONSTER, WALL -> {
                return ActionResult.NO_ACTION; //монстр не может наступать на сокровище или других монстров
            }
            case GOLDMAN -> {
                return ActionResult.DIE;
            }
        }

        return super.doAction(gameObject);
    }

    @Override
    public String getSoundName(ActionResult actionResult) {
        switch (actionResult) {
            case DIE -> {
                return WavPlayer.WAV_DIE;
            }
        }

        return null;
    }
}
