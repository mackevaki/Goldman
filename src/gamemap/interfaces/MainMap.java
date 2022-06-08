package gamemap.interfaces;

import collections.interfaces.GameCollection;
import objects.MapInfo;

/**
 * Интерфейс для создания игровой карты (задает поведение любой карты)
 */
public interface MainMap {

    MapInfo getMapInfo();

    GameCollection getGameCollection(); // все карты должны хранить коллекцию объектов

}
