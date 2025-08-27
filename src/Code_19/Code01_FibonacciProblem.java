package Code_19;

import tools.Constants;
import tools.NumberOperation;

//Cassini’s Identity
public class Code01_FibonacciProblem {

    private static int fibonacci1(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    private static int fibonacci2(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) {
            return 1;
        }
        int last_1 = 1, last_2 = 1;
        int current = 0;
        for (int i = 3; i <= n; i++) {
            current = last_1 + last_2;
            last_1 = last_2;
            last_2 = current;
        }
        return current;
    }

    // use the O(logN)
    private static int fibonacci3(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[][] base = {{1, 1}, {1, 0}};
        int[][] rest = matrixPower(base, n - 2);
        return rest[0][0] + rest[1][0];
    }

    private static int[][] matrixPower(int[][] m, int p) {
        if (m.length == 0 || m[0].length == 0) return new int[0][0];
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        while (p != 0) {
            if ((p & 1) != 0) {
                res = matrixMulti(res, t);
            }
            t = matrixMulti(t, t);
            p >>= 1;
        }
        return res;
    }

    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length; // a的列数同时也是b的行数
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    ans[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return ans;
    }

    private static int[][] matrixMulti(int[][] arr1, int[][] arr2) {
        int[][] result = new int[arr1.length][arr2.length];
        result[0][0] = arr1[0][0] * arr2[0][0] + arr1[0][1] * arr2[1][0];
        result[0][1] = arr1[0][0] * arr2[0][1] + arr1[0][1] * arr2[1][1];
        result[1][0] = arr1[1][0] * arr2[0][0] + arr1[1][1] * arr2[1][0];
        result[1][1] = arr1[1][0] * arr2[0][1] + arr1[1][1] * arr2[1][1];
        return result;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 1; i <= 10000; i++) {
            int n = NumberOperation.getRandomNumber(32);
            int result_1 = fibonacci1(n);
            int result_2 = fibonacci3(n);
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
