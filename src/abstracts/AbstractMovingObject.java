package abstracts;

import enums.MovingDirection;
import interfaces.gameobjects.MovingObject;
import objects.Coordinate;

import javax.swing.*;

public abstract class AbstractMovingObject extends AbstractGameObject implements MovingObject {

    private ImageIcon iconLeft;
    private ImageIcon iconRight;
    private ImageIcon iconUp;
    private ImageIcon iconDown;

    @Override
    public void move(MovingDirection direction) {
        // берем текущие координаты объекта, которые нужно передвинуть
        int x = this.getCoordinate().getX();
        int y = this.getCoordinate().getY();

        Coordinate newCoordinate = new Coordinate(x, y);

        switch (direction) {
            case UP -> {
                super.setIcon(iconUp);
                newCoordinate.setXY(x, y - 1);
            }
            case DOWN -> {
                super.setIcon(iconDown);
                newCoordinate.setXY(x, y + 1);
            }
            case RIGHT -> {
                super.setIcon(iconRight);
                newCoordinate.setXY(x + 1, y);
            }
            case LEFT -> {
                super.setIcon(iconLeft);
                newCoordinate.setXY(x - 1, y);
            }
        }
        this.setCoordinate(newCoordinate);
    }

    @Override
    public void getMoveResult(AbstractGameObject objectInNewCoordinate) {

    }

    public void setIconLeft(ImageIcon iconLeft) {
        this.iconLeft = iconLeft;
    }

    @Override
    public ImageIcon getIconLeft() {
        return iconLeft;
    }

    public void setIconRight(ImageIcon iconRight) {
        this.iconRight = iconRight;
    }

    @Override
    public ImageIcon getIconRight() {
        return iconRight;
    }

    public void setIconUp(ImageIcon iconUp) {
        this.iconUp = iconUp;
    }

    @Override
    public ImageIcon getIconUp() {
        return iconUp;
    }

    public void setIconDown(ImageIcon iconDown) {
        this.iconDown = iconDown;
    }

    @Override
    public ImageIcon getIconDown() {
        return iconDown;
    }
}
