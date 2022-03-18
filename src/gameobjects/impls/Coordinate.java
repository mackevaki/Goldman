package gameobjects.impls;

import java.io.Serializable;

public class Coordinate implements Serializable {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if(!(obj instanceof Coordinate)) return false;
        Coordinate objCoord = (Coordinate) obj;
        return (x == objCoord.getX()) && (y == objCoord.getY());
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }
}
