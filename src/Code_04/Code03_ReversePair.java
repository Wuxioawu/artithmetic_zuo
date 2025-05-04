package Code_04;

import tools.ArrayOperation;
import tools.Constants;


/**
 * In an array,a reverse pair is a pair where the number
 * on the left is greater than a number on the right,
 * and the two numbers are not adjacent.
 */
public class Code03_ReversePair {


    public static int reversePairs(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return 0;
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

        int index = temp.length - 1;
        int p1 = mid;
        int p2 = right;
        int result = 0;

        while (p1 >= left && p2 > mid) {
            result += arr[p2] < arr[p1] ? p2 - mid : 0;
            temp[index--] = arr[p1] <= arr[p2] ? arr[p2--] : arr[p1--];
        }

        while (p1 >= left) {
            temp[index--] = arr[p1--];
        }

        while (p2 > mid) {
            temp[index--] = arr[p2--];
        }
        System.arraycopy(temp, 0, arr, left, temp.length);
        return result;
    }

    public static int reversePairsByFor(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return 0;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {

            int[] array = ArrayOperation.getRandomArray(10, 10);
            int[] copyArray = ArrayOperation.copyArray(array);

            int[] originalArray = ArrayOperation.copyArray(array);

            int reverse = reversePairs(copyArray);
            int reverseByFor = reversePairsByFor(array);

            if (reverseByFor != reverse) {
                ArrayOperation.printArray(originalArray);
                System.out.println("reverse: " + reverse + ", reverseByFor: " + reverseByFor);
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
