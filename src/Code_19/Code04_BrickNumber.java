package Code_19;

import tools.Constants;
import tools.NumberOperation;

public class Code04_BrickNumber {

    private static int brickNumber1(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return n;
        return brickNumber1(n - 1) + brickNumber1(n - 2);
    }

    private static int brickNumber2(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return n;
        int[][] base = {{1, 1}, {1, 0}};
        int[][] ints = matrixPower(base, n - 2);
        return 2 * ints[0][0] + ints[1][0];
    }

    private static int[][] matrixPower(int[][] arr, int p) {
        int[][] res = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            res[i][i] = 1;
        }

        int[][] t = arr;
        while (p != 0) {
            if ((p & 1) != 0) {
                res = matrixMultiply(res, t);
            }
            t = matrixMultiply(t, t);
            p >>= 1;
        }
        return res;
    }

    private static int[][] matrixMultiply(int[][] arr1, int[][] arr2) {
        int n = arr1.length;
        int m = arr1[0].length;
        int k = arr2[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < k; l++) {
                    res[i][j] += arr1[i][l] * arr2[l][j];
                }
            }
        }
        return res;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 1; i <= 10000; i++) {
            int n = NumberOperation.getRandomNumber(32);
            int result_1 = brickNumber1(n);
            int result_2 = brickNumber2(n);
            if (result_1 != result_2) {
                System.out.println("result_1 = " + result_1 + " result_2 = " + result_2);
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
