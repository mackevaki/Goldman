package collections.impls;

import collections.abstracts.MapMoveListenerRegistrator;
import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import gameobjects.abstracts.AbstractGameObject;
import gameobjects.abstracts.AbstractMovingObject;
import gameobjects.impls.Coordinate;
import gameobjects.impls.Goldman;
import gameobjects.impls.Nothing;
import listeners.interfaces.MoveResultListener;
import movestrategies.interfaces.MoveStrategy;
import sound.impls.WavPlayer;

import java.io.Serializable;
import java.util.*;

public class MapCollection extends MapMoveListenerRegistrator implements Serializable { // объекты для карты, которые умеют уведомлять всех слушателей о своих ходах

    private HashMap<Coordinate, AbstractGameObject> gameObjects = new HashMap<>(); // хранит все объекты с доступом по координатам
    private EnumMap<GameObjectType, ArrayList<AbstractGameObject>> typeObjects = new EnumMap<>(GameObjectType.class); // хранит список объектов для каждого типа

    public MapCollection() {
        addMoveListener(new WavPlayer());
    }

    @Override
    public void addGameObject(AbstractGameObject gameObject) {
        gameObjects.put(gameObject.getCoordinate(), gameObject);

        ArrayList<AbstractGameObject> tmpList = typeObjects.get(gameObject.getType());
        if (tmpList == null) {
            tmpList = new ArrayList<>();
        }
        tmpList.add(gameObject);

        typeObjects.put(gameObject.getType(), tmpList);
    }

    @Override
    public Collection<AbstractGameObject> getAllGameObjects() {
        return gameObjects.values();
    }

    @Override
    public List<AbstractGameObject> getListOfDefinitObjects(GameObjectType type) {
        return typeObjects.get(type);
    }

    @Override
    public AbstractGameObject getObjectByCoordinate(Coordinate coordinate) {
        return gameObjects.get(coordinate);
    }

    @Override
    public AbstractGameObject getObjectByCoordinate(int x, int y) {
        return gameObjects.get(new Coordinate(x, y));
    }

    @Override
    public void moveObject(MovingDirection direction, GameObjectType gameObjectType) {
        doMoveAction(direction, gameObjectType, null);// движение по направлению (без стратегии)
    }

    @Override
    public void moveObject(MoveStrategy moveStrategy, GameObjectType gameObjectType) {
        doMoveAction(null, gameObjectType, moveStrategy);// движение по стратегии
    }

    private ActionResult doMoveAction(MovingDirection direction, GameObjectType gameObjectType, MoveStrategy moveStrategy) {
        Goldman goldman = (Goldman) getListOfDefinitObjects(GameObjectType.GOLDMAN).get(0);

        ActionResult actionResult = null;

        for (AbstractGameObject gameObject : getListOfDefinitObjects(gameObjectType)) {
            if (gameObject instanceof AbstractMovingObject) { //дорогостоящая операция
                AbstractMovingObject movingObject = (AbstractMovingObject) gameObject;

                if (moveStrategy != null){// если указана стратегия движения - то берем наравления оттуда
                    direction = moveStrategy.getDirection(movingObject, goldman, this);
                }

                Coordinate newCoordinate = movingObject.getNewCoordinateInDirection(direction);

                AbstractGameObject objectInNewCoordinate = getObjectByCoordinate(newCoordinate);

                actionResult = movingObject.moveToObject(direction, objectInNewCoordinate);

                switch (actionResult) {
                    case MOVE:
                        swapObjects(movingObject, objectInNewCoordinate);
                        break;
                    case COLLECT_TREASURE:
                        swapObjects(movingObject, new Nothing(newCoordinate));
                        break;
                    case WIN: {
                        break;
                    }
                    case DIE: {
                        break;
                    }
                }

                notifyMoveListeners(actionResult, goldman);

            }
        }

        return actionResult;
    }

    @Override
    public void notifyMoveListeners(ActionResult actionResult, AbstractMovingObject movingObject) {
        for (MoveResultListener listener : getMoveListeners()) {
            listener.notifyActionResult(actionResult, movingObject);
        }
    }

    private void swapObjects(AbstractGameObject obj1, AbstractGameObject obj2) {
        swapCoordinates(obj1, obj2);

        gameObjects.put(obj1.getCoordinate(), obj1);
        gameObjects.put(obj2.getCoordinate(), obj2);
    }

    private void swapCoordinates(AbstractGameObject obj1, AbstractGameObject obj2) {
        Coordinate tmpCoordinate = obj1.getCoordinate();
        obj1.setCoordinate(obj2.getCoordinate());
        obj2.setCoordinate(tmpCoordinate);
    }

    @Override
    public void clear() {
        gameObjects.clear();
        typeObjects.clear();
    }

}
