package gamemap.interfaces;

import java.awt.*;

/**
 * Интерфейс для отображения карты
 */
public interface DrawableMap  extends MainMap {

    Component getMapComponent();

    boolean drawMap();

    }
