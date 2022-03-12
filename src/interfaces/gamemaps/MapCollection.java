package interfaces.gamemaps;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import interfaces.collections.GameCollection;
import objects.Coordinate;

import java.util.*;

public class MapCollection implements GameCollection {

    private HashMap<Coordinate, AbstractGameObject> gameObjects = new HashMap<>(); // хранит все объекты с доступом по координатам
    private EnumMap<GameObjectType, ArrayList<AbstractGameObject>> typeObjects = new EnumMap<>(GameObjectType.class); // хранит список объектов для каждого типа

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
        for (AbstractGameObject gameObject : getListOfDefinitObjects(gameObjectType)) {
            if (gameObject instanceof AbstractMovingObject) { //дорогостоящая операция
                AbstractMovingObject movingObject = (AbstractMovingObject) gameObject;

                Coordinate newCoordinate = getNewCoordinate(direction, movingObject);

                AbstractGameObject objectInNewCoordinate = getObjectByCoordinate(newCoordinate);

                ActionResult actionResult = movingObject.moveToObject(direction, objectInNewCoordinate);

                switch (actionResult) {
                    case MOVE -> swapObjects(movingObject, objectInNewCoordinate);
                }
            }
        }

    }

    private Coordinate getNewCoordinate(MovingDirection direction, AbstractMovingObject movingObject) {
        // берем текущие координаты объекта, которые нужно передвинуть
        int x = movingObject.getCoordinate().getX();
        int y = movingObject.getCoordinate().getY();

        Coordinate newCoordinate = new Coordinate(x, y);

        switch (direction) {
            case UP -> {
                newCoordinate.setXY(x, y - movingObject.getStep());
            }
            case DOWN -> {
                newCoordinate.setXY(x, y + movingObject.getStep());
            }
            case RIGHT -> {
                newCoordinate.setXY(x + movingObject.getStep(), y);
            }
            case LEFT -> {
                newCoordinate.setXY(x - movingObject.getStep(), y);
            }
        }

        return newCoordinate;
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
}
