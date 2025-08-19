package Code_16;

import tools.Constants;
import tools.NumberOperation;

public class Code17_SplitNumber {

    public static int ways1(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return process(1, n);
    }

    private static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }

    public static int ways2(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int ways3(int n) {
        if (n <= 0) return 0;

        if (n == 1) return 1;

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre - 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {
            int n = NumberOperation.getRandomNumber(20);
            int result_1 = ways1(n);
            int result_2 = ways2(n);
            int result_3 = ways3(n);

            if (result_1 != result_2 && result_2 != result_3) {
                System.out.println("result_1: " + result_1 + ", result_2: " + result_2 + ", result_3: " + result_3);
                System.out.println("N: " + n);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
