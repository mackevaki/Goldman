package gameobjects.impls;

import gameobjects.abstracts.AbstractGameObject;
import enums.GameObjectType;
/**
 * отвечает за работу объекта NOTHING
 */
public class Nothing extends AbstractGameObject {

    public Nothing(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.NOTHING);
        super.setIcon(null);
    }
}
