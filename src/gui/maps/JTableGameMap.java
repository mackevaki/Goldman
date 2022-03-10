package gui.maps;

import abstracts.AbstractGameMap;
import abstracts.AbstractGameObject;
import enums.GameObjectType;
import interfaces.collections.GameCollection;
import interfaces.gamemaps.DrawableMap;
import enums.LocationType;
import objects.Coordinate;
import objects.Nothing;
import objects.Wall;
import objects.creators.MapCreator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class JTableGameMap implements DrawableMap {

    private JTable jTableMap = new JTable();

    private AbstractGameMap gameMap;
    private String[] columnNames;
    // объекты для отображения на карте будут храниться в двумерном массиве типа AbstractGameObject
    // каждый элемент массива будет обозначаться согласно текстовому представлению объекта как описано в GameObjectType
    private AbstractGameObject[][] mapObjects;

    public JTableGameMap(LocationType type, Object source, GameCollection gameCollection) {
        jTableMap.setEnabled(false); // отображение карты в таблице не кликабельно
        jTableMap.setSize(new Dimension(300, 300));
        jTableMap.setRowHeight(26);
        jTableMap.setRowSelectionAllowed(false);
        jTableMap.setShowHorizontalLines(false);
        jTableMap.setShowVerticalLines(false);
        jTableMap.setTableHeader(null);
        jTableMap.setUpdateSelectionOnSort(false); //???
        jTableMap.setVerifyInputWhenFocusTarget(false); // ???

        gameMap = MapCreator.getInstance().createMap(type, gameCollection);
        gameMap.loadMap(source);

    }

    private void fillEmptyMap(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapObjects[y][x] = new Nothing(new Coordinate(x, y));
            }
        }
    }

    public void updateObjectsArray() {
        mapObjects = new AbstractGameObject[gameMap.getHeight()][gameMap.getWidth()];
        fillEmptyMap(gameMap.getWidth(), gameMap.getHeight());

        // потом заполнить массив объектами
        for (AbstractGameObject obj :
                gameMap.getGameCollection().getAllGameObjects()) {
            if (!obj.getType().equals(GameObjectType.NOTHING)) { // пустоты не добавляем, так как они уже добавились при вызове метода fillEmptyMap
                int y = obj.getCoordinate().getY();
                int x = obj.getCoordinate().getX();
                if (!(mapObjects[y][x] instanceof Nothing) & // если в этих координатах уже есть какой-то объект, отличный от стены и пустоты
                        !((mapObjects[y][x]) instanceof Wall)) {
                    AbstractGameObject tmpObj = mapObjects[y][x];
                    mapObjects[y][x] = gameMap.getObjectPriority(tmpObj, obj);
                } else {
                    mapObjects[y][x] = obj;
                }

            }
        }
    }

    @Override
    public Component getMap() {
        return jTableMap;
    }

    public AbstractGameMap getGameMap() {
        return gameMap;
    }

    @Override
    public boolean drawMap() {

        updateObjectsArray();

        try {
            // присваиваем пустоту всем заголовкам столбцов, чтобы у таблицы не было заголовоков, а то некрасиво смотрится
            columnNames = new String[gameMap.getWidth()];
            for (int i = 0; i < columnNames.length; i++) {
                columnNames[i] = "";
            }

            // игровое поле будет отображаться в super без заголовков столбцов
            jTableMap.setModel(new DefaultTableModel(mapObjects, columnNames));

            // вместо текста в яйчейках таблицы устанавливаем отображение картинок
            for (int i = 0; i < jTableMap.getColumnCount(); i++) {
                jTableMap.getColumnModel().getColumn(i).setCellRenderer(new ImageRenderer());
                TableColumn a = jTableMap.getColumnModel().getColumn(i);
                a.setPreferredWidth(26);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
