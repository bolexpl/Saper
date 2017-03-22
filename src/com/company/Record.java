package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

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
        Vector<Record> list;

        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            list = read();
        } else {
            list = new Vector<>();
        }

        list.add(r);
        try {
            output = new ObjectOutputStream(new FileOutputStream(fileName));

            for (Record r2 : list) {
                output.writeObject(r2);
            }
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
        }
    }

    public static Vector<Record> read() {
        Vector<Record> wynik = new Vector<>();
        ObjectInputStream input;
        Record r;

        File f = new File(fileName);
        if (!f.exists()) {
            init();
        }

        try {
            input = new ObjectInputStream(new FileInputStream(fileName));

            while (true) {
                try {
                    r = (Record) input.readObject();
                    wynik.add(r);
                } catch (EOFException e) {
                    break;
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
        return wynik;
    }

    public static Vector<Record> read(String filter){
        Vector<Record> wynik = new Vector<>();

        ObjectInputStream input;
        Record r;

        File f = new File(fileName);
        if (!f.exists()) {
            init();
        }

        try {
            input = new ObjectInputStream(new FileInputStream(fileName));

            while (true) {
                try {
                    r = (Record) input.readObject();
                    if (r.getBoard().equals(filter) ||
                            (filter.equals("własne ustawienia") &&
                                    !r.getBoard().equals("8x8") &&
                                    !r.getBoard().equals("16x16") &&
                                    !r.getBoard().equals("30x16"))
                            ) {
                        wynik.add(r);
                    }

                } catch (EOFException e) {
                    break;
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
        return wynik;
    }

    public static void clearRecords() {
        File f = new File(fileName);

        Vector<Record> a = Record.read();
        for (Record s : a) {
            System.out.println(s.getDate() + ", " + s.getTime() + ", " + s.getBoard());
        }

        System.out.println("czyszczenie");
        if (f.delete()) {
            System.out.println("Usunięto");
        } else {
            System.out.println("Problem");
        }

        a = Record.read();
        for (Record s : a) {
            System.out.println(s.getDate() + ", " + s.getTime() + ", " + s.getBoard());
        }
    }

    public static void init(){
        try{
            new ObjectOutputStream(new FileOutputStream(fileName)).close();
        }catch (FileNotFoundException e){
            System.out.println("Brak pliku");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Błąd wyjścia");
            e.printStackTrace();
        }
    }
}
