package interfaces.gameobjects;

import abstracts.AbstractGameObject;

import java.util.List;

public interface MoveListener {

    List<GameObjectMoveListener> getListeners();

    void addListener(GameObjectMoveListener listener);

    void removeListener(GameObjectMoveListener listener);

    void removeAllListeners();

    /**
     *
     * @param obj1 - объект, который движется
     * @param obj2 - объект, на место которого совершается перемещение
     */
    void notifyListeners(AbstractGameObject obj1, AbstractGameObject obj2);

}
