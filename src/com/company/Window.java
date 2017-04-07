package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Klasa głównego okna programu
 */
class Window extends JFrame {

    private ImageIcon flaga = new ImageIcon(getClass().getResource("res/flaga.png"));
    private ImageIcon trafione = new ImageIcon(getClass().getResource("res/trafione.png"));
    private ImageIcon mina = new ImageIcon(getClass().getResource("res/mina.png"));
    private JLabel minyBT = new JLabel();
    private JPanel mainPanel = new JPanel();
    private JPanel plansza = new JPanel();

    private int maxX = 10;
    private int maxY = 10;
    private int hardline;
    private int minesFields;
    private int emptyFields;
    private Field button[][];

    private long startTime;
    private String board;

    Window() {
        super("Saper");

        createMenuBar();

        JPanel bar = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLayout(new BorderLayout());

        mainPanel.add(bar, BorderLayout.NORTH);
        bar.setLayout(new FlowLayout());
        bar.add(new JLabel("Pozostałe miny:"));
        bar.add(minyBT);

        newGame();
    }

    /**
     * Tworzenie nowej planszy
     */
    private void newGame() {
//        new Prompt(this);
        new Prompt(this);

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
                button[c][i].addMouseListener(new Odkrycie(c, i, button[c][i]));
                plansza.add(button[c][i]);
            }
        }

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setLocation((int) screen.getWidth() / 2 - this.getWidth() / 2,
                (int) screen.getHeight() / 2 - this.getHeight() / 2);
        setVisible(true);

        minesFields = hardline;
        emptyFields = (maxX * maxY) - hardline;
        minyBT.setText(Integer.toString(minesFields));
        startTime = System.currentTimeMillis();
    }

    /**
     * Tworzenie paska menu
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu game = new JMenu("Gra");
        game.setMnemonic(KeyEvent.VK_G);

        JMenuItem eMenuItem = new JMenuItem("Nowa gra");
        eMenuItem.setMnemonic(KeyEvent.VK_N);
        eMenuItem.setToolTipText("Rozpoczęcie nowej gry");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newGame();
            }
        });
        game.add(eMenuItem);

        eMenuItem = new JMenuItem("Rekordy");
        eMenuItem.setMnemonic(KeyEvent.VK_R);
        eMenuItem.setToolTipText("Zapisane rekordy gry");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                results();
            }
        });
        game.add(eMenuItem);

        eMenuItem = new JMenuItem("Wyjście");
        eMenuItem.setMnemonic(KeyEvent.VK_W);
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

    /**
     * Ustawienie rozmiarów planszy i ilości min
     *
     * @param x     - rozmiar x planszy
     * @param y     - rozmiar y planszy
     * @param count - ilość min
     */
    void setGameSize(int x, int y, int count) {
        this.maxX = x;
        this.maxY = y;
        this.hardline = count;
        minesFields = hardline;
        emptyFields = (maxX * maxY) - hardline;
        minyBT.setText(Integer.toString(minesFields));

        if (x == 8 && y == 8 || x == 16 && y == 16 || x == 30 && y == 16) {
            board = x + "x" + y;
        } else {
            board = x + "x" + y + " (" + hardline + " min)";
        }

    }

    /**
     * Generowanie min
     *
     * @param xx - współrzędna x wybranego pola
     * @param yy - współrzędna y wybranego pola
     */
    private void generate(int xx, int yy) {
        int x;
        int y;
        Random rand = new Random();
        int i = hardline;
        while (i > 0) {
            x = rand.nextInt(maxX);
            y = rand.nextInt(maxY);
            if (button[x][y].getValue() != Field.MINA
                    && !((x == xx && y == yy)
                    || (x == xx - 1 && y == yy - 1)
                    || (x == xx + 1 && y == yy + 1)
                    || (x == xx - 1 && y == yy)
                    || (x == xx + 1 && y == yy)
                    || (x == xx && y == yy - 1)
                    || (x == xx && y == yy + 1)
                    || (x == xx + 1 && y == yy - 1)
                    || (x == xx - 1 && y == yy + 1))
                    ) {
                button[x][y].setValue(Field.MINA);
                i--;
            }
        }

//        button[0][2].setValue(Field.MINA);
//        button[0][1].setValue(Field.MINA);
//        button[0][0].setValue(Field.MINA);
//        button[5][1].setValue(Field.MINA);
//        button[6][4].setValue(Field.MINA);
//        button[6][6].setValue(Field.MINA);
//        button[6][1].setValue(Field.MINA);
//        button[6][2].setValue(Field.MINA);
//        button[6][3].setValue(Field.MINA);
//        button[5][3].setValue(Field.MINA);

        generateNumbers();
    }

    /**
     * Generowanie pól numerowanych
     */
    private void generateNumbers() {
        int xmin, xmax, ymin, ymax;
        int countMines;
        for (int i = 0; i < maxX; i++) {
            for (int c = 0; c < maxY; c++) {
                if (button[i][c].getValue() != Field.MINA) {
                    countMines = 0;
                    xmin = (i == 0) ? 0 : i - 1;
                    xmax = (i == maxX - 1) ? maxX - 1 : i + 1;

                    ymin = (c == 0) ? 0 : c - 1;
                    ymax = (c == maxY - 1) ? maxY - 1 : c + 1;

                    for (int j = xmin; j <= xmax; j++) {
                        for (int z = ymin; z <= ymax; z++) {
                            if (button[j][z].getValue() == Field.MINA) {
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
     * Odkrywanie pola
     *
     * @param x - współrzędna x wybranego pola
     * @param y - współrzędna y wybranego pola
     */
    private void discovery(int x, int y, boolean recursive) {
        if (button[x][y].getState() == Field.ZAKRYTE) {
            button[x][y].setState(Field.ODKRYTE);
            button[x][y].setBackground(Color.WHITE);

            if (button[x][y].getValue() == Field.MINA) {

                button[x][y].setState(Field.ODKRYTE);
                button[x][y].setIcon(trafione);
                onLoose();
                newGame();
            } else if (button[x][y].getValue() > Field.PUSTE) {

                setNumberColor(x, y);
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
                        discovery(c, i, false);
                    }
                }
                emptyFields--;
            }
        } else if (button[x][y].getState() == Field.ODKRYTE && button[x][y].getValue() > Field.PUSTE && recursive) {

            int xmin = (x == 0) ? 0 : x - 1;
            int xmax = (x == maxX - 1) ? maxX - 1 : x + 1;

            int ymin = (y == 0) ? 0 : y - 1;
            int ymax = (y == maxY - 1) ? maxY - 1 : y + 1;

            int countMines = 0, countFlags = 0;
            for (int i = ymin; i <= ymax; i++) {
                for (int c = xmin; c <= xmax; c++) {
                    if (button[c][i].getState() == Field.FLAGA) {
                        countFlags++;
                    }
                    if (button[c][i].getValue() == Field.MINA) {
                        countMines++;
                    }
                }
            }
            if (countFlags == countMines) {
                for (int i = ymin; i <= ymax; i++) {
                    for (int c = xmin; c <= xmax; c++) {
                        if (button[c][i].getState() == Field.ZAKRYTE) {
                            discovery(c, i, false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Ustawienie koloru liczb na planszy
     *
     * @param x - współrzędna x pola
     * @param y - współrzędna y pola
     */
    private void setNumberColor(int x, int y) {
        switch (button[x][y].getValue()) {
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
    }

    /**
     * Sprawdzenie warunków wygranej
     */
    private void checkWin() {
        if (emptyFields == 0) {

            //TODO
            LocalDateTime d = LocalDateTime.now();
            String date = d.getDayOfMonth() + "/" + d.getMonthValue() + "/" + d.getYear();
            double time = (double) ((System.currentTimeMillis() - startTime) / 100) / 10;


            Record.write(new Record(date, time, board));

            new Alert("Wygrana", time);
            newGame();
        }
    }

    /**
     * W przypadku przegranej oznaczenie wszystkich min
     */
    private void onLoose() {
        for (int i = 0; i < maxX; i++) {
            for (int c = 0; c < maxY; c++) {
                if (button[i][c].getValue() == Field.MINA && button[i][c].getState() == Field.ZAKRYTE) {
                    button[i][c].setIcon(mina);
                }
            }
        }
        new Alert("Przegrana");
    }

    /**
     * Pokazanie okna wyników
     */
    private void results() {
        new ResultsDialog();
    }

    /**
     * Klasa obsługująca reakcję na kliknięcie
     */
    private class Odkrycie implements MouseListener {

        private int x;
        private int y;
        private JButton bt;

        /**
         * @param x - współrzędna x wybranego pola
         * @param y - współrzędna y wybranego pola
         */
        private Odkrycie(int x, int y, JButton bt) {
            this.x = x;
            this.y = y;
            this.bt = bt;
        }

        /**
         * Sprawdzenie czy kursor nadal jest na przycisku
         *
         * @param e - obiekt zdarzenia myszy
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            Point b = bt.getLocationOnScreen();

            if (p.getX() >= b.getX() &&
                    p.getX() <= b.getX() + bt.getWidth() &&
                    p.getY() >= b.getY() &&
                    p.getY() <= b.getY() + bt.getHeight()) {
                mouse(x, y, e);
            }
        }

        /**
         * Metoda wywołana przy kliknięciu
         *
         * @param x - współrzędna x wybranego pola
         * @param y - współrzędna y wybranego pola
         * @param e - obiekt zdarzenia myszy
         */
        private void mouse(int x, int y, MouseEvent e) {
            if (e.getButton() == 3) {
                if (button[x][y].getState() == Field.ZAKRYTE) {
                    button[x][y].setState(Field.FLAGA);
                    button[x][y].setIcon(flaga);
                    minesFields--;
                    minyBT.setText(Integer.toString(minesFields));
                } else if (button[x][y].getState() == Field.FLAGA) {
                    button[x][y].setState(Field.ZAKRYTE);
                    button[x][y].setIcon(null);
                    minesFields++;
                    minyBT.setText(Integer.toString(minesFields));
                }
            } else if (e.getButton() == 1) {
                if (button[x][y].getValue() == Field.NIEOKRESLONE) {
                    generate(x, y);
                }
                discovery(x, y, true);
            }
            checkWin();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    //TODO usunąć
    private void debug() {
        for (int i = 0; i < maxY; i++) {
            for (int c = 0; c < maxX; c++) {
                switch (button[c][i].getValue()) {
                    case Field.MINA:
                        System.out.print(" # ");
                        break;
                    case Field.PUSTE:
                        System.out.print("   ");
                        break;
                    default:
                        System.out.print(" " + button[c][i].getValue() + " ");
                }
            }

            System.out.print(" | ");
            for (int c = 0; c < maxX; c++) {
                switch (button[c][i].getState()) {
                    case Field.ZAKRYTE:
                        System.out.print(" # ");
                        break;
                    default:
                        System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
    }
}