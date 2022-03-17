package interfaces.gamemaps.collections;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.ActionResult;
import enums.GameObjectType;
import enums.MovingDirection;
import objects.Coordinate;
import objects.Goldman;
import objects.Nothing;
import objects.listeners.MapListenerRegistrator;
import objects.listeners.MoveResultListener;

import java.util.*;

public class MapCollection extends MapListenerRegistrator { // объекты для карты, которые умеют уведомлять всех слушателей о своих ходах

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
    public ActionResult moveObject(MovingDirection direction, GameObjectType gameObjectType) {
        Goldman goldman = (Goldman) getListOfDefinitObjects(GameObjectType.GOLDMAN).get(0);

        ActionResult actionResult = null;

        for (AbstractGameObject gameObject : getListOfDefinitObjects(gameObjectType)) {
            if (gameObject instanceof AbstractMovingObject) { //дорогостоящая операция
                AbstractMovingObject movingObject = (AbstractMovingObject) gameObject;

                if (direction == null) {
                    direction = getRandomMoveDirection(movingObject);
                }

                Coordinate newCoordinate = getNewCoordinate(direction, movingObject);

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
    public void notifyMoveListeners(ActionResult actionResult, Goldman goldman) {
        for (MoveResultListener listener : getMoveListeners()) {
            listener.notifyActionResult(actionResult, goldman);
        }
    }

    @Override
    public void moveObjectRandom(GameObjectType objectType) {
        moveObject(null, objectType);
    }

    private MovingDirection getRandomMoveDirection(AbstractMovingObject movingObject) {
        Goldman goldman = (Goldman) getListOfDefinitObjects(GameObjectType.GOLDMAN).get(0);

        MovingDirection direction = null;

        int goldmanX = goldman.getCoordinate().getX();
        int goldmanY = goldman.getCoordinate().getY();

        int monsterX = movingObject.getCoordinate().getX();
        int monsterY = movingObject.getCoordinate().getY();

        int randomDirectionInt = getRandomInt(2);

        if (randomDirectionInt == 1) { // 50 на 50 шанс двинуться по направлению к персонажу, 0 - к персонажу, 1 - от него
            randomDirectionInt = getRandomInt(2); // рандомно определяем по какой оси двигаемся к персонажу
            switch (randomDirectionInt) {
                case 1: { //движемся к персонажу по X
                    if (monsterX > goldmanX) {
                        direction = MovingDirection.LEFT;
                    } else {
                        direction = MovingDirection.RIGHT;
                    }
                    break;
                }
                case 2: { // движемся к персонажу по Y
                    if (monsterY > goldmanY) {
                        direction = MovingDirection.UP;
                    } else {
                        direction = MovingDirection.DOWN;
                    }
                    break;
                }
            }
        } else { // 1 - двигаться от персонажа
            randomDirectionInt = getRandomInt(2); // по какой оси от персонажа - Х или У

            switch (randomDirectionInt) {
                case 1: { // по Х
                    if (monsterX > goldmanX) {
                        direction = MovingDirection.RIGHT;
                    } else {
                        direction = MovingDirection.LEFT;
                    }
                    break;
                }
                case 2: { // по У
                    if (monsterY > goldmanY) {
                        direction = MovingDirection.DOWN;
                    } else {
                        direction = MovingDirection.UP;
                    }
                    break;
                }
            }
        }

        return direction;
    }

    private Coordinate getNewCoordinate(MovingDirection direction, AbstractMovingObject movingObject) {
        // берем текущие координаты объекта, которые нужно передвинуть
        int x = movingObject.getCoordinate().getX();
        int y = movingObject.getCoordinate().getY();

        Coordinate newCoordinate = new Coordinate(x, y);

        switch (direction) {
            case UP -> newCoordinate.setXY(x, y - movingObject.getStep());
            case DOWN -> newCoordinate.setXY(x, y + movingObject.getStep());
            case RIGHT -> newCoordinate.setXY(x + movingObject.getStep(), y);
            case LEFT -> newCoordinate.setXY(x - movingObject.getStep(), y);
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

    private int getRandomInt(int i) {
        Random r = new Random();
        return r.nextInt(i) + 1;
    }

}
