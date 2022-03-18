package movestrategies.interfaces;

import gameobjects.abstracts.AbstractGameObject;
import gameobjects.abstracts.AbstractMovingObject;
import enums.MovingDirection;
import collections.interfaces.GameCollection;

public interface MoveStrategy {

    MovingDirection getDirection(AbstractMovingObject movingObject, AbstractGameObject targetObject, GameCollection gameCollection);
}
