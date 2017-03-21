package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RecordsModel extends AbstractTableModel {

    private Vector<Record> records;
    private final static Object[] columns = {"Data", "Czas", "Plansza"};

    public RecordsModel(Vector<Record> records) {
        super();
        this.records = records;
    }

    public Record getRecord(int i){
        return records.get(i);
    }

    @Override
    public int getRowCount() {
        if (records == null) return 0;
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int i) {
        return columns[i].toString();
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {

        if(records == null) return null;
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
