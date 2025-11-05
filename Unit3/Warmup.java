package Unit3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;

public class Warmup {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> fruitMap = new HashMap<String, HashSet<String>>();

        fruitMap.put("Yellow", new HashSet<String>(Arrays.asList("Banana", "Lemon", "Apple")));
        fruitMap.put("Red", new HashSet<>(Arrays.asList("Apple", "Strawberry")));
        fruitMap.put("Green", new HashSet<>(Arrays.asList("Apple", "Kiwi", "Banana")));

        fruitMap.get("Red").add("Raspberry");
        fruitMap.remove("Brown");

        String[] moreGreen = {"Melon", "Avocado"};

        for(String green : moreGreen) {
            fruitMap.get("Green").add(green);
        }

        printHash(fruitMap);
        System.out.println();

        for(String key: fruitMap.keySet()) {
            if(fruitMap.get(key).contains("Apple")) {
                System.out.println(key);
            }
        }
    }

    public static <K, V> void printHash(HashMap<K, ? extends Iterable<V>> map) {
        String output = "";

        for (K key: map.keySet()) {
            output += key + ": ";
            for (V value: map.get(key)) {
                output += value + ", ";
            }
            output += "\n";
        }

        System.out.print(output);
    }
}
