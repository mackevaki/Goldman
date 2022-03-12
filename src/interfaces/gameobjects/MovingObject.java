package interfaces.gameobjects;

import abstracts.AbstractGameMap;
import enums.MovingDirection;

public interface MovingObject extends StaticObject {

    void move(MovingDirection direction, AbstractGameMap abstractGameMap);
}
