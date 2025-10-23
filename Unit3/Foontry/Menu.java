package Unit3.Foontry;

import java.util.Arrays;

public class Menu {
    Entree[] menu;
    int size;

    public static final double MAXLOAD = .75;

    public Menu() {
        menu = new Entree[10];
        size = 0;
    }

    public Cuisine add(Entree entree) {
        int index = entree.hashCode() % menu.length;

        do {
            if (menu[index] == null) {
                menu[index] = entree;
                size++;

                if (1.0 * size / menu.length >= MAXLOAD) {
                    rehash();
                }

                return null;
            }

            if (menu[index].key.equals(entree.key)) {
                Cuisine temp = menu[index].value;
                menu[index].value = entree.value;

                return temp;
            }

            index = (index + 1) % menu.length;
        } while (true);
    }

    public void rehash() {

    }

    @Override
    public String toString() {
        String output = "";
        int count = 0;

        for (Entree entree : menu) {
            if (entree != null) {
                output += count++ + ": " + entree + "\n";
            }
        }

        return output;
    }
}
