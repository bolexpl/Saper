package com.company;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ResultsDialog extends JDialog {

    private JPanel mainPanel;
    private JButton buttonOK;
    private JTable table;
    private RecordsModel model;

    public ResultsDialog() {
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        setModal(true);
        buttonOK = new JButton("Ok");
        getRootPane().setDefaultButton(buttonOK);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        mainPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(new JLabel("Rozmiar planszy: "));

        JComboBox<String> select = new JComboBox<>(DaneDoTabeli.boards);
        top.add(select);


        model = new RecordsModel(Record.read());
        table = new JTable(model);



        table.setShowGrid(false);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onOK();
            }
        });
        bottom.add(buttonOK);

        //TODO wybór planszy
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setUpTableData(select.getSelectedItem().toString());
            }
        });

        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setSize(400, 300);
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    private void setUpTableData(String a){
//        Vector<Record> xyz = Record.read(a);
//        for (Record s : xyz) {
//            System.out.println(s.getDate() + ", " + s.getTime() + ", " + s.getBoard());
//        }

        if(a.equals("wszystko")){
            model.setData(Record.read());
        }else{
            model.setData(Record.read(a));
        }
        model.fireTableDataChanged();
    }

    private void onOK() {
        dispose();
    }
}
