package com.company;

import javax.swing.*;
import java.awt.*;

public class ResultsWindow extends JFrame {

    public ResultsWindow() {
        super("Wyniki");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setContentPane(panel);

        String[] data = {"aaa", "ssd", "bdg", "werw", "rtnn", "qweqf"};
        JList<String> list = new JList<>(data);
        panel.add(list,BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(new JButton("przycisk"));
        panel.add(bottom,BorderLayout.SOUTH);


        setSize(500,400);
        setLocation(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
