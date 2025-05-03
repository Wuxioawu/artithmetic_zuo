package Code_01;

import tools.ArrayOperation;

public class Code03_InsertionSort {

    public static void insertionSort(int[] arr) {
        if(!ArrayOperation.isSortable(arr)) return;

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]) {
                    ArrayOperation.swap(arr, j, j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayOperation.testSort(arr -> insertionSort(arr));
    }
}

