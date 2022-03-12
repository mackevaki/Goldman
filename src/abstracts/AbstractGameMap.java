package abstracts;

import enums.GameObjectType;
import enums.MovingDirection;
import interfaces.collections.GameCollection;
import interfaces.gamemaps.GameMap;
import objects.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * базовая карта без какого-либо отображения
 */
public abstract class AbstractGameMap implements GameMap, Serializable {

    private static final long serialVersionUID = 1L; // переменная нужна для сериализации и десериализации

    private int width;
    private int height;
    private int timeLimit;
    private String name;

    private boolean isExitExists;
    private boolean isGoldManExists;

    private GameCollection gameCollection;

    public AbstractGameMap() {
    }

    public AbstractGameMap(GameCollection gameCollection) {
        this.gameCollection = gameCollection;
    }

    public GameCollection getGameCollection() {
        if (gameCollection == null) {
            try {
                throw new Exception("Game collection is not initialized");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return gameCollection;
    }

    public void setGameCollection(GameCollection gameCollection) {
        this.gameCollection = gameCollection;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExitExists() {
        return isExitExists;
    }

    public void setExitExists(boolean exitExists) {
        isExitExists = exitExists;
    }

    public boolean isGoldManExists() {
        return isGoldManExists;
    }

    public void setGoldManExists(boolean goldManExists) {
        isGoldManExists = goldManExists;
    }

    public AbstractGameObject getObjectPriority(AbstractGameObject firstObject, AbstractGameObject secondObject) {
        // приоритет объекта зависит от номера индекса объекта в enum
        return (firstObject.getType().getIndexPriority() > secondObject.getType().getIndexPriority()) ? firstObject : secondObject;
    }

    public boolean isValidMap() {
        return isExitExists && isGoldManExists; // если есть вход и выход -  крата валидна
    }

    public void move(MovingDirection direction, GameObjectType gameObjectType) {

        for (AbstractGameObject gameObject :
                getGameCollection().getListOfDefinitObjects(gameObjectType)) {
            if (gameObject instanceof AbstractMovingObject) { //дорогостоящая операция
                AbstractMovingObject movingObject = (AbstractMovingObject) gameObject;
                movingObject.move(direction, this);
            }
        }


    }
}
