package gameobjects.impls;

import enums.GameObjectType;
import gameobjects.abstracts.AbstractGameObject;

public class Tree extends AbstractGameObject {

    public Tree(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.TREE);
        super.setIcon(getImageIcon("/images/tree.jpg"));
    }
}
