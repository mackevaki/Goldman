package gamemap.adapters;

import enums.LocationType;
import gamemap.abstracts.AbstractGameMap;
import gamemap.loader.impls.DBMapLoader;
import gamemap.loader.impls.FSMapLoader;
import objects.MapInfo;
import objects.SavedMapInfo;
import objects.User;

import java.util.ArrayList;

public class HybridMapLoader {
    private DBMapLoader dbMapLoader;
    private FSMapLoader fsMapLoader;

    private AbstractGameMap gameMap;

    public HybridMapLoader(AbstractGameMap gameMap) {
        dbMapLoader = new DBMapLoader(gameMap);
        fsMapLoader = new FSMapLoader(gameMap);
        this.gameMap = gameMap;
    }

    public boolean saveMap(SavedMapInfo mapInfo, LocationType locationType) {
        switch (locationType) {
            case FS -> {
                return fsMapLoader.saveMap(mapInfo);
            }
            case DB -> {
                return dbMapLoader.saveMap(mapInfo);
            }
        }
        return false;
    }

    public boolean loadMap(MapInfo mapInfo, LocationType locationType) {
        switch (locationType) {
            case DB -> {
                gameMap = dbMapLoader.getGameMap();
                return dbMapLoader.loadMap(mapInfo);
            }
            case FS -> {
                gameMap = fsMapLoader.getGameMap();
                return fsMapLoader.loadMap(mapInfo);
            }
        }
    return false;
    }

    public ArrayList<SavedMapInfo> getSavedMapList(User user, LocationType locationType) {
        switch (locationType) {
            case DB -> {
                return dbMapLoader.getSavedMapList(user);
            }
            case FS -> {
                return fsMapLoader.getSavedMapList(user);
            }
        }
        return null;
    }

    public boolean deleteMap(MapInfo mapInfo, LocationType locationType) {
        switch (locationType) {
            case DB -> {
                return dbMapLoader.deleteSavedMap(mapInfo);
            }
            case FS -> {
                return fsMapLoader.deleteSavedMap(mapInfo);
            }
        }
        return false;
    }

    public AbstractGameMap getGameMap() {
        return gameMap;
    }

    public int getPlayerId(String username) {
        return dbMapLoader.getPlayerId(username);
    }
}
