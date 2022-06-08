package gamemap.loader.impls;

import collections.impls.MapCollection;
import collections.interfaces.GameCollection;
import database.SQLiteConnection;
import enums.GameObjectType;
import gamemap.abstracts.AbstractGameMap;
import gamemap.loader.abstracts.AbstractMapLoader;
import gameobjects.impls.Coordinate;
import gameobjects.impls.Goldman;
import objects.MapInfo;
import objects.SavedMapInfo;
import objects.User;
import utils.ObjectByteCreator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DBMapLoader extends AbstractMapLoader {

    public DBMapLoader(AbstractGameMap gameMap) {
        super(gameMap);
    }

    @Override
    public boolean saveMap(SavedMapInfo mapInfo) {
        PreparedStatement insertStmt = null;
        SQLiteConnection sqliteDB = SQLiteConnection.getInstance();

        try {
            insertStmt = sqliteDB.getConnection().prepareStatement("insert into saved_game(player_id, save_date, collection, total_score, map_id, turns_count) values(?,?,?,?,?,?)");
            insertStmt.setInt(1, mapInfo.getUser().getId());
            insertStmt.setLong(2, new Date().getTime());
            insertStmt.setBytes(3, ObjectByteCreator.getBytes(getGameMap().getGameCollection()));
            insertStmt.setInt(4, mapInfo.getTotalScore());
            insertStmt.setInt(5, mapInfo.getId());
            insertStmt.setInt(6, mapInfo.getTurnsCount());

            int result = insertStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertStmt != null) {
                    insertStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean loadMap(MapInfo mapInfo) {
        if (mapInfo.getLevelId() > 0) {
            return createNewMap(mapInfo.getLevelId());
        }

        if (mapInfo.getId() > 0) {
            return loadMap(mapInfo.getId());
        }

        return false;
    }

    @Override
    public ArrayList<SavedMapInfo> getSavedMapList(User user) {
        ArrayList<SavedMapInfo> list = new ArrayList<>();
        PreparedStatement selectStmt = null;
        ResultSet rs = null;
        SQLiteConnection sqliteDB = SQLiteConnection.getInstance();

        try {
            selectStmt = sqliteDB.getConnection().prepareStatement("select * from saved_game where player_id = ? order by total_score desc");

            selectStmt.setInt(1, user.getId());

            rs = selectStmt.executeQuery();

            while (rs.next()) {
                SavedMapInfo mapInfo = new SavedMapInfo();
                mapInfo.setId(rs.getInt("id"));
                mapInfo.setSaveDate(rs.getLong("save_date"));
                mapInfo.setUser(user);
                mapInfo.setTotalScore(rs.getInt("total_score"));
                mapInfo.setTurnsCount(rs.getInt("turns_count"));
                list.add(mapInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return list;
    }

    @Override
    public boolean deleteSavedMap(MapInfo mapInfo) {
        PreparedStatement deleteStmt = null;
        SQLiteConnection sqliteDB = SQLiteConnection.getInstance();

        try {
            deleteStmt = sqliteDB.getConnection().prepareStatement("delete from saved_game where id = ?");
            deleteStmt.setInt(1, mapInfo.getUser().getId());

            int result = deleteStmt.executeUpdate();

            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (deleteStmt != null) {
                    deleteStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean createNewMap(int levelId) {
        PreparedStatement selectStmt = null;
        ResultSet res = null;
        SQLiteConnection sqliteDB = SQLiteConnection.getInstance();

        try {
            gameMap.setGameCollection(new MapCollection());

            selectStmt = sqliteDB.getConnection().prepareStatement("select * from map where level_id = ? limit 1");

            selectStmt.setInt(1, levelId);

            res = selectStmt.executeQuery();

            while (res.next()) {
                gameMap.getMapInfo().setId(res.getInt("id"));
                gameMap.getMapInfo().setMapName(res.getString("map_name"));
                gameMap.getMapInfo().setTurnsLimit(res.getInt("turns_limit"));
                gameMap.getMapInfo().setWidth(res.getInt("width"));
                gameMap.getMapInfo().setHeight(res.getInt("height"));
                gameMap.getMapInfo().setLevelId(levelId);
                gameMap.getMapInfo().setValue(res.getString("value"));
            }

            String[] lines = gameMap.getMapInfo().getValue().split(System.getProperty("line.separator"));

            gameMap.getMapInfo().setGoldManExists(false);
            gameMap.getMapInfo().setExitExists(false);

            int y = 0; // номер строки в массиве
            int x = 0; // номер столбца в массиве
            
            for (int i = 0; i < lines.length; i++) {
                x = 0; // чтобы каждый раз начинал с первого столбца

                for (String cell :
                        lines[i].split(",")) {
                    createGameObject(cell, new Coordinate(x, y));
                    x++;
                }
                y++;
            }

            if (!gameMap.isValidMap()) {
                throw new Exception("The map is not valid!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (selectStmt != null) {
                    selectStmt.close();
                }
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private boolean loadMap(int id) {
        PreparedStatement selectStmt = null;
        ResultSet res = null;
        SQLiteConnection sqliteDB = SQLiteConnection.getInstance();

        try {
            selectStmt = sqliteDB.getConnection().prepareStatement("select "
                    + " g.player_id,"
                    + " g.save_date,"
                    + " g.collection,"
                    + " g.total_score,"
                    + " g.turns_count,"
                    + " m.height,"
                    + " m.width,"
                    + " m.turns_limit,"
                    + " m.map_name,"
                    + " m.level_id"
                    + " from saved_game g inner join map m on m.id = g.map_id where g.id = ?");
            selectStmt.setInt(1, id);

            res = selectStmt.executeQuery();

            while (res.next()) {
                gameMap.setGameCollection((GameCollection) ObjectByteCreator.getObject(res.getBytes("collection")));
                gameMap.getMapInfo().setMapName(res.getString("map_name"));
                gameMap.getMapInfo().setHeight(res.getInt("height"));
                gameMap.getMapInfo().setWidth(res.getInt("width"));
                gameMap.getMapInfo().setTurnsLimit(res.getInt("turns_limit"));
                //gameMap.getMapInfo().setLevelId(res.getInt("level_id"));

                Goldman goldMan = (Goldman)gameMap.getGameCollection().getListOfDefinitObjects(GameObjectType.GOLDMAN).get(0);
                goldMan.setTurnsNumber(res.getInt("turns_count"));
                goldMan.addTotalScore(res.getInt("total_score"));
            }
            gameMap.getMapInfo().setExitExists(false);
            gameMap.getMapInfo().setGoldManExists(false);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (selectStmt != null) {
                try {
                    if (selectStmt != null) {
                        selectStmt.close();
                    }
                    if (res != null) {
                        res.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

}
