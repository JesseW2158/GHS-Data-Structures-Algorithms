package Unit3.Foontry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("./Unit3/Foontry/FoodList.txt"));
        Menu Bertuccis = new Menu();

        for (int i = 0; i < 10; i++) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");

            Foontry key = new Foontry(columns[0], columns[1]);

            HashSet<String> dishes = new HashSet<String>();

            for(int j = 2; j < 6; j++) {
                dishes.add(columns[j]);
            }

            Cuisine value = new Cuisine(dishes);
            Bertuccis.add(new Entree(key, value));
        }

        System.out.println(Bertuccis);
        scanner.close();
    }
}
