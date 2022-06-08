package gamemap.impls;

import enums.ActionResult;
import enums.GameObjectType;
import gamemap.abstracts.AbstractGameMap;
import gamemap.interfaces.TimeMap;
import gameobjects.abstracts.AbstractGameObject;
import gameobjects.abstracts.AbstractMovingObject;
import gameobjects.impls.Coordinate;
import gameobjects.impls.Nothing;
import gameobjects.impls.Wall;
import listeners.interfaces.MoveResultListener;
import movestrategies.impls.AggressiveMoving;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTableGameMap extends AbstractGameMap implements TimeMap {

    private transient JTable jTableMap = new JTable();
    private transient TimeMover timeMover = new TimeMover();
    private transient String[] columnNames;
    // объекты для отображения на карте будут храниться в двумерном массиве типа AbstractGameObject
    // каждый элемент массива будет обозначаться согласно текстовому представлению объекта как описано в GameObjectType
    private transient AbstractGameObject[][] mapObjects;

    public JTableGameMap() {
        try {
            jTableMap.setEnabled(false); // отображение карты в таблице не кликабельно
            jTableMap.setSize(new Dimension(300, 300));
            jTableMap.setRowHeight(26);
            jTableMap.setRowSelectionAllowed(false);
            jTableMap.setShowHorizontalLines(false);
            jTableMap.setShowVerticalLines(false);
            jTableMap.setTableHeader(null);
            jTableMap.setUpdateSelectionOnSort(false); //???
            jTableMap.setVerifyInputWhenFocusTarget(false); // ???
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillEmptyMap(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mapObjects[y][x] = new Nothing(new Coordinate(x, y));
            }
        }
    }

    public void updateObjectsArray() {
        mapObjects = new AbstractGameObject[mapInfo.getHeight()][mapInfo.getWidth()];
        fillEmptyMap(mapInfo.getWidth(), mapInfo.getHeight());

        // потом заполнить массив объектами
        for (AbstractGameObject obj :
                getGameCollection().getAllGameObjects()) {
            if (!obj.getType().equals(GameObjectType.NOTHING)) { // пустоты не добавляем, так как они уже добавились при вызове метода fillEmptyMap
                int y = obj.getCoordinate().getY();
                int x = obj.getCoordinate().getX();
                if (!(mapObjects[y][x] instanceof Nothing) & // если в этих координатах уже есть какой-то объект, отличный от стены и пустоты
                        !((mapObjects[y][x]) instanceof Wall)) {
                    AbstractGameObject tmpObj = mapObjects[y][x];
                    mapObjects[y][x] = getObjectPriority(tmpObj, obj);
                } else {
                    mapObjects[y][x] = obj;
                }

            }
        }
    }

    @Override
    public Component getMapComponent() {
        return jTableMap;
    }

    @Override
    public boolean drawMap() {

        updateObjectsArray();

        try {
            // присваиваем пустоту всем заголовкам столбцов, чтобы у таблицы не было заголовоков, а то некрасиво смотрится
            columnNames = new String[mapInfo.getWidth()];
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

    @Override
    public void start() {
        timeMover.start();
    }

    @Override
    public void stop() {
        timeMover.stop();
    }

    private class TimeMover implements ActionListener, MoveResultListener {

        private Timer timer;
        private final static int MOVING_PAUSE = 500;
        private final static int INIT_PAUSE = 1000;

        private TimeMover() {
            timer = new Timer(MOVING_PAUSE, this);
            timer.setInitialDelay(INIT_PAUSE);
            timer.start();
            getGameCollection().addMoveListener(this);
        }

        public void start() {
            timer.start();
        }

        public void stop() {
            timer.stop();
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            getGameCollection().moveObject(new AggressiveMoving(), GameObjectType.MONSTER);
        }

        @Override
        public void notifyActionResult(ActionResult actionResult, AbstractMovingObject movingObject) {
            switch (actionResult) {
                case WIN, DIE -> timer.stop();
            }
        }
    }

}