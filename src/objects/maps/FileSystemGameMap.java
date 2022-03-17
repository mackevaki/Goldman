package objects.maps;

import abstracts.AbstractGameMap;
import abstracts.AbstractGameObject;
import enums.GameObjectType;
import interfaces.gamemaps.collections.GameCollection;
import objects.Coordinate;
import objects.creators.GameObjectCreator;

import java.io.*;

public class FileSystemGameMap extends AbstractGameMap {

    public FileSystemGameMap() {
        super();
    }

    public FileSystemGameMap(GameCollection gameCollection) {
        super(gameCollection);
    }

    @Override
    public boolean loadMap(Object source) {
        File file = new File(source.toString());
        if (!file.exists()) {
            throw new IllegalArgumentException("filename mist not be empty");
        }

        try {
            setExitExists(false); // сбрасываем значения переменных
            setGoldManExists(false);

            setHeight(getLineCount(file));

            BufferedReader br = new BufferedReader(new FileReader(file));

            String strLine = br.readLine().trim(); // считываем первую строку для поределения имени, длины, ширины карты, убрав пробелы по краям

            // разбираем первую строку на токены, разделенные запятой
            setName(strLine.split(",")[0]);

            setTimeLimit(Integer.valueOf(strLine.split(",")[1]).intValue());
            setWidth(Integer.valueOf(strLine.split(",")[2]).intValue());

            int x = 0; // номер столбца в массиве
            int y = 0; // номер строки в массиве

            while ((strLine = br.readLine()) != null) {
                x = 0; // чтобы каждый раз начинал с 1 столбца

                for (String str :
                        strLine.split(",")) {
                    createGameObject(str, new Coordinate(x, y));
                    x++;
                }
                y++;
            }

            if (!isValidMap()) {
                throw new Exception("The map is not valid");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createGameObject(String str, Coordinate coordinate) {
        GameObjectType type = GameObjectType.valueOf(str.toUpperCase());

        AbstractGameObject newObj = GameObjectCreator.getInstance().createObject(type, coordinate);

        getGameCollection().addGameObject(newObj);

        if (newObj.getType() == GameObjectType.EXIT) {
            setExitExists(true);
        } else if (newObj.getType() == GameObjectType.GOLDMAN) {
            setGoldManExists(true);
        }
    }

    // количество строк в файле == высота карты
    private int getLineCount(File file) {
        BufferedReader reader = null;
        int lineCount = -1; // потому что первая строка файла не входит в карту
        try {
            reader = new BufferedReader(new FileReader(file));

            while (reader.readLine() != null) {
                lineCount += 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lineCount;
    }

    @Override
    public boolean saveMap(Object source) {
        throw new IllegalArgumentException("Not supported yet");
    }

}
