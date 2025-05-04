package Code_04;

import tools.ArrayOperation;
import tools.Constants;

/**
 * For each number in the array, find out how many numbers to its right satisfy:
 * num_on_right * 2 < current_num
 */
public class Code04_BiggerThanRightTwice {

    public static int getBiggerThanRightTwice(int[] array) {
        if (!ArrayOperation.isSortable(array)) return 0;
        return process(array, 0, array.length - 1);
    }

    public static int process(int[] array, int left, int right) {
        if (left == right) return 0;

        int mid = left + (right - left) / 2;

        return process(array, left, mid) +
                process(array, mid + 1, right) +
                merge(array, left, mid, right);
    }

    public static int merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int result = 0;
        int windowR = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (windowR <= right && (long) array[i] > array[windowR] * 2L) {
                windowR++;
            }
            result += windowR - mid - 1;
        }

        int index = 0;
        int p1 = left;
        int p2 = mid + 1;

        while (p1 <= mid && p2 <= right) {
            temp[index++] = array[p1] < array[p2] ? array[p1++] : array[p2++];
        }

        while (p1 <= mid) {
            temp[index++] = array[p1++];
        }

        while (p2 <= right) {
            temp[index++] = array[p2++];
        }

        System.arraycopy(temp, 0, array, left, temp.length);
        return result;
    }

    public static int getBiggerThanRightTwiceByFor(int[] array) {
        if (!ArrayOperation.isSortable(array)) return 0;

        int result = 0;
        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j] * 2) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int[] copArray = ArrayOperation.copyArray(array);

            int[] originalArray = ArrayOperation.copyArray(array);

            int result = getBiggerThanRightTwice(array);
            int resultByFor = getBiggerThanRightTwiceByFor(copArray);

            if (result != resultByFor) {

                System.out.println("result: " + result + " resultByFor: " + resultByFor);
                ArrayOperation.printArray(originalArray);

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
