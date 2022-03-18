package objects.listeners;

import abstracts.AbstractMovingObject;
import enums.ActionResult;

public interface MoveResultListener {

    void notifyActionResult(ActionResult actionResult, AbstractMovingObject movingObject);
}
