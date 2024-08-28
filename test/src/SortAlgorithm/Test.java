package SortAlgorithm;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        MergeSort.mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
