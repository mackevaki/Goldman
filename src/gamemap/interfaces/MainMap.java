package gamemap.interfaces;

/**
 * Интерфейс для создания игровой карты (задает поведение любой карты)
 */
public interface MainMap {

    int getWidth();

    int getHeight();

    boolean loadMap(Object source);

    boolean saveMap(Object source);

    int getTimeLimit();
}
