package Unit4;

import java.util.Arrays;

public class Sorts {
    public static void main(String[] args) {
        int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static void recursiveBubbleSort(int[] arr, int length) {
        if (arr.length == 1) {
            return;
        }

        boolean swapped = false;

        for (int i = 0; i < length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                swapped = true;
            }
        }

        if (!swapped) {
            return;
        }

        System.out.println(Arrays.toString(arr));
        recursiveBubbleSort(arr, length - 1);
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min_index = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min_index]) {
                    min_index = j;
                }
            }

            swap(arr, i, min_index);
        }
    }

    public static void recursiveSelectionSort() {

    }

    public static void insertionSort() {

    }

    public static void recursiveInsertionSort() {

    }

    public static void mergeSort() {

    }

    public static void quickSort() {

    }

    public static void hoaresPartition() {

    }

    public static void quickSelect() {

    }

    public static void radixSort(int[] arr) {
        int maxDigitCount = 0;

        for (int element : arr) {
            maxDigitCount = Math.max(maxDigitCount, (element + "").length());
        }

        System.out.println(maxDigitCount);

        for (int i = 0; i < maxDigitCount; i++) {
            int[] count = new int[10];

            for (int element : arr) {
                count[(int)((element + "").toCharArray()[i])]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] += count[j - 1];
            }

            int[] auxillary = new int[arr.length];

            for (int j = arr.length - 1; j >= 0; j--) {
                auxillary[--count[arr[j]]] = arr[j];
            }

            for (int j = 0; j < auxillary.length; j++) {
                arr[j] = auxillary[j];
            }
        }
    }
}
