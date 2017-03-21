package com.company;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RecordsModel extends AbstractTableModel {

    private List<Record> records = null;
    private final static Object[] columns = {"Data", "Czas", "Plansza"};

    public RecordsModel(List<Record> records) {
        this.records = records;
    }

    public void setModelData(List<Record> records){
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
    public Class<?> getColumnClass(int i) {
        return null;
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

    @Override
    public void setValueAt(Object o, int i, int i1) {

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }
}
