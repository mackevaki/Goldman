package objects.listeners;

import enums.ActionResult;
import objects.Goldman;

import java.util.List;

public interface MoveResultNotifier {

    List<MoveResultListener> getMoveListeners();

    void addMoveListener(MoveResultListener listener);

    void removeMoveListener(MoveResultListener listener);

    void removeAllMoveListeners();

     void notifyMoveListeners(ActionResult actionResult, Goldman goldman);

}