package Code_16;

import tools.Constants;
import tools.NumberOperation;

import java.util.Arrays;

/**
 * 推导表结构
 */
public class Code11_CoinsWayEveryPaperDifferent {

    public static int coinWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) return 0;
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) return 0;

        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        return process(arr, index + 1, rest - arr[index]) + process(arr, index + 1, rest);
    }


    public static int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) return 0;

        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] < 0 ? 0 : dp[index + 1][rest - arr[index]]);
            }
        }
        return dp[0][aim];
    }

    private static int[] getMoneys(int maxLength) {
        int[] moneys = {1, 2, 5, 10, 50, 100};
        int[] result = new int[NumberOperation.getRandomNumber(maxLength)];
        for (int i = 0; i < result.length; i++) {
            result[i] = moneys[NumberOperation.getRandomNumber(moneys.length)];
        }
        return result;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 1; i <= Constants.TEST_TIMES; i++) {
            int[] moneys = getMoneys(10);
            int aim = NumberOperation.getRandomNumber(50);
            int result_1 = coinWays1(moneys, aim);
            int result_2 = coinWays2(moneys, aim);
            if (result_1 != result_2) {
                System.out.println(Arrays.toString(moneys));
                System.out.println("aim: " + aim);
                System.out.println("result_1 = " + result_1 + "; result_2 = " + result_2);
                return;
            }
        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
