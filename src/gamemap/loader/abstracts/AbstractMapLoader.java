package gamemap.loader.abstracts;

import creators.GameObjectCreator;
import database.SQLiteConnection;
import enums.GameObjectType;
import gamemap.abstracts.AbstractGameMap;
import gamemap.loader.interfaces.MapLoader;
import gameobjects.abstracts.AbstractGameObject;
import gameobjects.impls.Coordinate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractMapLoader implements MapLoader {

    protected AbstractGameMap gameMap;

    protected AbstractMapLoader(AbstractGameMap abstractGameMap) {
        this.gameMap = abstractGameMap;
    }

    protected void createGameObject(String str, Coordinate coordinate) {
        GameObjectType type = GameObjectType.valueOf(str.toUpperCase());

        AbstractGameObject newObj = GameObjectCreator.getInstance().createObject(type, coordinate);

        gameMap.getGameCollection().addGameObject(newObj);

        if (newObj.getType() == GameObjectType.EXIT) {
            gameMap.getMapInfo().setExitExists(true);
        } else if (newObj.getType() == GameObjectType.GOLDMAN) {
            gameMap.getMapInfo().setGoldManExists(true);
        }
    }

    public AbstractGameMap getGameMap() {
        return gameMap;
    }

    public int getPlayerId(String username) {
        PreparedStatement selectStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet res = null;

        try {
            // если есть уже такой пользователь - получить его id
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select id from player where username = ?");
            selectStmt.setString(1, username);

            res = selectStmt.executeQuery();

            if (res.next()) {
                return res.getInt(1);
            }

            selectStmt.close();

            // если нет такого пользователя - создать его и вернуть id
            insertStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("insert into player(username) values(?)");
            insertStmt.setString(1, username);
            insertStmt.executeUpdate();

            // получить id созданного пользователя
            selectStmt = SQLiteConnection.getInstance().getConnection().prepareStatement("select last_insert_rowid()");
            return selectStmt.executeQuery().getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
                }
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
