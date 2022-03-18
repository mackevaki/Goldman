package gameobjects.interfaces;

import gameobjects.abstracts.AbstractGameObject;
import enums.ActionResult;
import enums.MovingDirection;

public interface MovingObject extends StaticObject {

    int getStep();

    ActionResult moveToObject(MovingDirection direction, AbstractGameObject abstractGameObject);
}
