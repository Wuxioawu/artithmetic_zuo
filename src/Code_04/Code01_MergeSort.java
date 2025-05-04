package Code_04;

import tools.ArrayOperation;
import tools.Constants;

public class Code01_MergeSort {

    public static void mergeSortByRecursion(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            temp[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }
        // put values from temp to original array
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void mergeSortByStack(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;
        int step = 1;
        while (step < arr.length) {
            int left = 0;

            while (left + step < arr.length) {
                int mid = left + step - 1;
                int right = Math.min(mid + step, arr.length - 1);
                merge(arr, left, mid, right);
                left = right + 1;
            }
            // prevent integer overflow
            if (step > arr.length / 2) break;
            step <<= 1;
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int[] copyArr = ArrayOperation.copyArray(array);

            mergeSortByRecursion(array);
            mergeSortByStack(copyArr);

            if (!ArrayOperation.isEqualArrays(array, copyArr)) {
                System.out.println(Constants.CODE_ERROR);
                ArrayOperation.printArray(array);
                ArrayOperation.printArray(copyArr);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}












