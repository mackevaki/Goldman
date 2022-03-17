package objects.listeners;

import enums.ActionResult;
import objects.Goldman;

public interface MoveResultListener {

    void notifyActionResult(ActionResult actionResult, Goldman goldman);
}
