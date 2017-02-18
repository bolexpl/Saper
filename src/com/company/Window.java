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
    private final int hardline = 15;
    Field button[][];

    public Window() {
        super("Saper");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setLayout(new GridLayout(maxY, maxX));
        setLocation(500, 200);

        button = new Field[maxX][maxY];
        for (int i = 0; i < maxY; i++) {
            for (int c = 0; c < maxX; c++) {
                button[c][i] = new Field();
                button[c][i].setPreferredSize(new Dimension(40, 40));
                button[c][i].setMargin(new Insets(0, 0, 0, 0));

                button[c][i].addActionListener(new Odkrycie(c, i));
                panel.add(button[c][i]);
            }
        }

        pack();
        setVisible(true);
    }

    /**
     * generowanie min
     */
    private void generate(int xx, int yy) {
        int x;
        int y;
        Random rand = new Random();
        for (int i = hardline; i > 0; i--) {
            do {
                x = rand.nextInt(maxX);
                y = rand.nextInt(maxY);
            } while (button[x][y].getValue() == Field.MINA
                    || x == xx || y == yy
                    || x == xx - 1 || y == yy - 1
                    || x == xx + 1 || y == yy + 1);

            button[x][y].setValue(Field.MINA);
//            button[x][y].setIcon(flaga);
        }

        int xmin, xmax, ymin, ymax;
        int countMines = 0;
        for (int i = 0; i < maxX; i++) {
            for (int c = 0; c < maxY; c++) {
                if (button[i][c].getValue() != -1) {
                    countMines = 0;
                    xmin = (i == 0) ? 0 : i - 1;
                    xmax = (i == maxX - 1) ? maxX - 1 : i + 1;

                    ymin = (c == 0) ? 0 : c - 1;
                    ymax = (c == maxY - 1) ? maxY - 1 : c + 1;

                    for (int j = xmin; j <= xmax; j++) {
                        for (int z = ymin; z <= ymax; z++) {
                            if (button[j][z].getValue() == -1) {
                                countMines++;
                            }
                        }
                    }
                    button[i][c].setValue(countMines);
//                    if(countMines != Field.PUSTE){
//                        button[i][c].setText(Integer.toString(countMines));
//                    }
                }
            }
        }
    }

    /**
     * reakcja na kliknięcie
     */
    class Odkrycie implements ActionListener {
        private int x;
        private int y;

        public Odkrycie(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (button[x][y].getValue() == -2) {
                generate(x, y);
            }

            if (button[x][y].getState() == Field.ZAKRYTE) {
                button[x][y].setState(Field.ODKRYTE);
                button[x][y].setBackground(Color.WHITE);

                if (button[x][y].getValue() > Field.PUSTE) {
                    button[x][y].setText(Integer.toString(button[x][y].getValue()));
                }

                if(button[x][y].getValue() == Field.MINA){
                    button[x][y].setIcon(flaga);
                }

                if (button[x][y].getValue() == Field.PUSTE) {
//                    System.out.println(x + ", " + y + ": " + button[x][y].getValue() + ", " + button[x][y].getState());

                    System.out.println("obecne: "+x+", "+y);
                    //rekurencja
                    int xmin, xmax, ymin, ymax;
                    xmin = (x == 0) ? 0 : x - 1;
                    xmax = (x == maxX - 1) ? maxX - 1 : x + 1;

                    ymin = (y == 0) ? 0 : y - 1;
                    ymax = (y == maxY - 1) ? maxY - 1 : y + 1;

                    for (int i = ymin; i <= ymax; i++) {
                        for (int c = xmin; c <= xmax; c++) {
                            if(i!=x && c!=y){
                                button[i][c].doClick();
//                                System.out.println(i+", "+c);
                            }
                        }
                    }
                }
            }
        }
    }
}
