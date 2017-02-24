package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class Window extends JFrame {

    private ImageIcon flaga = new ImageIcon(getClass().getResource("res/flaga.png"));
    private ImageIcon trafione = new ImageIcon(getClass().getResource("res/trafione.png"));
    private int maxX = 10;
    private int maxY = 10;
    private int hardline = 15;
    private Field button[][];
    private int minesFields = hardline;
    private int emptyFields = (maxX * maxY) - hardline;
    private JLabel minyBT = new JLabel(Integer.toString(minesFields));

    private JPanel mainPanel = new JPanel();
    private JPanel plansza = new JPanel();

    public Window() {
        super("Saper");
        createMenuBar();

        JPanel bar = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLayout(new BorderLayout());
        setLocation(500, 200);

        mainPanel.add(bar, BorderLayout.NORTH);
        bar.setLayout(new FlowLayout());
        bar.add(new JLabel("Pozostałe miny:"));
        bar.add(minyBT);

        newGame();
    }

    private void newGame(){
        Prompt dialog = new Prompt(this);
        dialog.pack();
        dialog.setVisible(true);

        mainPanel.remove(plansza);
        plansza = new JPanel();

        mainPanel.add(plansza, BorderLayout.CENTER);

        plansza.setLayout(new GridLayout(maxY, maxX));

        button = new Field[maxX][maxY];
        for (int i = 0; i < maxY; i++) {
            for (int c = 0; c < maxX; c++) {
                button[c][i] = new Field();
                button[c][i].setPreferredSize(new Dimension(40, 40));
                button[c][i].setMargin(new Insets(0, 0, 0, 0));
                button[c][i].addMouseListener(new Odkrycie(c, i));
                plansza.add(button[c][i]);
            }
        }

        pack();
        setVisible(true);
    }

    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu game = new JMenu("Gra");
        game.setMnemonic(KeyEvent.VK_G);

        JMenuItem eMenuItem = new JMenuItem("Nowa gra");
        eMenuItem.setMnemonic(KeyEvent.VK_N);
        eMenuItem.setToolTipText("Rozpoczęcie nowej gry");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                System.exit(0);
                newGame();
            }
        });

        game.add(eMenuItem);

        eMenuItem = new JMenuItem("Zakończ");
        eMenuItem.setMnemonic(KeyEvent.VK_Z);
        eMenuItem.setToolTipText("Wyjście z gry");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        game.add(eMenuItem);
        menuBar.add(game);
        setJMenuBar(menuBar);
    }

    public void setGameSize(int x, int y, int count){
        this.maxX = x;
        this.maxY = y;
        this.hardline = count;
        minesFields = hardline;
        emptyFields = (maxX * maxY) - hardline;
        minyBT = new JLabel(Integer.toString(minesFields));
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
        }

