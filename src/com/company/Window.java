package com.company;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    private JPanel panel = new JPanel();
    private ImageIcon flaga = new ImageIcon(getClass().getResource("res/flaga.png"));
    private ImageIcon trafione = new ImageIcon(getClass().getResource("res/trafione.png"));
    private int maxX = 9;
    private int maxY = 9;

    public Window() {
        super("Saper");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(new GridLayout(maxY,maxX));
        setLocation(500,200);

        Field button[][] = new Field[maxX][maxY];
        for(int i=0;i<maxX;i++){
            for(int c=0;c<maxY;c++){
                button[i][c] = new Field(flaga);
//                button[i][c].setBackground(Color.WHITE);

                button[i][c].setPreferredSize(new Dimension(40,40));
                panel.add(button[i][c]);
            }
        }

        pack();
        setVisible(true);
    }
}
