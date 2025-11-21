package Unit4;

import java.util.Arrays;

public class LinkSort {
    public static void main(String[] args) {
       int[] arr = new int[10];

        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 10);
        }

        System.out.println(Arrays.toString(arr));
        recursiveBubbleSort(arr, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for(int j = 0 ; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }

            if(!swapped) {
                break;
            }
        }
    }

    public static void recursiveBubbleSort(int[] arr, int length) {
        if(arr.length == 1) {
            return;
        }

        boolean swapped = false;

        for(int i = 0; i < length - 1; i++) {
            if(arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                swapped = true;
            }
        }

        if(!swapped) {
            return;
        }

        System.out.println(Arrays.toString(arr));
        recursiveBubbleSort(arr, length - 1);
    }

    public static void selectionSort() {
        
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

    public static void HoaresPartition() {

    }

    public static void quickSelect() {

    }
}
