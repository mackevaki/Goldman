package collections.interfaces;

import gameobjects.abstracts.AbstractGameObject;
import enums.GameObjectType;
import enums.MovingDirection;
import movestrategies.interfaces.MoveStrategy;
import gameobjects.impls.Coordinate;
import listeners.interfaces.MoveResultNotifier;

import java.util.Collection;
import java.util.List;

public interface GameCollection extends MoveResultNotifier {
    void addGameObject(AbstractGameObject gameObject);

    Collection<AbstractGameObject> getAllGameObjects();

    List<AbstractGameObject> getGameObjectsByType(GameObjectType type);

    AbstractGameObject getObjectByCoordinate(Coordinate coordinate);

    AbstractGameObject getObjectByCoordinate(int x, int y);

    void moveObject(MovingDirection direction, GameObjectType objectType);

    void moveObject(MoveStrategy moveStrategy, GameObjectType gameObjectType);

    void clear();

}
