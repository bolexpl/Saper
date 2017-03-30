package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertTest extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label1;

    /**
     * @param x - komunikat
     */
    public AlertTest(String x) {
        contentPane = new JPanel();
        label1 = new JLabel(x);
        buttonOK = new JButton("Ok");

        contentPane.setLayout(new BorderLayout());
        JPanel top = new JPanel();
        top.setBorder(new EmptyBorder(10,15,5,15));
        top.setLayout(new BorderLayout());
        top.add(label1,BorderLayout.WEST);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(buttonOK);

        contentPane.add(top,BorderLayout.NORTH);
        contentPane.add(bottom,BorderLayout.SOUTH);
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

    /**
     * @param x    - komunikat
     * @param time - czas przejścia gry
     */
    public AlertTest(String x, double time) {
        this("<html>" + x + "<br/><br/>Czas: " + time + "</html>");
    }
}
