package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * Klasa opisująca wynik gry
 */
public class Record implements Serializable {

    private String date;
    private double time;
    private String board;
    private static String fileName = "records.dat";

    public Record(String date, double time, String board) {
        this.date = date;
        this.time = time;
        this.board = board;
    }

    public String getDate() {
        return date;
    }

    public double getTime() {
        return time;
    }

    public String getBoard() {
        return board;
    }

    public static void write(Record r) {
        ObjectOutputStream output;
        ArrayList<Record> list;

//        System.out.println("#############################################write");

        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            list = read();
        } else {
            list = new ArrayList<>();
        }

//        for(Record x : list){
//            System.out.println(x.getDate()+", "+x.getTime()+", "+x.getBoard());
//        }

        list.add(r);
        try {
            output = new ObjectOutputStream(new FileOutputStream(fileName));

            for (Record r2 : list) {
//                System.out.println("a");
                output.writeObject(r2);
            }
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
//            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
//            e.printStackTrace();
            System.exit(0);
        }
//        System.out.println("----------------------------------------write");
    }

    //------------------------------------------------------------------------------------

    public static ArrayList<Record> read() {
        ArrayList<Record> wynik = new ArrayList<>();
        ObjectInputStream input;
        Record r;

//        System.out.println("#############################################read");

        try {
            input = new ObjectInputStream(new FileInputStream(fileName));

            while (true) {
                try {
                    r = (Record) input.readObject();
//                    System.out.println(r.getDate()+", "+r.getTime()+", "+r.getBoard());
                    wynik.add(r);
//                    System.out.println("dodałem");
                } catch (EOFException e) {
                    break;
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
//            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
//            e.printStackTrace();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
//            e.printStackTrace();
            System.exit(0);
        }
//        System.out.println("----------------------------------------read");
        return wynik;
    }

    public static void clearRecords() {
        File f = new File(fileName);

        if (f.delete()) {
            System.out.println("Usunięto");
        } else {
            System.out.println("Problem");
        }
    }
}
