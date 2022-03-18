package creators;

import gamemap.abstracts.AbstractGameMap;
import enums.LocationType;
import collections.interfaces.GameCollection;
import gamemap.impls.FileSystemGameMap;

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
