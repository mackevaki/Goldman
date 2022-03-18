package movestrategies;

import abstracts.AbstractGameObject;
import abstracts.AbstractMovingObject;
import enums.GameObjectType;
import enums.MovingDirection;
import interfaces.gamemaps.collections.GameCollection;
import objects.Goldman;

import java.util.Random;

public class SlowMoving implements MoveStrategy {

    @Override
    public MovingDirection getDirection(AbstractMovingObject movingObject, AbstractGameObject targetObject, GameCollection gameCollection) {
        //Goldman goldman = (Goldman) gameCollection.getListOfDefinitObjects(GameObjectType.GOLDMAN).get(0);

        MovingDirection direction = null;

        int characterX = targetObject.getCoordinate().getX();
        int characterY = targetObject.getCoordinate().getY();

        int monsterX = movingObject.getCoordinate().getX();
        int monsterY = movingObject.getCoordinate().getY();

        int randomDirectionInt = getRandomInt(2);

        if (randomDirectionInt == 1) { // 50 на 50 шанс двинуться по направлению к персонажу, 0 - к персонажу, 1 - от него
            randomDirectionInt = getRandomInt(2); // рандомно определяем по какой оси двигаемся к персонажу
            switch (randomDirectionInt) {
                case 1: { //движемся к персонажу по X
                    if (monsterX > characterX) {
                        direction = MovingDirection.LEFT;
                    } else {
                        direction = MovingDirection.RIGHT;
                    }
                    break;
                }
                case 2: { // движемся к персонажу по Y
                    if (monsterY > characterY) {
                        direction = MovingDirection.UP;
                    } else {
                        direction = MovingDirection.DOWN;
                    }
                    break;
                }
            }
        } else { // 1 - двигаться от персонажа
            randomDirectionInt = getRandomInt(2); // по какой оси от персонажа - Х или У

            switch (randomDirectionInt) {
                case 1: { // по Х
                    if (monsterX > characterX) {
                        direction = MovingDirection.RIGHT;
                    } else {
                        direction = MovingDirection.LEFT;
                    }
                    break;
                }
                case 2: { // по У
                    if (monsterY > characterY) {
                        direction = MovingDirection.DOWN;
                    } else {
                        direction = MovingDirection.UP;
                    }
                    break;
                }
            }
        }

        return direction;
    }

    private int getRandomInt(int i) {
        Random r = new Random();
        return r.nextInt(i) + 1;
    }

}
