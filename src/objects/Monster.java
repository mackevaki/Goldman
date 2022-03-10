package objects;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.GameObjectType;
import enums.MovingDirection;

/**
 * отвечает за работу объекта MONSTER
 */
public class Monster extends AbstractMovingObject {

    public Monster(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.MONSTER);

        super.setIconLeft(getImageIcon("/images/monster_left.jpg"));
        super.setIconRight(getImageIcon("/images/monster_right.jpg"));
        super.setIconUp(getImageIcon("/images/monster_up.jpg"));
        super.setIconDown(getImageIcon("/images/monster_down.jpg"));

        super.setIcon(super.getIconLeft()); // по умолчанию будет использоваться эта кнопка
    }

    @Override
    public void getMoveResult(AbstractGameObject objectInNewCoordinate) {
        throw new UnsupportedOperationException("Methods are not supported yet");
    }
}
