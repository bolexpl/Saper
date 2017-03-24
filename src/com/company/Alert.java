package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URISyntaxException;

public class Alert extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label1;

    /**
     * @param x - komunikat
     */
    Alert(String x) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        String fileName = "records.dat";
        try{
            fileName = Record.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

            StringBuilder s = new StringBuilder();
            if(fileName.contains(".jar")){
                for(int i=fileName.length()-1; i>=0;i--){
                    if(fileName.charAt(i) == '/') {
                        s.append(fileName.subSequence(0,i+1));
                        break;
                    }
                }
                fileName = s.toString()+"records.dat";
            }else{
                fileName = fileName+"records.dat";
            }
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        label1.setText(fileName);

//        label1.setText(x);

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
    Alert(String x, double time) {
        this("<html>" + x + "<br/><br/>Czas: " + time + "</html>");
    }
}
