package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RecordsModel extends AbstractTableModel {

    private Vector<Record> records;
    private final static Object[] COLUMNS = {"Data", "Czas", "Plansza"};

    public RecordsModel(Vector<Record> records) {
        super();
        this.records = records;
    }

    /**
     * Przypisanie danych
     * @param records - wektor rekordów
     * */
    public void setData(Vector<Record> records) {
        this.records = records;
    }

    @Override
    public int getRowCount() {
        if (records == null) return 0;
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int i) {
        return COLUMNS[i].toString();
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {

        if (records == null) return null;
        Record r = records.get(row);
        switch (column) {
            case 0:
                return r.getDate();
            case 1:
                return r.getTime();
            case 2:
                return r.getBoard();
            default:
                return r;
        }
    }
}
