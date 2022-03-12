package objects;

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
}
