package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomHashMap {
    private Entry[] table;
    private int size;

    private static final double MAX_LOAD_FACTOR = 0.75;
    private static final double MIN_LOAD_FACTOR = 0.1;
    private static final int MIN_TABLE_LENGTH = 10;
    private static final int TABLE_SIZE = 8;

    public CustomHashMap() {
        this.table = new Entry[TABLE_SIZE];
        this.size = 0;
    }

    public Entry put(Entry entry) {
        if(entry.getKey() == null) {
            return null;
        }

        Entry prev = table[entry.getKey().hashCode() % table.length];

        if (prev == null) {
            table[entry.getKey().hashCode() % table.length] = entry;
        } else {
            Entry runner = table[entry.getKey().hashCode() % table.length];
            boolean inserted = false;

            while (runner.getNext() != null) {
                if (runner.getNext().getKey().equals(entry.getKey())) {
                    Entry next = table[entry.getKey().hashCode() % table.length].getNext().getNext();

                    runner.setNext(entry);
                    runner.getNext().setNext(next);

                    inserted = true;

                    this.size--;

                    break;
                }

                runner = runner.getNext();
            }

            if (!inserted) {
                runner.setNext(entry);
            }
        }

        this.size++;

        if (loadFactor() >= MAX_LOAD_FACTOR || (loadFactor() <= MIN_LOAD_FACTOR && table.length > MIN_TABLE_LENGTH)) {
            debugPrint();
            System.out.println("\n\n");
            rehash();
        }

        return prev;
    }

    // public int countCollisions() {

    // }

    public Entry get(Key key) {
        if (table[key.hashCode() % table.length].getNext() == null) {
            return table[key.hashCode() % table.length];
        } else {
            Entry runner = table[key.hashCode() % table.length];

            while (runner != null && runner.getKey().equals(key)) {
                runner = runner.getNext();
            }

            return runner;
        }
    }

    // public Entry remove(Key key) {

    // }

    public double loadFactor() {
        return (double) size / table.length;
    }

    public void rehash() {
        Entry[] temp = table.clone();

        if (loadFactor() >= MAX_LOAD_FACTOR) {
            this.table = new Entry[temp.length * 2];

            this.size = 0;

            for (Entry entry : temp) {
                if (entry != null) {
                    Entry tempRunner = temp[entry.getKey().hashCode() % temp.length];

                    while (tempRunner != null) {
                        if (table[tempRunner.getKey().hashCode() % table.length] == null) {
                            table[tempRunner.getKey().hashCode() % table.length] = new Entry(tempRunner.getKey(), tempRunner.getValue());
                        } else {
                            Entry runner = table[entry.getKey().hashCode() % table.length];

                            while (runner.getNext() != null) {
                                runner = runner.getNext();
                            }

                            runner.setNext(new Entry(tempRunner.getKey(), tempRunner.getValue()));
                        }

                        this.size++;

                        tempRunner = tempRunner.getNext();
                    }
                }
            }
        } else {
            // TODO Rehash smaller
        }
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.nextLine();

        // while(scanner.hasNext()) {

        for (int i = 0; i < 100; i++) {
            String[] columns = scanner.nextLine().split(",");

            this.put(new Entry(new Key(Integer.parseInt(columns[0])), new Value(columns[1])));
        }

        scanner.close();

        // }
    }

    // public double collisionRate() {
    //     // TODO Count all entry lengths - 1
    // }

    public Entry[] getTable() {
        return table;
    }

    public void setTable(Entry[] table) {
        this.table = table;
    }

    // @Override
    // public String toString() {

    // }

    public void debugPrint() {
        for(Entry entry: table) {
            System.out.println(entry);
        }
    }
}
