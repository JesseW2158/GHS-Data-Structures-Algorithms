package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();

        try {
            map.loadFromFile(new File("Unit3/CustomHashMap/Health_Inspection_Scores_(2016-2019)_20251105.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
