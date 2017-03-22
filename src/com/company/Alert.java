package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Alert extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label1;

    Alert(String x) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        label1.setText(x);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        contentPane.setPreferredSize(new Dimension(150,80));
        pack();
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    Alert(String x, String date, double time, String board) {
//        this(x+"\n Data: "+date+"\n Czas: "+time+"\n Plansza: "+board);
        x = x+"\n Data: "+date+"\n Czas: "+time+"\n Plansza: "+board;
        System.out.println(x);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        label1.setText(x);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//        contentPane.setPreferredSize(new Dimension(150,80));
        pack();
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    private void onOK() {
        dispose();
    }
}
