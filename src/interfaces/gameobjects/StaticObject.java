package interfaces.gameobjects;

import objects.Coordinate;
import enums.GameObjectType;

import javax.swing.*;

public interface StaticObject {
    // объект должен иметь иконку
    ImageIcon getIcon();

    // координаты
    Coordinate getCoordinate();

    // тип объекта
    GameObjectType getType();
}
