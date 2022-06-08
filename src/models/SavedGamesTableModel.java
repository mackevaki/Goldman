package models;

import objects.MapInfo;
import objects.SavedMapInfo;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SavedGamesTableModel extends AbstractTableModel {
    private final ArrayList<SavedMapInfo> list;

    public SavedGamesTableModel(ArrayList<SavedMapInfo> list) {
        this.list = list;
    }

    public MapInfo getMapInfo(int index){
        return list.get(index);
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return list.size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return 5;
    }

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        switch (columnIndex) {
            case 0 -> value = rowIndex + 1;
            case 1 -> value = list.get(rowIndex).getUser().getUserName();
            case 2 -> {
                    Date date = new Date(list.get(rowIndex).getSaveDate());
                    value = dateFormat.format(date);
            }
            case 3 -> value = list.get(rowIndex).getTotalScore();
            case 4 -> value = list.get(rowIndex).getTurnsCount();
            default -> throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }
        return value;
    }

    @Override
    public String getColumnName(int column) {
        String columnName = switch (column) {
            case 0 -> "№";
            case 1 -> "Имя игрока";
            case 2 -> "Дата игры";
            case 3 -> "Кол-во очков";
            case 4 -> "Кол-во ходов";
            default -> throw new IndexOutOfBoundsException("Column index out of bounds: " + column);
        };
        return columnName;
    }

    /**
     * Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
     *
     * @param columnIndex the column being queried
     * @return the Object.class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> cl;
        switch (columnIndex) {
            case 0, 3, 4 -> cl = Integer.class;
            case 1, 2 -> cl = String.class;
            default -> throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }
        return cl;
    }

    public void deleteMapInfo(int index){
        list.remove(index);
    }

    public void refresh() {
        fireTableDataChanged();
    }
}
