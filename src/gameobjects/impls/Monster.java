package gameobjects.impls;

import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import gameobjects.abstracts.AbstractGameObject;
import gameobjects.abstracts.AbstractSoundObject;
import sound.interfaces.SoundObject;

import javax.sound.sampled.Clip;

/**
 * отвечает за работу объекта MONSTER
 */
public class Monster extends AbstractSoundObject implements SoundObject {

    public Monster(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.MONSTER);
        super.setIcon(getImageIcon("/images/monster_up.jpg")); // по умолчанию будет использоваться эта иконка
        movingImages.put(MovingDirection.LEFT, getImageIcon("/images/monster_left.jpg"));
        movingImages.put(MovingDirection.RIGHT, getImageIcon("/images/monster_right.jpg"));
        movingImages.put(MovingDirection.DOWN, getImageIcon("/images/monster_down.jpg"));
        movingImages.put(MovingDirection.UP, getImageIcon("/images/monster_up.jpg"));
    }

    @Override
    public ActionResult doAction(AbstractGameObject gameObject) {
        if (gameObject == null) { // край карты
            return ActionResult.NO_ACTION;
        }

        switch (gameObject.getType()) {
            case TREASURE, MONSTER, WALL, TREE -> {
                return ActionResult.NO_ACTION; //монстр не может наступать на сокровище или других монстров
            }
            case GOLDMAN -> {
                return ActionResult.DIE;
            }
        }

        return super.doAction(gameObject);
    }

    @Override
    public Clip getSoundClip(ActionResult actionResult) {
        switch (actionResult) {
            case DIE -> {
                return super.getDieClip();
            }
        }

        return null;
    }
}
