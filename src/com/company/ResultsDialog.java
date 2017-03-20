package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class ResultsDialog extends JDialog {

    private JPanel mainPanel;
    private JButton buttonOK;

    public ResultsDialog() {
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        setModal(true);
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

        JTable table = new JTable(DaneDoTabeli.data, DaneDoTabeli.kolumny);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(new JLabel("Rozmiar planszy: "));

        String[] boards = {"3x3", "4x4", "5x5"};




        //TODO
        Vector<String> boards2 = new Vector<>();

        for(Object[] x: DaneDoTabeli.data){
//            System.out.println(x[2].toString());
            if(!boards2.contains(x[2].toString())){
                boards2.add(x[2].toString());
            }
        }
        for(String x: boards2){
            System.out.println(x);
        }



        JComboBox<String> select = new JComboBox<>(boards);
        top.add(select);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a = select.getSelectedItem().toString();
                System.out.println(a);
            }
        });

        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setSize(500, 400);
        setLocation(400, 200);
        setVisible(true);
    }

    private void onOK() {
        dispose();
    }
}
