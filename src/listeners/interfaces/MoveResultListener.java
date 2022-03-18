package listeners.interfaces;

import gameobjects.abstracts.AbstractMovingObject;
import enums.ActionResult;

public interface MoveResultListener {

    void notifyActionResult(ActionResult actionResult, AbstractMovingObject movingObject);
}
