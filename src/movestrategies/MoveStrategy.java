package movestrategies;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.MovingDirection;
import interfaces.gamemaps.collections.GameCollection;

public interface MoveStrategy {

    MovingDirection getDirection(AbstractMovingObject movingObject, AbstractGameObject targetObject, GameCollection gameCollection);
}
