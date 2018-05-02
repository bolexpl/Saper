package com.company.gui;

import com.company.Record;
import com.company.RecordsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Klasa okna dialogowego wyświetlającego tabelę wyników
 * */
class RecordsDialog extends JDialog {
    private JComboBox<String> select;
    private RecordsModel model;

    RecordsDialog() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout());

        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        setModal(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        JButton buttonOK = new JButton("Ok");
        JButton buttonClear = new JButton("Resetuj wyniki");
        String[] boards = {"wszystko", "8x8", "16x16", "30x16", "własne ustawienia"};
        select = new JComboBox<>(boards);
        getRootPane().setDefaultButton(buttonOK);

        mainPanel.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonOK.addActionListener(actionEvent -> dispose());

        buttonClear.addActionListener(actionEvent -> {
            Record.init();
            setUpTableData("wszystko");
        });

        select.addActionListener(actionEvent -> setUpTableData(select.getSelectedItem().toString()));

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(new JLabel("Rozmiar planszy: "));
        top.add(select);

        model = new RecordsModel(Record.read());

        JTable table = new JTable(model);
        table.setShowGrid(false);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(buttonOK);
        bottom.add(buttonClear);

        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setSize(400, 300);
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    /**
     * Pobranie danych do modelu i odświeżenie tabeli
     *
     * @param filter - rozmiar planszy
     */
    private void setUpTableData(String filter) {
        if (filter.equals("wszystko")) {
            model.setData(Record.read(false));
        } else {
            model.setData(Record.read(filter, false));
        }
        model.fireTableDataChanged();
    }
}
