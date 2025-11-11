package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

public class Driver {
    public static void main(String[] args) {
        JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));

        int code = file.showOpenDialog(null);

        if(code == JFileChooser.APPROVE_OPTION) {
            System.out.println("File selected");
        } else {
            System.out.println("File not selected");
        }

        CustomHashMap map = new CustomHashMap();

        try {
            map.loadFromFile((file.getSelectedFile()));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }

        map.debugPrint();
    }
}
