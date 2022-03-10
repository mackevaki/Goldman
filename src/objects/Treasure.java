package objects;

import abstracts.AbstractGameObject;
import enums.GameObjectType;

/**
 * класс отвечает за работу объекта TREASURE
 */
public class Treasure extends AbstractGameObject {
    public Treasure(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.TREASURE);
        super.setIcon(getImageIcon("/images/gold.png"));
    }

    private int score = 5; // за каждое сокровище игрок будет получать по 5 очков

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
