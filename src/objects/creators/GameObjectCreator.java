package objects.creators;

import abstracts.AbstractGameObject;
import enums.GameObjectType;
import objects.*;

public class GameObjectCreator {
    private static final GameObjectCreator instance = new GameObjectCreator();

    public static GameObjectCreator getInstance() {
        return instance;
    }

    public AbstractGameObject createObject(GameObjectType type, Coordinate coordinate) {
        AbstractGameObject obj = null;

        switch (type) {
            case EXIT -> {
                obj = new Exit(coordinate);
            }
            case WALL -> {
                obj = new Wall(coordinate);
            }
            case GOLDMAN -> {
                obj = new Goldman(coordinate);
            }
            case NOTHING -> {
                obj = new Nothing(coordinate);
            }
            case MONSTER -> {
                obj = new Monster(coordinate);
            }
            case TREASURE -> {
                obj = new Treasure(coordinate);
            }
            default -> throw new IllegalArgumentException("Can't create object type: " + type);
        }

        return obj;
    }
}
