package com.company.gui;

import com.company.interfaces.GameWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

class Prompt extends JDialog {
    private JTextField xField;
    private JTextField yField;
    private JTextField countField;
    private JRadioButton customRadioButton;
    private JLabel error;
    private ButtonGroup group;
    private GameWindow mainWindow;

    Prompt(GameWindow mainWindow) {
        this.mainWindow = mainWindow;
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        GridLayout grid = new GridLayout(9, 1);
        grid.setVgap(10);
        GridLayout grid2 = new GridLayout(1, 2);
        grid2.setHgap(10);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        JPanel right = new JPanel();
        right.setLayout(grid2);
        right.setBorder(new EmptyBorder(0, 0, 10, 10));
        JButton buttonOK = new JButton("Ok");
        JButton buttonCancel = new JButton("Wyjście");
        right.add(buttonOK);
        right.add(buttonCancel);
        right.setBorder(BorderFactory.createEmptyBorder(10,100,10,10));

        JPanel top = new JPanel();


        top.setLayout(grid);
        top.setBorder(new EmptyBorder(15, 15, 0, 15));
        top.add(new JLabel("Podaj ilość min:"));
        JRadioButton a8x8RadioButton = new JRadioButton("8x8");
        JRadioButton a16x16RadioButton = new JRadioButton("16x16");
        JRadioButton a30x16RadioButton = new JRadioButton("30x16");
        customRadioButton = new JRadioButton("własne ustawienia");
        error = new JLabel();
        xField = new JTextField();
        yField = new JTextField();
        countField = new JTextField();

        top.add(a8x8RadioButton);
        top.add(a16x16RadioButton);
        top.add(a30x16RadioButton);
        top.add(customRadioButton);
        top.add(error);
        top.add(xField);
        top.add(yField);
        top.add(countField);
        bottom.add(right, BorderLayout.EAST);
        contentPane.add(top, BorderLayout.NORTH);
        contentPane.add(bottom, BorderLayout.SOUTH);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        a8x8RadioButton.setActionCommand("8x8");
        a8x8RadioButton.setSelected(true);
        a16x16RadioButton.setActionCommand("16x16");
        a30x16RadioButton.setActionCommand("30x16");

        error.setForeground(Color.RED);

        group = new ButtonGroup();
        group.add(a8x8RadioButton);
        group.add(a16x16RadioButton);
        group.add(a30x16RadioButton);
        group.add(customRadioButton);

        xField.setEnabled(false);
        yField.setEnabled(false);
        countField.setEnabled(false);

        customRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                    xField.setEnabled(false);
                    yField.setEnabled(false);
                    countField.setEnabled(false);
                } else {
                    xField.setEnabled(true);
                    yField.setEnabled(true);
                    countField.setEnabled(true);
                }
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                error.setText("");
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
//        setSize(286,400);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);

    }

    private void onOK() {
        if (!customRadioButton.isSelected()) {
            simpleBoard();
        }else if (!xField.getText().equals("") && !yField.getText().equals("") && !countField.getText().equals("")) {
            customBoard();
        } else {
            error.setText("Nie wpisano danych!");
        }
    }

    private void simpleBoard(){
        String[] a = group.getSelection().getActionCommand().split("x");
        int x = Integer.parseInt(a[0]);
        int y = Integer.parseInt(a[1]);
        int count;
        if (x == 8) {
            count = 10;
        } else if (x == 16) {
            count = 40;
        } else {
            count = 99;
        }
        mainWindow.setGameSize(x, y, count);
        dispose();
    }

    private void customBoard(){
        try {
            int x = Integer.parseInt(xField.getText());
            int y = Integer.parseInt(yField.getText());
            int count = Integer.parseInt(countField.getText());

            if (x <= 3 || y <= 3) {
                error.setText("Za mała wielkość!");
                return;
            }
            if (count >= (x * y) - 9) {
                error.setText("Za dużo min!");
                return;
            }
            mainWindow.setGameSize(x, y, count);
            dispose();
        } catch (NumberFormatException e) {
            error.setText("Złe dane!");
        }
    }
}
