package gameobjects.abstracts;

import enums.ActionResult;
import enums.MovingDirection;
import gameobjects.interfaces.MovingObject;
import gameobjects.impls.Coordinate;

import javax.swing.*;
import java.util.EnumMap;

public abstract class AbstractMovingObject extends AbstractGameObject implements MovingObject {

    private int step = 1; // по-умолчанию у всех объектов шаг равен 1

    protected EnumMap<MovingDirection, ImageIcon> movingImages = new EnumMap<>(MovingDirection.class);

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

    public void changeIcon(MovingDirection direction) {
        super.setIcon(movingImages.get(direction));
    }
}
