package Code_16;

import tools.ArrayOperation;
import tools.Constants;

public class Code18_SplitSumClosed {

    private static int rightSum1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return process(0, arr, sum / 2);
    }

    // the value of return is no more than the rest
    private static int process(int index, int[] arr, int rest) {
        if (index == arr.length) {
            return 0;
        }
        int p2 = process(index + 1, arr, rest);
        int p1 = -1;
        if (arr[index] <= rest) {
            p1 = arr[index] + process(index + 1, arr, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }

    private static int rightSum2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }

        int[][] dp = new int[arr.length + 1][sum / 2 + 1];
        for (int i = 0; i <= sum / 2; i++) {
            dp[arr.length][i] = 0;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                if (arr[index] <= rest) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum / 2];
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int[] array = ArrayOperation.getRandomArray(10, 10);
            int result_1 = rightSum1(array);
            int result_2 = rightSum2(array);
            if (result_1 != result_2) {
                System.out.println("result_1: " + result_1 + ", result_2: " + result_2);
                ArrayOperation.printArray(array);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
