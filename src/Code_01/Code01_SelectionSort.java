package Code_01;

import tools.ArrayOperation;

public class Code01_SelectionSort {
    public static void main(String[] args) {
        ArrayOperation.testSort(arr -> selectionSort(arr));
    }

    public static void selectionSort(int[] arr) {
        if(arr == null || arr.length < 2) return;

        // 0 ~ N-1
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            ArrayOperation.swap(arr, i, minIndex);

        }
    }
}
