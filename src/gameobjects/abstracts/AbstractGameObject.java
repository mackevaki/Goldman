package gameobjects.abstracts;

import gameobjects.impls.Coordinate;
import enums.GameObjectType;
import gameobjects.interfaces.StaticObject;

import javax.swing.*;
import java.io.Serializable;

/**
 * класс, который отвечает за любой объект, созданный в игре. задает все общие
 * характеристики объектов в игре
 */
public abstract class AbstractGameObject implements StaticObject, Serializable {

    private GameObjectType type; // все объекты будут иметь тип
    private Coordinate coordinate; // все объекты будут иметь координаты их положения

    private ImageIcon icon = getImageIcon("/images/noicon.png"); // изображение по умолчанию

    protected AbstractGameObject() {

    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    protected ImageIcon getImageIcon(String path) {
        return new ImageIcon(getClass().getResource(path));
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setType(GameObjectType type) {
        this.type = type;
    }

    @Override
    public GameObjectType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractGameObject)) return false;

        AbstractGameObject that = (AbstractGameObject) o;

        if (type != that.type) return false;
        return coordinate.equals(that.coordinate);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + coordinate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "type=" + type +
                ", coordinate=" + coordinate;
    }
}
