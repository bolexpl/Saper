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


        RecordsModel model = new RecordsModel(Record.read());
        JTable table = new JTable(model);
//        table.setShowVerticalLines(false);
//        table.setShowHorizontalLines(false);
        table.setShowGrid(false);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(new JLabel("Rozmiar planszy: "));

        //TODO wybór planszy
//        Vector<String> boards = new Vector<>();
//        for (Object[] x : DaneDoTabeli.data) {
//            if (!boards.contains(x[2].toString())) {
//                boards.add(x[2].toString());
//            }
//        }

        JComboBox<String> select = new JComboBox<>(DaneDoTabeli.boards);
        top.add(select);
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String a = select.getSelectedItem().toString();
                System.out.println(a);
            }
        });

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onOK();
            }
        });
        bottom.add(buttonOK);


        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        setSize(400, 300);

        //TODO
//        table.getColumnModel().getColumn(2).setMaxWidth(getWidth() /5);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    private void onOK() {
        dispose();
    }
}
