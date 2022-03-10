package enums;

import java.io.Serializable;

/**
* типы объектов, которые участвуют в игре (которые будут рисоваться на карте)
*/
public enum GameObjectType implements Serializable {
    MONSTER(5),
    TREASURE(4),
    EXIT(3),
    WALL(2),
    GOLDMAN(1),
    NOTHING(-1);

    private GameObjectType(int indexPriority) {
        this.indexPriority = indexPriority;
    }

    private int indexPriority; // индекс для приоритета показа на карте, если несколько объектов окажется в одном квадрате

    public int getIndexPriority() {
        return indexPriority;
    }
}
