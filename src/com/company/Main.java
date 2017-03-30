package com.company;

import java.awt.*;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                System.exit(0);
//            }
//        });


//        Vector<Record> v = Record.read();
//        for(Record r : v){
//            System.out.println(r.getDate()+", "+r.getTime()+", "+r.getBoard());
//        }


//        Record.write(new Record("tera",34,"duża"));
//        Record.write(new Record("tera",1,"duża"));
//        Record.write(new Record("tera",39,"duża"));
//        Record.write(new Record("tera",5,"duża"));
//        Record.write(new Record("tera",45,"duża"));
    }
}
