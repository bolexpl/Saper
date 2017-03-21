package com.company;

import java.awt.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new Window();
                new ResultsDialog();
            }
        });


//        Record.write(new Record("bolek", 3.1,"8x8"));
//        Record.write(new Record("jeden", 4.5,"8x8"));
//        Record.write(new Record("dwa",25.6,"8x8"));
//        Record.write(new Record("asd",20.1,"16x16"));
//        Record.write(new Record("qwe",20.6,"30x16"));

//        System.out.println("##################################################");

//        Vector<Record> a = Record.read();
//        for (Record s : a) {
//            System.out.println(s.getDate() + ", " + s.getTime() + ", " + s.getBoard());
//        }
    }
}
