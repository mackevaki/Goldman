package movestrategies.impls;

import gameobjects.abstracts.AbstractGameObject;
import gameobjects.abstracts.AbstractMovingObject;
import enums.ActionResult;
import enums.MovingDirection;
import collections.interfaces.GameCollection;
import movestrategies.interfaces.MoveStrategy;

import java.util.Random;

public class AggressiveMoving implements MoveStrategy {

    private MovingDirection[] directions = MovingDirection.values();
    private AbstractMovingObject movingObject;
    private GameCollection gameCollection;

    @Override
    public MovingDirection getDirection(AbstractMovingObject movingObject, AbstractGameObject targetObject, GameCollection gameCollection) {

        this.movingObject = movingObject;
        this.gameCollection = gameCollection;

        // сначала ищем вокруг, можно ли съесть игрока
        MovingDirection direction = searchAction(ActionResult.DIE, false);

        // если нет рядом персонажа - просто двигаемся в случайном направлении
        if (direction == null) {
            direction = searchAction(ActionResult.MOVE, true);
        }

        return direction;
    }

    private MovingDirection searchAction(ActionResult actionResult, boolean random) {
        int c = 0;

        MovingDirection direction = null;

        if (random) {
            do {
                direction = getRandomDirection();
                c++;
                if (c > 4) {
                    return direction;
                }
            } while (!checkActionResult(actionResult, direction)); //  случайно подбирать сторону, пока не найдем нужный ActionResult
        } else {
            for (MovingDirection movingDirection : directions) { // искать по всем 4 сторонам
                if (checkActionResult(actionResult, movingDirection)) {
                    direction = movingDirection;
                    break;
                }
            }
        }

        return direction;
    }

    private boolean checkActionResult(ActionResult actionResult, MovingDirection direction) {
        AbstractGameObject objectInNewCoordinate = gameCollection.getObjectByCoordinate(movingObject.getNewCoordinateInDirection(direction));
        return movingObject.doAction(objectInNewCoordinate).equals(actionResult);
    }

    private Random random = new Random();

    private MovingDirection getRandomDirection() {
        return directions[random.nextInt(directions.length)];
    }


}
