package Unit4;

import java.util.Arrays;

public class Quads {
    public static void main(String[] args) {
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }

        System.out.println(Arrays.toString(arr));
        recursiveSelection(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void recursiveSelection(int[] arr) {
        recursiveSelectionHelper(arr, 0);
    }

    public static void recursiveSelectionHelper(int[] arr, int i) {
        if (i >= arr.length - 1) {
            return;
        }

        int minSpot = findSmallest(arr, i);

        int temp = arr[i];
        arr[i] = arr[minSpot];
        arr[minSpot] = temp;

        recursiveSelectionHelper(arr, i+1);
    }

    public static int findSmallest(int[] arr, int i) {
        if (i == arr.length - 1) {
            return i;
        }

        int minIndex = findSmallest(arr, i + 1);
        return arr[minIndex] < arr[i] ? minIndex : i;
    }
}