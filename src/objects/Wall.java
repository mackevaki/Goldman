package objects;

import abstracts.AbstractGameObject;
import enums.GameObjectType;

/**
 * класс отвечает за работу объекта WALL
 */
public class Wall extends AbstractGameObject {

    public Wall(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.WALL);
        super.setIcon(getImageIcon("/images/wall.png"));
    }
}
