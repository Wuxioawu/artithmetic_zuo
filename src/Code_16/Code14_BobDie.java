package Code_16;

import tools.Constants;
import tools.NumberOperation;

public class Code14_BobDie {

    public static double livePosibility1(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= M) return 0;
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    private static int process(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= M) return 0;
        if (k == 0) return 1;
        return process(row - 1, col, k - 1, N, M) +
                process(row + 1, col, k - 1, N, M) +
                process(row, col - 1, k - 1, N, M) +
                process(row, col + 1, k - 1, N, M);
    }

    public static double livePosibility2(int row, int col, int k, int N, int M) {
        if (row < 0 || row >= N || col < 0 || col >= M) return 0;

        long[][][] dp = new long[k + 1][N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[0][i][j] = 1;
            }
        }

        for (int i = 1; i <= k; i++) {
            for (int row_index = 0; row_index < N; row_index++) {
                for (int col_index = 0; col_index < M; col_index++) {
                    dp[i][row_index][col_index] =
                            pickUp(i - 1, row_index - 1, col_index, dp) +
                                    pickUp(i - 1, row_index + 1, col_index, dp) +
                                    pickUp(i - 1, row_index, col_index - 1, dp) +
                                    pickUp(i - 1, row_index, col_index + 1, dp);


                }
            }
        }
        return (double) dp[k][row][col] / Math.pow(4, k);
    }

    private static long pickUp(int rest, int row, int col, long[][][] dp) {
        if (row < 0 || row >= dp[0].length || col < 0 || col >= dp[0][0].length) return 0;
        return dp[rest][row][col];
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 10000; i++) {
            int N = NumberOperation.getRandomNumber(5);
            int M = NumberOperation.getRandomNumber(5);
            int row = NumberOperation.getRandomNumber(N);
            int col = NumberOperation.getRandomNumber(M);
            int k = NumberOperation.getRandomNumber(5);

            double result_1 = livePosibility1(row, col, k, N, M);
            double result_2 = livePosibility2(row, col, k, N, M);

            if (result_1 != result_2) {
                System.out.println("result_1: " + result_1 + " result_2: " + result_2);
                System.out.println("N: " + N + " M: " + M + " row:" + row + " col:" + col + " k:" + k);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
