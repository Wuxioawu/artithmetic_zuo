package Code_01;

import tools.ArrayOperation;

public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length < 2) return;

        for(int i = arr.length - 1; i > 0; i--) {

            for(int j = 0; j < i ; j++) {
                if(arr[j] > arr[j+1]) {
                    ArrayOperation.swap(arr, j, j+1);
                }
            }

        }
    }



    public static void main(String[] args) {
        ArrayOperation.testSort(arr -> bubbleSort(arr));
    }
}
