package Code_16;

import tools.Constants;
import tools.NumberOperation;

import java.util.Arrays;

public class Code12_CoinsWayNoLimit {

    public static int coinsWay1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (rest < 0) return 0;
        if (index == arr.length) return rest == 0 ? 1 : 0;
        int result = 0;
        for (int num = 0; num * arr[index] < rest; num++) {
            result += process(arr, index + 1, rest - arr[index] * num);
        }
        return result;
    }

    private static int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 1;

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = aim; rest > 0; rest--) {
                for (int num = 0; num * arr[index] < rest; num++) {
                    dp[index][rest] += dp[index + 1][rest - arr[index] * num];
                }
            }
        }
        return dp[0][aim];
    }

    // return the various denominations of money
    private static int[] createMoneys(int maxLength, int maxValue) {
        int[] arr = new int[NumberOperation.getRandomNumber(maxLength)];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = NumberOperation.getRandomNumber(maxValue);
        }
        return arr;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 100; i++) {
            int[] moneys = {3, 18, 4, 11, 7, 3, 6, 18};
            createMoneys(10, 20);
            int aim = 22;
            NumberOperation.getRandomNumber(50);
            int result_1 = coinsWay1(moneys, aim);
            int result_2 = coinsWay2(moneys, aim);

            if (result_1 != result_2) {
                System.out.println("result_1 :" + result_1 + "  result_2: " + result_2);
                System.out.println(Arrays.toString(moneys));
                System.out.println(aim);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }


    public static void main(String[] args) {
        test();
    }
}
