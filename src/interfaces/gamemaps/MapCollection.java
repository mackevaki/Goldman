package interfaces.gamemaps;

import abstracts.AbstractGameObject;
import enums.GameObjectType;
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
}
