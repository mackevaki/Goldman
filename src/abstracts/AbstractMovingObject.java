package abstracts;

import enums.ActionResult;
import enums.MovingDirection;
import interfaces.gameobjects.GameObjectMoveListener;
import interfaces.gameobjects.MoveListener;
import interfaces.gameobjects.MovingObject;
import objects.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMovingObject extends AbstractGameObject implements MovingObject, MoveListener {

    public abstract void changeIcon(MovingDirection direction);
    private int step = 1; // по-умолчанию у всех объектов шаг равен 1

    @Override
    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    protected void actionBeforeMove(MovingDirection direction) {
        // при движении объект должен сменить иконку и произвести звук
        changeIcon(direction);
        // playSound();
    }

    /**
     * @param gameObject - объект, с которым будем взаимодействовать
     * @return
     */
    public ActionResult doAction(AbstractGameObject gameObject) {
        if (gameObject == null) { // край карты
            return ActionResult.NO_ACTION;
        }

        switch (gameObject.getType()) {
            case NOTHING -> {
                return ActionResult.MOVE;
            }
        }

        return ActionResult.NO_ACTION;
    }

    @Override
    public ActionResult moveToObject(MovingDirection direction, AbstractGameObject abstractGameObject) {
        actionBeforeMove(direction);
        return doAction(abstractGameObject);
    }

    private ArrayList<GameObjectMoveListener> listeners = new ArrayList<>();

    @Override
    public List<GameObjectMoveListener> getListeners() {
        return listeners;
    }

    @Override
    public void addListener(GameObjectMoveListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(GameObjectMoveListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeAllListeners() {
        listeners.clear();
    }

    /**
     * @param obj1 - объект, который движется
     * @param obj2 - объект, на место которого совершается перемещение
     */
    @Override
    public void notifyListeners(AbstractGameObject obj1, AbstractGameObject obj2) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
