package collections.abstracts;

import collections.interfaces.GameCollection;
import listeners.interfaces.MoveResultListener;

import java.util.ArrayList;
import java.util.List;

public abstract class MapMoveListenerRegistrator implements GameCollection {
    private ArrayList<MoveResultListener> listeners = new ArrayList<>();

    @Override
    public List<MoveResultListener> getMoveListeners() {
        return listeners;
    }

    @Override
    public void addMoveListener(MoveResultListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeMoveListener(MoveResultListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeAllMoveListeners() {
        listeners.clear();
    }

}
