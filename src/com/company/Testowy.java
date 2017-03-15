package com.company;

import javax.swing.*;
import java.awt.*;

public class Testowy extends JFrame {

    private JPanel mainPanel;

    public Testowy() {
        super("testowy");

        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        String kolumny[] = {"Data", "Plansza", "Czas"};
        Object[][] data = {
                {"asdad", "12s","3x3"},
                {"drugi", "1s","3x3"},
                {"tr2342e", "dużo","3x3"},
                {"tre", "duż3243o","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "2632636","3x3"},
                {"tr234e", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "du46452462żo","3x3"},
                {"t457re", "dużo","3x3"},
                {"t235re", "dużo","3x3"},
                {"tre", "54377547","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"4sd", "dużo","3x3"},
                {"tre", "678658","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "47547","3x3"},
                {"tre", "dużo","3x3"},
                {"45457", "sdgehnerne","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "sgs","3x3"},
                {"tre", "dużo","3x3"},
                {"hgd", "aafasfa","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"2314", "67867969","3x3"},
                {"tre", "bsdsg","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"sfaf", "dużo","3x3"},
                {"tre", "dużo","3x3"},
                {"tre", "558","3x3"},
                {"tre", "89070","3x3"},
                {"457", "dużo","3x3"},
                {"tre", "dużo","3x3"},
        };

        JTable table = new JTable(data, kolumny);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        setSize(500, 400);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
