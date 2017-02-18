package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Window extends JFrame {

    private JPanel panel = new JPanel();
    private ImageIcon flaga = new ImageIcon(getClass().getResource("res/flaga.png"));
    private ImageIcon trafione = new ImageIcon(getClass().getResource("res/trafione.png"));
    private final int maxX = 9;
    private final int maxY = 9;
    private final int hardline = 18;
    Field button[][];

    public Window() {
        super("Saper");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(new GridLayout(maxY, maxX));
        setLocation(500, 200);

        button = new Field[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int c = 0; c < maxY; c++) {
                button[i][c] = new Field();
//                button[i][c].setBackground(Color.WHITE);
                button[i][c].setPreferredSize(new Dimension(40, 40));
                button[i][c].setMargin(new Insets(0, 0, 0, 0));

                button[i][c].addActionListener(new Odkrycie(i,c));

                panel.add(button[i][c]);
            }
        }

        generate(button);

        pack();
        setVisible(true);
    }

    /**
     * generowanie min
     * */
    private void generate(Field button[][]) {
        int x;
        int y;
        Random rand = new Random();
        for (int i = hardline; i > 0; i--) {
            do{
                x = rand.nextInt(maxX);
                y = rand.nextInt(maxY);
            }while(button[x][y].getValue() == -1);

            button[x][y].setValue(-1);
            button[x][y].setIcon(flaga);
        }
        //TODO generowanie numerów
    }

    /**
     * reakcja na kliknięcie
     * */
    class Odkrycie implements ActionListener{
        private int a;
        private int b;

        public Odkrycie(int a,int b){
            this.a = a;
            this.b = b;
        }
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println(this.a+", "+this.b);
            button[a][b].setIcon(flaga);
        }
    }
}
