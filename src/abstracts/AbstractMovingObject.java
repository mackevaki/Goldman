package abstracts;

import enums.ActionResult;
import enums.MovingDirection;
import interfaces.gameobjects.MovingObject;
import objects.Coordinate;

public abstract class AbstractMovingObject extends AbstractGameObject implements MovingObject {

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

    public Coordinate getNewCoordinateInDirection(MovingDirection direction) {

        // берем текущие координаты объекта, которые нужно передвинуть
        int x = this.getCoordinate().getX();
        int y = this.getCoordinate().getY();

        Coordinate newCoordinate = new Coordinate(x, y);

        switch (direction) {
            case UP -> newCoordinate.setXY(x, y - this.getStep());
            case DOWN -> newCoordinate.setXY(x, y + this.getStep());
            case RIGHT -> newCoordinate.setXY(x + this.getStep(), y);
            case LEFT -> newCoordinate.setXY(x - this.getStep(), y);
        }

        return newCoordinate;
    }
}
