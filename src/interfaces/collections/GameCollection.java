package interfaces.collections;

import abstracts.AbstractGameObject;
import enums.GameObjectType;
import enums.MovingDirection;
import objects.Coordinate;

import java.util.Collection;
import java.util.List;

public interface GameCollection {
    void addGameObject(AbstractGameObject gameObject);

    Collection<AbstractGameObject> getAllGameObjects();

    List<AbstractGameObject> getListOfDefinitObjects(GameObjectType type);

    AbstractGameObject getObjectByCoordinate(Coordinate coordinate);

    AbstractGameObject getObjectByCoordinate(int x, int y);

    void moveObject(MovingDirection direction, GameObjectType objectType);
}
