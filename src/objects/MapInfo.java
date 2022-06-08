package objects;

public class MapInfo {

    private User user = new User();

    private int id;
    private String mapName;
    private String value;
    private int levelId;
    private int width;
    private int height;
    private int turnsLimit;
    private boolean isExitExists;
    private boolean isGoldManExists;

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getTurnsLimit() {
        return turnsLimit;
    }

    public void setTurnsLimit(int turnsLimit) {
        this.turnsLimit = turnsLimit;
    }

    public boolean isExitExists() {
        return isExitExists;
    }

    public void setExitExists(boolean exitExists) {
        isExitExists = exitExists;
    }

    public boolean isGoldManExists() {
        return isGoldManExists;
    }

    public void setGoldManExists(boolean goldManExists) {
        isGoldManExists = goldManExists;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
