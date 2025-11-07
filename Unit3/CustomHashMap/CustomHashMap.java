package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomHashMap {
    private Entry[] table;

    public CustomHashMap() {
        this.table = new Entry[10];
    }

    public CustomHashMap(Entry[] table) {
        this.table = table;
    }

    public Entry put(Entry entry) {
        Entry prev = table[entry.getKey().hashCode() % table.length];

        if(prev == null) {
            table[entry.getKey().hashCode() % table.length] = entry;
        } else {
            Entry runner = table[entry.getKey().hashCode() % table.length];
            boolean inserted = false;

            while(runner.getNext() != null) {
                if(runner.getNext().getKey().equals(entry.getKey())) {
                    Entry next = table[entry.getKey().hashCode() % table.length].getNext().getNext();

                    runner.setNext(entry);
                    runner.getNext().setNext(next);

                    inserted = true;

                    break;
                }

                runner = runner.getNext();
            }

            if(!inserted) {
                runner.setNext(entry);
            }
        }

        System.out.println(loadFactor());
        if(loadFactor() > 0.75) {
            rehash();
        }

        return prev;
    }

    public Entry get(Key key) {
        if(table[key.hashCode() % table.length].getNext() == null) {
            return table[key.hashCode() % table.length];
        } else {
            Entry runner = table[key.hashCode() % table.length];

            while(runner != null && runner.getKey().equals(key)) {
                runner = runner.getNext();
            }

            return runner;
        }
    }

    // public Entry remove(Key key) {

    // }

    public double loadFactor() {
        int occupied = 0;

        for(Entry entry : table) {
            if(entry != null) {
                occupied++;
            }
        }

        return (double) occupied / table.length;
    }

    // TODO When rehashing, rehash entries in buckets that have linked lists of entries
    public void rehash() {
        Entry[] temp = table;
        table = new Entry[temp.length * 2];
        
        for (Entry entry : temp) {
            if (entry != null) {
                Entry runner = new Entry()
                runner.setNext(table[entry.getKey().hashCode() % table.length]);

                while(runner.getNext() != null) {
                    this.put(runner);

                    runner = runner.getNext();
                }
            }
        }
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        scanner.nextLine();

        // while(scanner.hasNext()) {

        for(int i = 0; i < 10; i++) {
            String[] columns = scanner.nextLine().split(",");

            this.put(new Entry(new Key(Integer.parseInt(columns[0])), new Value(columns[1])));
        }

        scanner.close();

        // }
    }

    public Entry[] getTable() {
        return table;
    }

    public void setTable(Entry[] table) {
        this.table = table;
    }
}
