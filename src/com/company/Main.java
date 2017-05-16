package com.company;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });

//        Record.write(new Record("tera",34,"duża"));
//        Record.write(new Record("tera",1,"duża"));
//        Record.write(new Record("tera",39,"duża"));
//        Record.write(new Record("tera",5,"duża"));
//        Record.write(new Record("tera",45,"duża"));
    }
}
