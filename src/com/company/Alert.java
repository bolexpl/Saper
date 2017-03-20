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

        //TODO
//        setSize(200, 100);
        contentPane.setPreferredSize(new Dimension(150,90));
        pack();
//        setSize((int) screen.getWidth() / 3, (int) screen.getHeight() / 3);



        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    private void onOK() {
        dispose();
    }
}
