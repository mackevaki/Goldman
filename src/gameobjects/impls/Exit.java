package gameobjects.impls;

import gameobjects.abstracts.AbstractGameObject;
import enums.GameObjectType;

/**
 * объект EXIT
 */
public class Exit extends AbstractGameObject {
    public Exit(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.EXIT);
        super.setIcon(getImageIcon("/images/exit.gif"));
    }
}
