package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

class Prompt extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private ButtonGroup group;
    private Window w;

    Prompt(Window w) {
        this.w = w;
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        GridLayout grid2 = new GridLayout(1, 2);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        JPanel right = new JPanel();
        right.setLayout(grid2);
        right.setBorder(new EmptyBorder(0, 0, 10, 10));
        buttonOK = new JButton("Ok");
        buttonCancel = new JButton("Wyjście");

        right.add(buttonOK);
        right.add(buttonCancel);
        bottom.add(right, BorderLayout.EAST);
        contentPane.add(bottom, BorderLayout.SOUTH);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();

        setSize(new Dimension(this.getWidth() + 80, this.getHeight()));
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) screen.getWidth() / 2 - getWidth() / 2,
                (int) screen.getHeight() / 2 - getHeight() / 2);
        setVisible(true);

    }

    private void onOK() {
        //null
    }
}
