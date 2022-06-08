package gamemap.loader.interfaces;

import objects.MapInfo;
import objects.SavedMapInfo;
import objects.User;

import java.util.ArrayList;

public interface MapLoader {

    boolean saveMap(SavedMapInfo mapInfo);

    boolean loadMap(MapInfo mapInfo);

    ArrayList<SavedMapInfo> getSavedMapList(User user);

    boolean deleteSavedMap(MapInfo mapInfo);
}
