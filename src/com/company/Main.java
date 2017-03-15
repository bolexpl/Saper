package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
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
