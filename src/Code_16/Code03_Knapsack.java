package Code_16;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

/**
 * 从左往右的尝试模型
 */
public class Code03_Knapsack {

    private static int maxValue1(int[] weight, int[] value, int bag) {
        if (weight == null || weight.length == 0 || value == null || value.length == 0) return 0;
        return process(weight, value, bag, 0);
    }

    //the max value of the current situation
    private static int process(int[] weight, int[] value, int rest, int index) {
        if (rest < 0)
            return -1;

        if (index == value.length) {
            return 0;
        }

        int p1 = 0;
        int next = process(weight, value, rest - weight[index], index + 1);
        if (next != -1) {
            p1 = value[index] + next;
        }

        int p2 = process(weight, value, rest, index + 1);
        return Math.max(p1, p2);
    }

    private static int maxValue2(int[] weight, int[] value, int bag) {
        if (weight == null || weight.length == 0 || value == null || value.length == 0) return 0;

        int[][] dp = new int[weight.length + 1][bag + 1];

        for (int index = weight.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - weight[index] < 0 ? -1 : dp[index + 1][rest - weight[index]];
                if (next != -1) {
                    p2 = value[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    // for test
    private static int[] getValue(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = NumberOperation.getRandomNumber(maxValue);
        }
        return arr;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int[] weight = ArrayOperation.getRandomArray(10, 50);
            int[] value = getValue(weight.length, 10);
            int bag = NumberOperation.getRandomNumber(200);

            int result_1 = maxValue1(weight, value, bag);
            int result_2 = maxValue2(weight, value, bag);

            if (result_1 != result_2) {
                ArrayOperation.printArray(weight);
                ArrayOperation.printArray(value);
                System.out.println("result_1 = " + result_1 + ", result_2 = " + result_2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
