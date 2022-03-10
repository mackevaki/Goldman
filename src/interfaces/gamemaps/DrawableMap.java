package interfaces.gamemaps;

import abstracts.AbstractGameMap;

import java.awt.*;

/**
 * Интерфейс для отображения карты
 */
public interface DrawableMap {

    Component getMap();

    boolean drawMap();

    AbstractGameMap getGameMap();

    }
