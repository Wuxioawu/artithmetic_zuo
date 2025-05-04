package Code_03;

import tools.ArrayOperation;
import tools.Constants;

public class Code08_GetMax {

    public static int getMax(int[] array) {
        if (ArrayOperation.isEmpty(array)) return Constants.UN_INVALID;
        return process(array, 0, array.length - 1);
    }

    public static int process(int[] array, int left, int right) {
        if (left == right) return array[left];
        int mid = left + (right - left) / 2;
        int leftMax = process(array, left, mid);
        int rightMax = process(array, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }

    public static int getMaxByFor(int[] array) {
        if (ArrayOperation.isEmpty(array)) return Constants.UN_INVALID;
        int max = Constants.UN_INVALID;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        return max;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_MAX_SIZE; i++) {
            int[] array = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int[] copyArray = ArrayOperation.copyArray(array);

            if (getMax(copyArray) != getMaxByFor(array)) {
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