//        button[4][0].setValue(Field.MINA);
//        button[4][1].setValue(Field.MINA);
//        button[4][2].setValue(Field.MINA);
//        button[0][4].setValue(Field.MINA);
//        button[1][4].setValue(Field.MINA);
//        button[2][4].setValue(Field.MINA);
//
//        button[8][0].setForeground(Color.BLUE);
//        button[8][0].setText(Integer.toString(1));
//        button[8][1].setForeground(Color.GREEN);
//        button[8][1].setText(Integer.toString(2));
//        button[8][2].setForeground(Color.RED);
//        button[8][2].setText(Integer.toString(3));
//        button[8][3].setForeground(Color.YELLOW);
//        button[8][3].setText(Integer.toString(4));
//        button[8][4].setForeground(Color.CYAN);
//        button[8][4].setText(Integer.toString(5));
//        button[8][5].setForeground(Color.MAGENTA);
//        button[8][5].setText(Integer.toString(6));
//        button[8][6].setForeground(Color.ORANGE);
//        button[8][6].setText(Integer.toString(7));
//        button[8][7].setForeground(Color.GRAY);
//        button[8][7].setText(Integer.toString(8));

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
                }
            }
        }
    }

    /**
     * odkrycie pola
     */
    private void discovery(int x, int y) {
        if (button[x][y].getState() == Field.ZAKRYTE) {
            button[x][y].setState(Field.ODKRYTE);
            button[x][y].setBackground(Color.WHITE);

            if (button[x][y].getValue() == Field.MINA) {

                button[x][y].setIcon(trafione);
                Alert dialog = new Alert("Przegrana");
                dialog.pack();
                dialog.setSize(150, 80);
                dialog.setLocation(600, 300);
                dialog.setVisible(true);
                System.exit(0);
            } else if (button[x][y].getValue() > Field.PUSTE) {

                switch (button[x][y].getValue()){
                    case 1:
                        button[x][y].setForeground(Color.BLUE);
                        break;
                    case 2:
                        button[x][y].setForeground(Color.GREEN);
                        break;
                    case 3:
                        button[x][y].setForeground(Color.RED);
                        break;
                    case 4:
                        button[x][y].setForeground(Color.YELLOW);
                        break;
                    case 5:
                        button[x][y].setForeground(Color.CYAN);
                        break;
                    case 6:
                        button[x][y].setForeground(Color.MAGENTA);
                        break;
                    case 7:
                        button[x][y].setForeground(Color.ORANGE);
                        break;
                    case 8:
                        button[x][y].setForeground(Color.GRAY);
                        break;
                }
                button[x][y].setText(Integer.toString(button[x][y].getValue()));
                emptyFields--;
            } else if (button[x][y].getValue() == Field.PUSTE) {

                //rekurencja
                int xmin = (x == 0) ? 0 : x - 1;
                int xmax = (x == maxX - 1) ? maxX - 1 : x + 1;

                int ymin = (y == 0) ? 0 : y - 1;
                int ymax = (y == maxY - 1) ? maxY - 1 : y + 1;

                for (int i = ymin; i <= ymax; i++) {
                    for (int c = xmin; c <= xmax; c++) {
                        discovery(c, i);
                    }
                }
                emptyFields--;
            }
        }else if(button[x][y].getState() == Field.ODKRYTE && button[x][y].getValue() > Field.PUSTE){

            int xmin = (x == 0) ? 0 : x - 1;
            int xmax = (x == maxX - 1) ? maxX - 1 : x + 1;

            int ymin = (y == 0) ? 0 : y - 1;
            int ymax = (y == maxY - 1) ? maxY - 1 : y + 1;

            int countMines=0, countFlags=0;
            for (int i = ymin; i <= ymax; i++) {
                for (int c = xmin; c <= xmax; c++) {
                    if(button[c][i].getState() ==  Field.FLAGA){
                        countFlags++;
                    }
                    if(button[c][i].getValue() == Field.MINA){
                        countMines++;
                    }
                }
            }
            if(countFlags == countMines){
                for (int i = ymin; i <= ymax; i++) {
                    for (int c = xmin; c <= xmax; c++) {
                        if(button[c][i].getState() == Field.ZAKRYTE){
                            discovery(c, i);
                        }
                    }
                }
            }
        }
    }

    /**
     * reakcja na kliknięcie
     */
    class Odkrycie implements MouseListener {
        private int x;
        private int y;

        private Odkrycie(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 3) {
                if (button[x][y].getState() == Field.ZAKRYTE) {
                    button[x][y].setState(Field.FLAGA);
                    button[x][y].setIcon(flaga);
                    minesFields--;
                    minyBT.setText(Integer.toString(minesFields));
                } else if(button[x][y].getState() == Field.FLAGA) {
                    button[x][y].setState(Field.ZAKRYTE);
                    button[x][y].setIcon(null);
                    minesFields++;
                    minyBT.setText(Integer.toString(minesFields));
                }
            } else if (e.getButton() == 1) {
                if (button[x][y].getValue() == -2) {
                    generate(x, y);
                }
                discovery(x, y);
            }

            boolean allMinesFlagged = true;
            if (minesFields == 0) {
                for (int i = 0; i < maxX; i++) {
                    for (int c = 0; c < maxY; c++) {
                        if (button[c][i].getValue() == Field.MINA && button[c][i].getState() != Field.FLAGA) {
                            allMinesFlagged = false;
                        }
                    }
                }
            }
            if (allMinesFlagged && minesFields == 0 || emptyFields == 0) {
                Alert dialog = new Alert("Wygrana");
                dialog.pack();
                dialog.setSize(150, 80);
                dialog.setLocation(600, 300);
                dialog.setVisible(true);
                System.exit(0);
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }
}