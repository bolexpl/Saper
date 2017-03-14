package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * Klasa opisująca wynik gry
 */
public class Record implements Serializable {
    private String name;
    private double time;
    private static String fileName = "records.dat";

    Record(String name, double time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }

    static ArrayList<Record> read() {
        ArrayList<Record> wynik = new ArrayList<>();

        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
            System.exit(0);
        }

        Record r;
        try {
            while (true) {
                try {
                    r = (Record) input.readObject();
                    wynik.add(r);
                } catch (EOFException e) {
                    break;
                }
            }

            input.close();
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
            e.printStackTrace();
            System.exit(0);
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
            System.exit(0);
        }

        return wynik;
    }

    static void write(String name, double time) {
        ObjectOutputStream output = null;
        ArrayList<Record> list;

        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            list = read();
        } else {
            list = new ArrayList<>();
        }

        try {
            output = new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
            e.printStackTrace();
            System.exit(0);
        }

        Record r = new Record(name, time);
        list.add(r);
        try {
            for (Record r2 : list) {
                output.writeObject(r2);
            }

            output.close();
        } catch (IOException e) {
            System.out.println("Błąd wyjścia");
            e.printStackTrace();
            System.exit(0);
        }
    }

    static void clearRecords(){
        File f = new File(fileName);

        if(f.delete()){
            System.out.println("Usunięto");
        }else{
            System.out.println("Problem");
        }
    }
}
