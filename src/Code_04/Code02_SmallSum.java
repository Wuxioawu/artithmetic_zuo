package Code_04;

import tools.ArrayOperation;
import tools.Constants;

/**
 * For each number in the array,
 * sum all the numbers to its left that are smaller than it.
 * The total of all such sums is called the "small sum".
 */
public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return Constants.UN_INVALID;
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) return 0;

        int mid = left + (right - left) / 2;
        return process(arr, left, mid) +
                process(arr, mid + 1, right) +
                merger(arr, left, mid, right);

    }

    public static int merger(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int index = 0;
        int p1 = left;
        int p2 = mid + 1;

        int totalSum = 0;

        while (p1 <= mid && p2 <= right) {
            totalSum += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];

        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }

        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
        return totalSum;
    }

    public static int smallSumByFor(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return Constants.UN_INVALID;

        int totalSum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    totalSum += arr[j];
                }
            }
        }
        return totalSum;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(10, 10);
            int[] copyArray = ArrayOperation.copyArray(array);

            int[] originalArr = ArrayOperation.copyArray(array);

            int result1 = smallSum(array);
            int resultByFor = smallSumByFor(copyArray);

            if (result1 != resultByFor) {
                System.out.println("result1: " + result1 + "  resultByFor: " + resultByFor);
                ArrayOperation.printArray(originalArr);
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
