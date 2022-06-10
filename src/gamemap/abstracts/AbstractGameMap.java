package gamemap.abstracts;

import collections.interfaces.GameCollection;
import gamemap.interfaces.TimeMap;
import gameobjects.abstracts.AbstractGameObject;
import objects.MapInfo;

import java.io.Serializable;

/**
 * базовая карта без какого-либо отображения
 */
public abstract class AbstractGameMap implements TimeMap, Serializable {

    private static final long serialVersionUID = 1L; // переменная нужна для сериализации и десериализации
    protected GameCollection gameCollection;
    protected MapInfo mapInfo = new MapInfo();

    public AbstractGameMap() {
        //gameCollection = new MapCollection();
    }

    public AbstractGameMap(GameCollection gameCollection) {
        this.gameCollection = gameCollection;
    }

    public GameCollection getGameCollection() {
        if (gameCollection == null) {
            try {
                throw new Exception("Game collection is not initialized");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return gameCollection;
    }

    public void setGameCollection(GameCollection gameCollection) {
        this.gameCollection = gameCollection;
    }

    public AbstractGameObject getObjectPriority(AbstractGameObject firstObject, AbstractGameObject secondObject) {
        // приоритет объекта зависит от номера индекса объекта в enum
        return (firstObject.getType().getIndexPriority() > secondObject.getType().getIndexPriority()) ? firstObject : secondObject;
    }

    public boolean isValidMap() {
        return mapInfo.isExitExists() && mapInfo.isGoldManExists(); // если есть вход и выход - крата валидна
    }

    @Override
    public MapInfo getMapInfo() {
        return mapInfo;
    }

    public void setMapInfo(MapInfo mapInfo) {
        this.mapInfo = mapInfo;
    }
}
