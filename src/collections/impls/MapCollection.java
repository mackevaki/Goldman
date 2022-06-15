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
import gameobjects.impls.Wall;
import listeners.interfaces.MoveResultListener;
import movestrategies.interfaces.MoveStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public class MapCollection extends MapMoveListenerRegistrator implements Serializable { // объекты для карты, которые умеют уведомлять всех слушателей о своих ходах

    private HashMap<Coordinate, ArrayList<AbstractGameObject>> gameObjects = new HashMap<>(); // хранит все объекты с доступом по координатам
    private EnumMap<GameObjectType, ArrayList<AbstractGameObject>> typeObjects = new EnumMap<>(GameObjectType.class); // хранит список объектов для каждого типа

    public MapCollection() {
        //addMoveListener(new WavPlayer());
    }

    @Override
    public void addGameObject(AbstractGameObject gameObject) {
        ArrayList<AbstractGameObject> typeList = typeObjects.get(gameObject.getType());
        ArrayList<AbstractGameObject> objList = gameObjects.get(gameObject.getCoordinate());

        if (typeList == null) {
            typeList = new ArrayList<>();
        }

        if (objList == null) {
            objList = new ArrayList<>();
        }

        typeList.add(gameObject);
        objList.add(gameObject);

        typeObjects.put(gameObject.getType(), typeList);
        gameObjects.put(gameObject.getCoordinate(), objList);
    }

    @Override
    public List<AbstractGameObject> getAllGameObjects() {
        ArrayList<AbstractGameObject> list = new ArrayList<>(); // узкое место - каждый раз создается новая коллекция
        for (ArrayList<AbstractGameObject> tmpList :
                gameObjects.values()) {
            for (AbstractGameObject obj :
                    tmpList) {
                list.add(obj);
            }
        }
        return list;
    }

    @Override
    public List<AbstractGameObject> getGameObjectsByType(GameObjectType type) {
        return typeObjects.get(type);
    }

    @Override
    public AbstractGameObject getObjectByCoordinate(Coordinate coordinate) {
        AbstractGameObject gameObject = null;
        ArrayList<AbstractGameObject> list = gameObjects.get(coordinate);

        if (list == null) { // край карты
            return new Wall(coordinate);
        }

        for (AbstractGameObject obj : list) {
            if (gameObject == null) {
                gameObject = obj;
                continue;
            }

            if (obj.getType().getIndexPriority() > gameObject.getType().getIndexPriority()) {
                gameObject = obj;
            }
        }

        return gameObject;
    }

    @Override
    public AbstractGameObject getObjectByCoordinate(int x, int y) {
        return getObjectByCoordinate(new Coordinate(x, y));
    }

    @Override
    public void moveObject(MovingDirection direction, GameObjectType gameObjectType) {
        doMoveAction(direction, gameObjectType, null);// движение по направлению (без стратегии)
    }

    @Override
    public void moveObject(MoveStrategy moveStrategy, GameObjectType gameObjectType) {
        doMoveAction(null, gameObjectType, moveStrategy);// движение по стратегии
    }

    private void doMoveAction(MovingDirection direction, GameObjectType gameObjectType, MoveStrategy moveStrategy) {
        Goldman goldman = (Goldman) getGameObjectsByType(GameObjectType.GOLDMAN).get(0);

        ActionResult actionResult = null;

        if (this.getGameObjectsByType(gameObjectType) == null) {
            return;
        }

        for (AbstractGameObject gameObject : this.getGameObjectsByType(gameObjectType)) {
            if (gameObject instanceof AbstractMovingObject) { //дорогостоящая операция
                AbstractMovingObject movingObject = (AbstractMovingObject) gameObject;

                if (moveStrategy != null){ // если указана стратегия движения - то берем направления оттуда
                    direction = moveStrategy.getDirection(movingObject, goldman, this);
                }

                Coordinate newCoordinate = movingObject.getNewCoordinateInDirection(direction);

                AbstractGameObject objectInNewCoordinate = getObjectByCoordinate(newCoordinate);

                actionResult = movingObject.moveToObject(direction, objectInNewCoordinate);

                switch (actionResult) {
                    case MOVE -> swapObjects(movingObject, objectInNewCoordinate);
                    case COLLECT_TREASURE -> {
                        swapObjects(movingObject, new Nothing(newCoordinate));
                        removeObject(objectInNewCoordinate);
                    }
                    case HIDE_IN_TREE -> swapObjects(movingObject, new Nothing(newCoordinate));
                    case WIN, DIE -> {
                    }
                }

                notifyMoveListeners(actionResult, goldman);

            }
        }

    }

    private void removeObject(AbstractGameObject object) {
        gameObjects.get(object.getCoordinate()).remove(object);
        typeObjects.get(object.getType()).remove(object);
    }


    @Override
    public void notifyMoveListeners(ActionResult actionResult, AbstractMovingObject movingObject) {
        for (MoveResultListener listener : getMoveListeners()) {
            listener.notifyActionResult(actionResult, movingObject);
        }
    }

    private void swapObjects(AbstractGameObject obj1, AbstractGameObject obj2) {
        gameObjects.get(obj1.getCoordinate()).remove(obj1);
        gameObjects.get(obj2.getCoordinate()).remove(obj2);

        swapCoordinates(obj1, obj2);

        gameObjects.get(obj1.getCoordinate()).add(obj1);
        gameObjects.get(obj2.getCoordinate()).add(obj2);
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
