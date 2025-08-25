package Code_17;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.HashMap;
import java.util.Map;

//todo: need to learn how to modify the slop rate modify
public class Code04_MinCoinsOnePaper {

    // by adjust if is exist
    private static int getMinCount1(int[] arr, int aim) {
        if (arr.length == 0 || aim == 0) return 0;
        int ans = process1(arr, 0, aim);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private static int process1(int[] arr, int index, int rest) {
        if (rest < 0) return Integer.MAX_VALUE;
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int p1 = process1(arr, index + 1, rest);
        int p2 = Integer.MAX_VALUE;
        if (rest - arr[index] >= 0) {
            p2 = process1(arr, index + 1, rest - arr[index]);
            if (p2 != Integer.MAX_VALUE) {
                p2++;
            }
        }
        return Math.min(p1, p2);
    }

    private static int getMinCount2(int[] arr, int aim) {
        if (arr.length == 0 || aim == 0) return 0;

        int[][] dp = new int[arr.length + 1][aim + 1];

        dp[arr.length][0] = 0;

        for (int i = 1; i <= aim; i++) {
            dp[arr.length][i] = Integer.MAX_VALUE;
        }

        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = aim; rest >= 0; rest--) {
                int p1 = dp[index + 1][rest];
                int p2 = Integer.MAX_VALUE;
                if (rest - arr[index] >= 0) {
                    p2 = dp[index + 1][rest - arr[index]];
                    if (p2 != Integer.MAX_VALUE) {
                        p2++;
                    }
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim] == Integer.MAX_VALUE ? 0 : dp[0][aim];
    }

    private static int getMinCount3(int[] arr, int aim) {
        if (arr.length == 0 || aim == 0) return 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) + 1);
        }
        int N = countMap.size();
        int[] money = new int[N];
        int[] count = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            money[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        return process3(money, count, 0, aim);
    }

    private static int process3(int[] money, int[] count, int index, int rest) {
        if (rest < 0) return Integer.MAX_VALUE;

        if (index == money.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int ways = Integer.MAX_VALUE;

        for (int num = 0; num <= count[index]; num++) {
            int p1 = process3(money, count, index + 1, rest - money[index] * num);
            if (p1 != Integer.MAX_VALUE) {
                ways += Math.min(p1, num + 1);
            }
        }
        return ways;
    }

    private static int getMinCount4(int[] arr, int aim) {
        return -1;
    }

    // for test
    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 10000; i++) {
            int[] arr = {5, 9, 1, 6, 5, 1, 10};
            ArrayOperation.getRandomArray(20, 10);
            int aim = 10;
            NumberOperation.getRandomNumber(20);

            int result_1 = getMinCount2(arr, aim);
            int result_2 = getMinCount3(arr, aim);

            if (result_1 != result_2) {
                ArrayOperation.printArray(arr);
                System.out.println(aim);
                System.out.println("result_1: " + result_1 + ", result_2: " + result_2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
