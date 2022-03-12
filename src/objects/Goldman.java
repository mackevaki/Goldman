package objects;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.GameObjectType;
import enums.MovingDirection;

/**
 * класс отвечает за работу объекта GOLDMAN - главный персонаж игры
 */
public class Goldman extends AbstractMovingObject {
    
    private int totalScore = 0; // количество собранных игроком очков
    private int turnsNumber = 0; // количество сделанных ходов
    
    public Goldman(Coordinate coordinate) {
        super.setCoordinate(coordinate);
        super.setType(GameObjectType.GOLDMAN);
        super.setIcon(getImageIcon("/images/goldman_up.png"));
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addTotalScore(int totalScore) {
        this.totalScore += totalScore;
    }

    public int getTurnsNumber() {
        return turnsNumber;
    }

    public void setTurnsNumber(int turnsNumber) {
        this.turnsNumber = turnsNumber;
    }

    @Override
    public void changeIcon(MovingDirection direction) {
        switch (direction) {
            case LEFT -> super.setIcon(getImageIcon("/images/goldman_left.png"));
            case RIGHT -> super.setIcon(getImageIcon("/images/goldman_right.png"));
            case DOWN -> super.setIcon(getImageIcon("/images/goldman_down.png"));
            case UP -> super.setIcon(getImageIcon("/images/goldman_up.png"));
        }
    }
}
