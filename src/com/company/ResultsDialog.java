package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultsDialog extends JDialog {
    private JPanel mainPanel;
    private JButton buttonOK;
    private JButton buttonClear;
    private JComboBox<String> select;
    private RecordsModel model;

    public ResultsDialog() {
        mainPanel = new JPanel();
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

        assignButtons();
        locateComponents();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize().getSize();
        setSize(400, 300);
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    /**
     * Przypisanie funkcjonalności do przycisków
     * */
    private void assignButtons() {
        buttonOK = new JButton("Ok");
        buttonClear = new JButton("Resetuj wyniki");
        String[] boards = {"wszystko", "8x8", "16x16", "30x16", "własne ustawienia"};
        select = new JComboBox<>(boards);
        getRootPane().setDefaultButton(buttonOK);
        mainPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Record.clearRecords();
                setUpTableData("wszystko");
            }
        });
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setUpTableData(select.getSelectedItem().toString());
            }
        });
    }

    /**
     * Rozmieszczenie komponentów w oknie
     * */
    private void locateComponents() {
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
    }

    /**
     * Pobranie danych do modelu i odświeżenie tabeli
     * @param s - rozmiar planszy
     * */
    private void setUpTableData(String s) {
        if (s.equals("wszystko")) {
            model.setData(Record.read());
        } else {
            model.setData(Record.read(s));
        }
        model.fireTableDataChanged();
    }
}
