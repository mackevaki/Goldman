package listeners.interfaces;

import gameobjects.abstracts.AbstractMovingObject;
import enums.ActionResult;

import java.util.List;

public interface MoveResultNotifier {

    List<MoveResultListener> getMoveListeners();

    void addMoveListener(MoveResultListener listener);

    void removeMoveListener(MoveResultListener listener);

    void removeAllMoveListeners();

     void notifyMoveListeners(ActionResult actionResult, AbstractMovingObject movingObject);

}
