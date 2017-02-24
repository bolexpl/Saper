package com.company;

import javax.swing.*;
import java.awt.event.*;

public class Alert extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label1;

    public Alert(String x) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        label1.setText(x);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}
