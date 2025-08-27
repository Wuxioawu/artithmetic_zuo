package Code_19;

import tools.Constants;
import tools.NumberOperation;

public class Code03_TheCow {

    private static int cowNumber1(int n) {
        if (n == 0) return n;
        if (n == 1 || n == 2 || n == 3 || n == 4) return n;
        return cowNumber1(n - 1) + cowNumber1(n - 3);
    }

    private static int cowNumber2(int n) {
        if (n == 0) return n;
        if (n == 1 || n == 2 || n == 3 || n == 4) return n;
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] ints = matrixPower(base, n - 3);
        return 3 * ints[0][0] + 2 * ints[1][0] + ints[2][0];
    }

    private static int[][] matrixPower(int[][] arr, int p) {
        int[][] res = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = arr;
        while (p != 0) {
            if ((p & 1) != 0) {
                res = matrix(t, res);
            }
            t = matrix(t, t);
            p >>= 1;
        }
        return res;
    }

    private static int[][] matrix(int[][] arr1, int[][] arr2) {
        int n = arr1.length;
        int m = arr1[0].length;
        int k = arr2[0].length; // a的列数同时也是b的行数
        int[][] ans = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    ans[i][j] += arr1[i][c] * arr2[c][j];
                }
            }
        }
        return ans;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 1; i <= 10000; i++) {
            int n = NumberOperation.getRandomNumber(32);
            int result_1 = cowNumber1(n);
            int result_2 = cowNumber2(n);
            if (result_1 != result_2) {
                System.out.println("result = " + result_1 + " result_3 = " + result_2);
                System.out.println("n = " + n);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
