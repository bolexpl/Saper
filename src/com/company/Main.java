package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try{
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }catch (Exception e){
            e.printStackTrace();
        }

        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            System.out.println(info.getClassName());
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new Window();
//                new ResultsWindow();
                new ResultsWindow();
            }
        });

//        Record.write("bolek",3.1);
//        Record.write("jeden",4.5);
//        Record.write("dwa",20.6);
//
//        ArrayList<Record> a = Record.read();
//        for (Record s: a) {
//            System.out.println(s.getName()+", "+s.getTime());
//        }
     }
}
