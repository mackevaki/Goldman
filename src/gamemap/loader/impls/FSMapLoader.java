package gamemap.loader.impls;

import gamemap.abstracts.AbstractGameMap;
import gamemap.loader.abstracts.AbstractMapLoader;
import gameobjects.impls.Coordinate;
import objects.MapInfo;
import objects.SavedMapInfo;
import objects.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class FSMapLoader extends AbstractMapLoader {
    private static final String SAVE_EXTENSION = ".sav";

    public FSMapLoader(AbstractGameMap gameMap) {
        super(gameMap);
    }


    @Override
    public boolean loadMap(MapInfo mapInfo) {
        File file = new File("game.map");
        if (!file.exists()) {
            throw new IllegalArgumentException("filename must not be empty!");
        }

        try {
            gameMap.getGameCollection().clear();

            gameMap.getMapInfo().setExitExists(false);
            gameMap.getMapInfo().setGoldManExists(false);

            gameMap.getMapInfo().setHeight(getLineCount(file));

            BufferedReader br = new BufferedReader(new FileReader(file));

            String strLine = br.readLine().trim(); // считываем первую строку для определения имени, длины, ширины карты. убираем пробела по краям

            // разбиваем первую строку на токены, разделенные запятой.
            gameMap.getMapInfo().setMapName(strLine.split(",")[0]);
            gameMap.getMapInfo().setId(1);

            gameMap.getMapInfo().setTurnsLimit(Integer.valueOf(strLine.split(",")[1]).intValue());
            gameMap.getMapInfo().setWidth(Integer.valueOf(strLine.split(",")[2]).intValue());

            int y = 0; // номер строки в массиве
            int x = 0; // номер столбца в массиве

            while ((strLine = br.readLine()) != null) {
                x = 0; // чтобы каждый раз с первого столбца начинал

                for (String str : strLine.split(",")) {
                    // вытаскивать все значения в строке между запятыми, чтобы заполнить карту элементами

                    createGameObject(str, new Coordinate(x, y));
                    x++;
                }
                y++;
            }

            if (!gameMap.isValidMap()) {
                throw new Exception("The map is not valid!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    // количество строк в файле == высота карты
    private int getLineCount(File file) {
        BufferedReader reader = null;
        int lineCount = -1; // потому что первая строка файла не входит в карту
        try {
            reader = new BufferedReader(new FileReader(file));

            while (reader.readLine() != null) {
                lineCount ++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lineCount;
    }

    @Override
    public boolean saveMap(SavedMapInfo savedMapInfo) {
        File f = new File("save");

        if (!f.exists()) {
            f.mkdir();
        }

        try {
            FileOutputStream fos = new FileOutputStream(f.getAbsoluteFile() + "/" + savedMapInfo.getUser().getUserName() + "_" + new Date().getTime() + SAVE_EXTENSION);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<SavedMapInfo> getSavedMapList(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteSavedMap(MapInfo mapInfo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
