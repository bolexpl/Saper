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
    private JLabel message;
    private JButton buttonDefault;
    private Window w;

    public Prompt(Window wx) {
        this.w = wx;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        buttonDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (!textField1.getText().equals("") && !textField2.getText().equals("") && !textField3.getText().equals("")) {
            int x = Integer.parseInt(textField1.getText());
            int y = Integer.parseInt(textField2.getText());
            int count = Integer.parseInt(textField3.getText());
            if (x <= 3 || y <= 3) {
                message.setForeground(Color.RED);
                message.setText("Za małe dane!");
                return;
            }
            if(count >= (x*y)-9) {
                message.setForeground(Color.RED);
                message.setText("Za dużo min!");
                return;
            }
            w.setGameSize(x, y, count);
            dispose();
        } else {
            message.setForeground(Color.RED);
            message.setText("Złe dane!");
        }
    }

    private void onCancel() {
        System.exit(0);
    }
}
