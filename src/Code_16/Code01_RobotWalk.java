package Code_16;

import tools.Constants;
import tools.NumberOperation;

/**
 * 会撞墙的杨辉三角形
 */
public class Code01_RobotWalk {

    private static int way1(int start, int K, int aim, int N) {

        if (N <= 1 || K <= 0 || start < 1 || start > N || aim > N || aim < 1) {
            return -1;
        }

        return process1(start, K, aim, N);
    }

    /**
     * @param cur  the current position of the robot
     * @param rest the rest step of the robot
     * @param aim  ultimate position
     * @param N    1~N
     * @return a robot start from cur, by rest step, ultimately stop at aim,
     * how many ways?
     */

    private static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }

        if (cur == 1) {
            return process1(cur + 1, rest - 1, aim, N);
        }

        if (cur == N) {
            return process1(cur - 1, rest - 1, aim, N);
        }

        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    private static int way2(int start, int K, int aim, int N) {
        if (N <= 1 || K <= 0 || start < 1 || start > N || aim > N || aim < 1) {
            return -1;
        }

        int[][] buffer = new int[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                buffer[i][j] = -1;
            }
        }
        return process2(start, K, aim, N, buffer);
    }

    private static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(cur + 1, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(cur - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);

        }
        dp[cur][rest] = ans;
        return ans;
    }

    // get the result conclusion, create the dp by calculate
    private static int way3(int start, int K, int aim, int N) {
        if (N <= 1 || K <= 0 || start < 1 || start > N || aim > N || aim < 1) {
            return -1;
        }
        int[][] buffer = new int[N + 1][K + 1];
        buffer[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            buffer[1][rest] = buffer[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                buffer[cur][rest] = buffer[cur - 1][rest - 1] + buffer[cur + 1][rest - 1];
            }
            buffer[N][rest] = buffer[N - 1][rest - 1];
        }
        return buffer[start][K];
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 1; i <= 1000; i++) {
            int N = NumberOperation.getRandomNumber(10);
            int start = NumberOperation.getRandomNumber(10);
            int aim = NumberOperation.getRandomNumber(10);
            int K = NumberOperation.getRandomNumber(10);

            int result_1 = way1(N, start, aim, K);
            int result_2 = way2(N, start, aim, K);
            int result_3 = way3(N, start, aim, K);

            if (result_1 != result_2 || result_1 != result_3) {
                System.out.println(Constants.CODE_ERROR);
                System.out.println("result_1 : " + result_1 + " result_2:  " + result_2 + " result_3:  " + result_3);

                System.out.println("N: " + N + " start: " + start + " aim: " + aim + " K: " + K);

                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
