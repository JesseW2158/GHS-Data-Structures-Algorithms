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
        // TODO Add linked list capabilities 😞
        Entry prev = table[entry.hashCode() % table.length];

        if(prev == null) {
            table[entry.hashCode() % table.length] = entry;
        } else {
            // TODO traverse to end of entry's value linked list and add to end
            Entry runner = table[entry.hashCode() % table.length];

            while(table[entry.hashCode() % table.length].getNext() != null) {
                runner = runner.getNext();
            }
        }

        if(loadFactor() > 0.75) {
            rehash();
        }

        return prev;
    }

    public Entry get(Key key) {
        return table[key.hashCode() % table.length];
    }

    public Entry remove(Key key) {

    }

    public double loadFactor() {
        int occupied = 0;

        for(Entry entry : table) {
            if(entry != null) {
                occupied++;
            }
        }

        return (double) occupied / table.length;
    }

    public void rehash() {
        Entry[] temp = table;
        table = new Entry[temp.length * 2];
        
        for (Entry entry : temp) {
            if (entry != null) {
                table[entry.hashCode() % table.length] = entry;
            }
        }
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNext()) {

        }
    }

    public Entry[] getTable() {
        return table;
    }

    public void setTable(Entry[] table) {
        this.table = table;
    }
}
