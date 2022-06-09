package models;

import score.objects.UserScore;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScoreTableModel extends AbstractTableModel {

    private final ArrayList<UserScore> list;

    public ScoreTableModel(ArrayList<UserScore> list) {
        this.list = list;
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
                Date date = new Date(list.get(rowIndex).getPlayDate());
                value = dateFormat.format(date);
            }
            case 3 -> value = list.get(rowIndex).getScore();
            case 4 -> list.get(rowIndex).getPlayCount();
            default -> throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }

        return value;
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
            case 0, 3, 4 -> {
                cl = Integer.class;
            }
            case 1 -> cl = String.class;
            case 2 -> cl = Long.class;
            default -> throw new IndexOutOfBoundsException("Column index out of bounds: " + columnIndex);
        }

        return cl;
    }

    /**
     * Returns a default name for the column using spreadsheet conventions:
     * A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cannot be found,
     * returns an empty string.
     *
     * @param column the column being queried
     * @return a string containing the default name of <code>column</code>
     */
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "№";
            case 1 -> "Имя игрока";
            case 2 -> "Дата игры";
            case 3 -> "Кол-во очков";
            case 4 -> "Кол-во игр";
            default -> throw new IndexOutOfBoundsException("Column index out of bounds: " + column);
        };
    }
}