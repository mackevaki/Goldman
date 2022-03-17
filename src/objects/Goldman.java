package objects;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.ActionResult;
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

    /**
     * @param gameObject - объект, с которым будем взаимодействовать
     * @return
     */
    @Override
    public ActionResult doAction(AbstractGameObject gameObject) {
        turnsNumber++;

        if (gameObject == null) { // край карты
            return ActionResult.NO_ACTION;
        }

        switch (gameObject.getType()) {
            case MONSTER -> {
                return ActionResult.DIE;
            }
            case TREASURE -> {
                totalScore += ((Treasure)gameObject).getScore();
                return ActionResult.COLLECT_TREASURE;
            }
            case EXIT -> {
                totalScore *= 2;
                return ActionResult.WIN;
            }
        }

        return super.doAction(gameObject);
    }
}
