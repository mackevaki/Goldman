package interfaces.gameobjects;

import abstracts.AbstractGameObject;
import enums.MovingDirection;
import interfaces.gameobjects.StaticObject;

import javax.swing.*;

public interface MovingObject extends StaticObject {
    void move(MovingDirection direction);

    void getMoveResult(AbstractGameObject objectInNewCoordinate);

    // иконки при движении в разные стороны
    ImageIcon getIconLeft();

    ImageIcon getIconRight();

    ImageIcon getIconUp();

    ImageIcon getIconDown();
}
