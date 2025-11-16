package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;

// TODO REHASH DONE CHECK PUT
public class Driver {
    public static void main(String[] args) {
        // JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));

        // int code = file.showOpenDialog(null);

        // if(code == JFileChooser.APPROVE_OPTION) {
        //     System.out.println("File selected");
        // } else {
        //     System.out.println("File not selected");
        // }

        // CustomHashMap map = new CustomHashMap();

        // try {
        //     map.loadFromFile((file.getSelectedFile()));
        // } catch (FileNotFoundException e) {
        //     System.err.println("File not found: " + e.getMessage());
        //     e.printStackTrace();
        // }

        System.out.println("\n\n\n");

        CustomHashMap map = new CustomHashMap();

        try {
            map.loadFromFile(new File("Unit3//CustomHashMap//Health_Inspection_Scores_(2016-2019)_20251105.csv"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println();
    }
}
