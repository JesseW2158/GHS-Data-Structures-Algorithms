package Unit3.CustomHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Driver {
    public static void main(String[] args) {
        JFileChooser file = new JFileChooser(new File(System.getProperty("user.dir")));
        boolean fileFound = false;

        while (!fileFound) { // Ensures some file has been selected
            if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                System.out.println("File selected.");
                fileFound = true;
            } else {
                System.out.println("File not selected. Please try again.");
            }
        }

        CustomHashMap map = new CustomHashMap();

        try {
            map.loadFromFile((file.getSelectedFile()));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        // Clear terminal
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner input = new Scanner(System.in);
        Boolean finished = false;

        while (!finished) {
            System.out.println(map);

            System.out.println(
                    "What would you like to do? Put (1), Remove (2), Get(3), or are you finished using the program (4)? ");

            int choice = input.nextInt();

            int key;

            switch (choice) {
                case 1: // If choosing to put new entry
                    System.out.println("What is your business ID? Please provide a number.");
                    key = input.nextInt();
                    input.nextLine();

                    System.out.println("What is your restaurant name?");
                    String value = input.nextLine();

                    System.out.println("Do you have a specific location to insert this entry? True or False");
                    boolean knownLocation = input.nextBoolean();
                    System.out.println("\n");

                    Value prev;

                    if (!knownLocation) {
                        prev = map.put(new Entry(new Key(key), new Value(value)));
                    } else {
                        System.out.println("Where would you like this entry to go? Please provide a number.");

                        prev = map.put(input.nextInt(), new Entry(new Key(key), new Value(value)));
                    }

                    System.out.println("The previous business with this ID was: " + prev);

                    break;
                case 2: // If choosing to remove a entry
                    System.out.println("What is your business ID? Please provide a number.");

                    System.out.println("The business with this ID was: " + map.remove(new Key(input.nextInt())));

                    break;
                case 3: // If choosing to get an entry
                    System.out.println("What is your business ID? Please provide a number.");

                    System.out.println("Your business name is: " + map.get(new Key(input.nextInt())));

                    break;
                default: // If finished with the program
                    finished = true;
            }

            if(!finished) {
                System.out.println();
                try { // Pauses to allow reading
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Thank you for using the program!");

        input.close();
    }
}
