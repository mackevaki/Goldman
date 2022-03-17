package interfaces.gamemaps.collections;

import abstracts.AbstractGameObject;
import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import objects.Coordinate;
import objects.listeners.MoveResultNotifier;

import java.util.Collection;
import java.util.List;

public interface GameCollection extends MoveResultNotifier {
    void addGameObject(AbstractGameObject gameObject);

    Collection<AbstractGameObject> getAllGameObjects();

    List<AbstractGameObject> getListOfDefinitObjects(GameObjectType type);

    AbstractGameObject getObjectByCoordinate(Coordinate coordinate);

    AbstractGameObject getObjectByCoordinate(int x, int y);

    ActionResult moveObject(MovingDirection direction, GameObjectType objectType);

    void moveObjectRandom(GameObjectType objectType);
}
