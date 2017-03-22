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
                dispose();
            }
        });

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d = contentPane.getPreferredSize();
        d.width += 50;
        contentPane.setPreferredSize(d);
        pack();
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);
    }

    Alert(String x, double time) {
        this("<html>"+x+"<br/><br/>Czas: "+time+"</html>");
    }
}
