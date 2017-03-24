package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Prompt extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JRadioButton a8x8RadioButton;
    private JRadioButton a16x16RadioButton;
    private JRadioButton a30x16RadioButton;
    private JRadioButton wlasneUstawieniaRadioButton;
    private JLabel error;
    private ButtonGroup group;
    private Window w;

    public Prompt(Window w) {
        this.w = w;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        a8x8RadioButton.setActionCommand("8x8");
        a16x16RadioButton.setActionCommand("16x16");
        a30x16RadioButton.setActionCommand("30x16");

        error.setForeground(Color.RED);

        group = new ButtonGroup();
        group.add(a8x8RadioButton);
        group.add(a16x16RadioButton);
        group.add(a30x16RadioButton);
        group.add(wlasneUstawieniaRadioButton);

        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);

        wlasneUstawieniaRadioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                    textField1.setEnabled(false);
                    textField2.setEnabled(false);
                    textField3.setEnabled(false);
                } else {
                    textField1.setEnabled(true);
                    textField2.setEnabled(true);
                    textField3.setEnabled(true);
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

        setSize(new Dimension(this.getWidth() + 80, this.

                getHeight()));
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);

    }

    private void onOK() {
        if (!wlasneUstawieniaRadioButton.isSelected()) {
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
            w.setGameSize(x, y, count);
            dispose();
            return;
        }

        if (!textField1.getText().equals("") && !textField2.getText().equals("") && !textField3.getText().equals("")) {

            try {
                int x = Integer.parseInt(textField1.getText());
                int y = Integer.parseInt(textField2.getText());
                int count = Integer.parseInt(textField3.getText());

                if (x <= 3 || y <= 3) {
                    error.setText("Za mała wielkość!");
                    return;
                }
                if (count >= (x * y) - 9) {
                    error.setText("Za dużo min!");
                    return;
                }
                w.setGameSize(x, y, count);
                dispose();
            } catch (NumberFormatException e) {
                error.setText("Złe dane!");
            }
        } else {
            error.setText("Nie wpisano danych!");
        }
    }
}
