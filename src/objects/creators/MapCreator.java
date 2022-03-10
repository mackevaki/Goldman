package objects.creators;

import abstracts.AbstractGameMap;
import enums.LocationType;
import interfaces.collections.GameCollection;
import objects.maps.FileSystemGameMap;

public class MapCreator {
    private static final MapCreator instance = new MapCreator();

    public static MapCreator getInstance() {
        return instance;
    }

    public AbstractGameMap createMap(LocationType type, GameCollection gameCollection) {
        AbstractGameMap map = null;

        switch (type) {
            case FS -> map = new FileSystemGameMap(gameCollection);
            case DB -> { }
            default -> throw new IllegalArgumentException("Can't create map type: " + type);
        }

        return map;
    }
}
