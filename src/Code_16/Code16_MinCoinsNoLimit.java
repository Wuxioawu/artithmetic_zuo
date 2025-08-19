package Code_16;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.HashSet;

public class Code16_MinCoinsNoLimit {

    private static int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) return 0;
        int result = process(arr, 0, aim);
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ways = Integer.MAX_VALUE;
        for (int num = 0; num * arr[index] <= rest; num++) {
            int p1 = process(arr, index + 1, rest - num * arr[index]);
            if (p1 != Integer.MAX_VALUE) {
                ways = Math.min(ways, num + p1);
            }
        }
        return ways;
    }

    private static int minCoins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = aim; rest >= 0; rest--) {
                int ways = Integer.MAX_VALUE;
                for (int num = 0; num * arr[index] <= rest; num++) {
                    int p1 = dp[index + 1][rest - num * arr[index]];
                    if (p1 != Integer.MAX_VALUE) {
                        ways = Math.min(ways, num + p1);
                    }
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim] == Integer.MAX_VALUE ? 0 : dp[0][aim];
    }

    private static int minCoins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) return 0;
        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }

        return dp[0][aim] == Integer.MAX_VALUE ? 0 : dp[0][aim];
    }

    //for test
    private static int[] getMoney(int maxValue, int maxLength) {
        HashSet<Integer> set = new HashSet<>();
        int length = NumberOperation.getRandomNumber(maxLength);

        int[] moneys = new int[length];

        for (int i = 0; i < length; i++) {
            int value;
            do {
                value = NumberOperation.getRandomNumber(maxValue);
            } while (set.contains(value) || value == 0);
            moneys[i] = value;
            set.add(value);
        }
        return moneys;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int[] money = getMoney(20, 10);
            int aim = NumberOperation.getRandomNumber(30);

            int result_1 = minCoins1(money, aim);
            int result_2 = minCoins2(money, aim);
            int result_3 = minCoins3(money, aim);

            if (result_1 != result_2 || result_2 != result_3) {
                System.out.println("result_1: " + result_1 + " result_2: " + result_2 + " result_3: " + result_3);
                ArrayOperation.printArray(money);
                System.out.println("aim : " + aim);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
