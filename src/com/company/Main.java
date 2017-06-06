package com.company;

import java.awt.*;

import com.company.gui.Window;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });

//        Record.write(new Record("tera",34,"8x8"));
//        Record.write(new Record("tera",1,"16x16"));
//        Record.write(new Record("tera",39,"8x8"));
//        Record.write(new Record("tera",5,"16x16"));
//        Record.write(new Record("tera",45,"8x8"));
    }
}
