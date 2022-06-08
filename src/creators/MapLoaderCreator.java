package creators;

import gamemap.abstracts.AbstractGameMap;
import enums.LocationType;
import collections.interfaces.GameCollection;
import gamemap.loader.impls.DBMapLoader;
import gamemap.loader.impls.FSMapLoader;
import gamemap.loader.interfaces.MapLoader;

public class MapLoaderCreator {
    private static final MapLoaderCreator instance = new MapLoaderCreator();

    public static MapLoaderCreator getInstance() {
        return instance;
    }

    public MapLoader createMap(LocationType type, AbstractGameMap gameMap) {
        MapLoader obj = null;

        switch (type) {
            case FS -> obj = new FSMapLoader(gameMap);
            case DB -> obj = new DBMapLoader(gameMap);
            default -> throw new IllegalArgumentException("Can't create map type: " + type);
        }

        return obj;
    }
}
