package abstracts;

import enums.MovingDirection;
import interfaces.gameobjects.MovingObject;
import objects.Coordinate;

public abstract class AbstractMovingObject extends AbstractGameObject implements MovingObject {

    public abstract void changeIcon(MovingDirection direction);

    @Override
    public void move(MovingDirection direction, AbstractGameMap abstractGameMap) {
        Coordinate newCoordinate = getNewCoordinate(direction);

        AbstractGameObject objectInNewCoordinate = abstractGameMap.getGameCollection().getObjectByCoordinate(newCoordinate);

        switch (objectInNewCoordinate.getType()) {
            case NOTHING -> {
                changeIcon(direction);
                this.setCoordinate(newCoordinate);
            }
            default -> { }
        }

    }

    public Coordinate getNewCoordinate(MovingDirection direction) {
        // берем текущие координаты объекта, которые нужно передвинуть
        int x = this.getCoordinate().getX();
        int y = this.getCoordinate().getY();

        Coordinate newCoordinate = new Coordinate(x, y);

        switch (direction) {
            case UP -> {
                newCoordinate.setXY(x, y - 1);
            }
            case DOWN -> {
                newCoordinate.setXY(x, y + 1);
            }
            case RIGHT -> {
                newCoordinate.setXY(x + 1, y);
            }
            case LEFT -> {
                newCoordinate.setXY(x - 1, y);
            }
        }

        return newCoordinate;
    }



}
