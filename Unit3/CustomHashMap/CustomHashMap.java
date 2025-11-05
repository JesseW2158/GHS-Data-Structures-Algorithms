package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomHashMap {
    private Entry[] table;

    public CustomHashMap(Entry[] table) {
        this.table = table;
    }

    public void loadFromFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        
        
    }

    public Entry[] getTable() {
        return table;
    }

    public void setTable(Entry[] table) {
        this.table = table;
    }
}
