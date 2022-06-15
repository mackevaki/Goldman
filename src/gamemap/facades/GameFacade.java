package gamemap.facades;

import enums.GameObjectType;
import enums.LocationType;
import enums.MovingDirection;
import gamemap.abstracts.AbstractGameMap;
import gamemap.adapters.HybridMapLoader;
import gameobjects.impls.Goldman;
import listeners.interfaces.MoveResultListener;
import objects.MapInfo;
import objects.SavedMapInfo;
import score.interfaces.ScoreSaver;
import score.objects.UserScore;
import sound.impls.WavPlayer;
import sound.interfaces.SoundPlayer;

import java.awt.*;

public class GameFacade {
    private HybridMapLoader mapLoader;
    private ScoreSaver scoreSaver;
    private MapInfo mapInfo;
    private SoundPlayer soundPlayer;
    private AbstractGameMap gameMap;

    public GameFacade(HybridMapLoader mapLoader, ScoreSaver scoreSaver, SoundPlayer soundPlayer) {
        this.mapLoader = mapLoader;
        this.scoreSaver = scoreSaver;
        this.soundPlayer = soundPlayer;
    }

    private void init() {
        gameMap = mapLoader.getGameMap();
        mapInfo = mapLoader.getGameMap().getMapInfo();

        // слушатели для звуков должны идти в первую очередь, т.к. они запускаются в отдельном потоке и не мешают выполняться следующим слушателям
        if (soundPlayer instanceof MoveResultListener) {
            mapLoader.getGameMap().getGameCollection().addMoveListener((MoveResultListener) soundPlayer);
        }
    }

    public GameFacade() {
    }

    public void setSoundPlayer(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
    }

    public void setMapLoader(HybridMapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    public void setScoreSaver(ScoreSaver scoreSaver) {
        this.scoreSaver = scoreSaver;
    }

    public void stopGame() {
        soundPlayer.stopBackgroundMusic();
        mapLoader.getGameMap().stop();
    }

    public void moveGameObject(MovingDirection movingDirection, GameObjectType gameObjectType) {
        gameMap.getGameCollection().moveObject(movingDirection, gameObjectType);
    }

    public Component getMap() {
        init();
        gameMap.drawMap();
        return mapLoader.getGameMap().getMapComponent();
    }

    public void saveScore() {
        UserScore userScore = new UserScore();
        userScore.setUser(mapInfo.getUser());
        userScore.setScore(getGoldman().getTotalScore());
        scoreSaver.saveScore(userScore);
    }

    public void addMoveListener(MoveResultListener listener) {
        mapLoader.getGameMap().getGameCollection().addMoveListener(listener);
    }

    public void saveMap() {
        SavedMapInfo saveMapInfo = new SavedMapInfo();
        saveMapInfo.setId(mapInfo.getId());
        saveMapInfo.setUser(mapInfo.getUser());
        saveMapInfo.setTotalScore(getGoldman().getTotalScore());
        saveMapInfo.setTurnsCount(getGoldman().getTurnsNumber());
        mapLoader.saveMap(saveMapInfo, LocationType.DB);
    }

    public void startGame() {
        soundPlayer.startBackgroundMusic(WavPlayer.WAV_BACKGROUND);
        mapLoader.getGameMap().start();
    }

    private Goldman getGoldman() {
        return (Goldman) mapLoader.getGameMap().getGameCollection().getGameObjectsByType(GameObjectType.GOLDMAN).get(0);
    }

    public int getTurnsLeftCount() {
        return mapInfo.getTurnsLimit() - getGoldman().getTurnsNumber();
    }

    public int getTotalScore() {
        return getGoldman().getTotalScore();
    }

    public void updateMap() {
        gameMap.drawMap();
    }
}
