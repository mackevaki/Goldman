package interfaces.gamemaps;

/**
 * Интерфейс для создания игровой карты (задает поведение любой карты)
 */
public interface GameMap {

    int getWidth();

    int getHeight();

    boolean loadMap(Object source);

    boolean saveMap(Object source);

    int getTimeLimit();
}
