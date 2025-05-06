package Code_06;

import tools.ArrayOperation;
import tools.Constants;

public class Code02_HeapSort {

    public static void heapSort(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;

        // build heap from head
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        // build heap from tail
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //sort
        int size = arr.length;
        while (size > 0) {
            ArrayOperation.swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }


    public static void heapInsert(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        while (arr[index] > arr[parentIndex]) {
            ArrayOperation.swap(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {

        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex >= heapSize) return;

        int rightChildIndex = leftChildIndex + 1;
        int largestIndex = rightChildIndex < heapSize && arr[rightChildIndex] > arr[leftChildIndex] ?
                rightChildIndex : leftChildIndex;

        while (arr[index] < arr[largestIndex]) {
            ArrayOperation.swap(arr, index, largestIndex);
            index = largestIndex;

            leftChildIndex = 2 * index + 1;
            if (leftChildIndex >= heapSize) break;

            rightChildIndex = leftChildIndex + 1;
            largestIndex = rightChildIndex < heapSize && arr[rightChildIndex] > arr[leftChildIndex] ?
                    rightChildIndex : leftChildIndex;
        }

    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] randomArray = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int[] copyArray = ArrayOperation.copyArray(randomArray);
            int[] originalArray = ArrayOperation.copyArray(randomArray);
            heapSort(randomArray);
            ArrayOperation.sort(copyArray);

            if (!ArrayOperation.isEqualArrays(randomArray, copyArray)) {
                ArrayOperation.printArray(originalArray);
                ArrayOperation.printArray(copyArray);
                ArrayOperation.printArray(randomArray);
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
