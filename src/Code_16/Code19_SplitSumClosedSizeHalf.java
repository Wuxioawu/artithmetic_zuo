package Code_16;

import tools.ArrayOperation;
import tools.Constants;

public class Code19_SplitSumClosedSizeHalf {

    private static int rightSum1(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        if (arr.length % 2 == 0) {
            return process1(arr, 0, arr.length / 2, sum / 2);
        } else {
            int v1 = process1(arr, 0, arr.length / 2, sum / 2);
            int v2 = process1(arr, 0, arr.length / 2 + 1, sum / 2);
            return Math.max(v1, v2);
        }
    }

    private static int process1(int[] arr, int index, int count, int rest) {
        if (index == arr.length)
            return count == 0 ? 0 : -1;

        int p1 = process1(arr, index + 1, count, rest);
        int next = -1;
        if (rest >= arr[index]) {
            next = process1(arr, index + 1, count - 1, rest - arr[index]);
            if (next != -1) {
                next += arr[index];
            }
        }
        return Math.max(p1, next);
    }

    private static int rightSum2(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }

        int[][][] dp = new int[arr.length + 1][arr.length / 2 + 2][sum / 2 + 1];

        for (int index = 0; index < dp.length; index++) {
            for (int count = 0; count < dp[0].length; count++) {
                for (int rest = 0; rest < dp[0][0].length; rest++) {
                    if (index == arr.length && count == 0) {
                        dp[index][count][rest] = 0;
                    } else {
                        dp[index][count][rest] = -1;
                    }
                }
            }
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int count = 0; count < arr.length / 2 + 2; count++) {
                for (int rest = 0; rest < sum / 2 + 1; rest++) {
                    int p1 = dp[index + 1][count][rest];
                    int next = -1;
                    if (rest >= arr[index] && count >= 1) {
                        next = dp[index + 1][count - 1][rest - arr[index]];
                        if (next != -1) {
                            next += arr[index];
                        }
                    }
                    dp[index][count][rest] = Math.max(p1, next);
                }
            }
        }

        if (arr.length % 2 == 0) {
            return dp[0][arr.length / 2][sum / 2];
        } else {
            int v1 = dp[0][arr.length / 2][sum / 2];
            int v2 = dp[0][arr.length / 2 + 1][sum / 2];
            return Math.max(v1, v2);
        }
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
