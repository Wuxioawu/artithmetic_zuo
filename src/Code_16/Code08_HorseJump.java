package Code_16;

import tools.Constants;
import tools.NumberOperation;

public class Code08_HorseJump {

    public static int jump1(int a, int b, int k) {
        if (a < 0 || a >= 9 || b < 0 || b >= 10) return 0;
        return process(0, 0, a, b, k);
    }

    private static int process(int x, int y, int a, int b, int rest) {
        if (x < 0 || x >= 9 || y < 0 || y >= 10) return 0;
        if (rest == 0) {
            return x == a && y == b ? 1 : 0;
        }

        int ways = process(x - 2, y + 1, a, b, rest - 1);
        ways += process(x - 1, y + 2, a, b, rest - 1);
        ways += process(x + 1, y + 2, a, b, rest - 1);
        ways += process(x + 2, y + 1, a, b, rest - 1);

        ways += process(x - 1, y - 2, a, b, rest - 1);
        ways += process(x + 1, y - 2, a, b, rest - 1);
        ways += process(x - 2, y - 1, a, b, rest - 1);
        ways += process(x + 2, y - 1, a, b, rest - 1);

        return ways;
    }

    private static int jump2(int a, int b, int k) {
        int[][][] dp = new int[k + 1][9][10];
        dp[0][a][b] = 1;

        for (int i = 1; i <= k; i++) {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 10; y++) {
                    int ways = getValue(i - 1, x - 2, y + 1, dp);
                    ways += getValue(i - 1, x - 1, y + 2, dp);
                    ways += getValue(i - 1, x + 1, y + 2, dp);
                    ways += getValue(i - 1, x + 2, y + 1, dp);

                    ways += getValue(i - 1, x - 1, y - 2, dp);
                    ways += getValue(i - 1, x + 1, y - 2, dp);
                    ways += getValue(i - 1, x - 2, y - 1, dp);
                    ways += getValue(i - 1, x + 2, y - 1, dp);
                    dp[i][x][y] = ways;
                }
            }
        }

        return dp[k][0][0];
    }

    private static int getValue(int rest, int x, int y, int[][][] dp) {
        if (x < 0 || x >= 9 || y < 0 || y >= 10) return 0;

        return dp[rest][x][y];
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 1; i <= 100; i++) {
            int a = NumberOperation.getRandomNumber(9);
            int b = NumberOperation.getRandomNumber(10);
            int steps = NumberOperation.getRandomNumber(15);

            int result_1 = jump1(a, b, steps);
            int result_2 = jump2(a, b, steps);

            if (result_1 != result_2) {
                System.out.println("a: " + a + " b: " + b + " steps: " + steps);
                System.out.println(result_1 + " " + result_2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
