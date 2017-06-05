package com.company;

import com.company.exception.RecordsException;
import com.company.windows.Alert;

import java.io.*;
import java.net.URISyntaxException;
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

    String getDate() {
        return date;
    }

    double getTime() {
        return time;
    }

    String getBoard() {
        return board;
    }

    /**
     * Zapisywanie nowego rekordu do pliku
     *
     * @param r - rekord do zapisania
     */
    public static void write(Record r) {
        setFileName();
        ObjectOutputStream output;
        Vector<Record> list;

        File f = new File(fileName);
        if (f.exists() && !f.isDirectory()) {
            list = read(false);
        } else {
            list = new Vector<>();
        }

        list.add(r);
        try {
            BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(fileName));
            output = new ObjectOutputStream(buff);

            for (Record r2 : list) {
                output.writeObject(r2);
            }
            output.close();
        } catch (FileNotFoundException e) {
            new Alert("Brak pliku");
        } catch (IOException e) {
            new Alert("Błąd wyjścia");
        }
    }

    /**
     * Odczytywanie wszystkich rekordów z pliku
     */
    public static Vector<Record> read() {
        return read(true);
    }

    public static Vector<Record> read(boolean message) {
        return read("wszystko", message);
    }

    /**
     * Odczytywanie rekordów dla danej planszy
     *
     * @param filter - rozmiar planszy
     */
    public static Vector<Record> read(String filter, boolean message) {
        setFileName();
        Vector<Record> wynik = new Vector<>();

        ObjectInputStream input;
        Record r;

        File f = new File(fileName);
        if (!f.exists()) {
            init();
        }

        try {
            BufferedInputStream buff = new BufferedInputStream(new FileInputStream(fileName));
            input = new ObjectInputStream(buff);

            while (true) {
                try {
                    r = (Record) input.readObject();
                    if (r.getBoard().equals(filter) ||
                            (filter.equals("własne ustawienia") &&
                                    !r.getBoard().equals("8x8") &&
                                    !r.getBoard().equals("16x16") &&
                                    !r.getBoard().equals("30x16")) ||
                            filter.equals("wszystko")
                            ) {
                        wynik.add(r);
                    }

                } catch (EOFException e) {
                    break;
                }
            }

            input.close();

            if (message && wynik.size() == 0) {
                throw new RecordsException("Brak wyników.");
            }
        } catch (FileNotFoundException e) {
            new Alert("Brak pliku");
            e.printStackTrace();
        } catch (IOException e) {
            new Alert("Błąd odczytania wyników");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            new Alert("Uszkodzony plik");
            e.printStackTrace();
        } catch (RecordsException e) {
            new Alert(e.getMessage());
        }
        return wynik;
    }

    /**
     * Usuwanie wszystkich rekordów z pliku
     */
    public static void clearRecords() {
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.close();
        } catch (FileNotFoundException e) {
            new Alert("Brak pliku");
            e.printStackTrace();
        } catch (IOException e) {
            new Alert("Błąd usuwania wyników");
            e.printStackTrace();
        }
    }

    /**
     * Tworzenie pliku binarnego do przechowywania wyników
     */
    private static void init() {
        try {
            new ObjectOutputStream(new FileOutputStream(fileName)).close();
        } catch (FileNotFoundException e) {
            new Alert("Brak pliku");
        } catch (IOException e) {
            new Alert("Błąd usuwania wyników");
        }
    }

    private static void setFileName() {
        try {
            fileName = Record.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();

            StringBuilder s = new StringBuilder();
            if (fileName.contains(".jar")) {
                for (int i = fileName.length() - 1; i >= 0; i--) {
                    if (fileName.charAt(i) == '/') {
                        s.append(fileName.subSequence(0, i + 1));
                        break;
                    }
                }
                fileName = s.toString() + "records.dat";
            } else {
                fileName = fileName + "records.dat";
            }
        } catch (URISyntaxException e) {
            fileName = "records.dat";
        }
    }
}
