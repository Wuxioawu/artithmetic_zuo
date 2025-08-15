package Code_16;

import tools.Constants;
import tools.NumberOperation;

/**
 * 数组压缩技巧：（空间优化）
 * 依赖上面和左边
 * 依赖左，上，左上角
 * 比较行列，列比较短处理列，行比较短处理行
 */
public class Code10_MinPathSum {

    // first use the dp tablet to finish
    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }
        return process(m, 0, 0);
    }

    private static int process(int[][] m, int row, int col) {
        if (row > m.length - 1 || col > m[0].length - 1) return 0;
        if (row == m.length - 1 && col == m[0].length - 1) return m[row][col];
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        if (row < m.length - 1) {
            p1 = m[row][col] + process(m, row + 1, col);
        }
        if (col < m[0].length - 1) {
            p2 = m[row][col] + process(m, row, col + 1);
        }
        return Math.min(p1, p2);
    }

    private static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = m[row - 1][col - 1];
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = m[i][col - 1] + dp[i + 1][col - 1];
        }

        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = m[row - 1][i] + dp[row - 1][i + 1];
        }

        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = m[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    // use the compressed array to finish,
    private static int minPathSum3(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) return 0;
        int row = m.length;
        int col = m[0].length;

        int[] dp = new int[row];
        dp[row - 1] = m[row - 1][col - 1];

        for (int i = row - 2; i >= 0; i--) {
            dp[i] = m[i][col - 1] + dp[i + 1];
        }

        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1] += m[row - 1][i];
            for (int j = row - 2; j >= 0; j--) {
                dp[j] = m[j][i] + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }


    private static int[][] creatTablet(int value, int N, int M) {
        int[][] table = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                table[i][j] = NumberOperation.getRandomNumber(value);
            }
        }
        return table;
    }

    private static void printTablet(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int N = NumberOperation.getRandomNumber(10);
            int M = NumberOperation.getRandomNumber(10);
            int[][] tablets = creatTablet(10, N, M);
            int result_1 = minPathSum3(tablets);
            int result_2 = minPathSum2(tablets);
            if (result_1 != result_2) {
                printTablet(tablets);
                System.out.println("result_1" + result_1 + " result_2" + result_2);
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
