package Code_16;

import tools.ArrayOperation;
import tools.Constants;

public class Code02_CardsInLine {
    private static int win1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    //当前轮到我自己拿牌，我最多能拿多少分
    private static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    // 当前轮到对手拿牌，我自己能得多少分，我自己作为后手
    private static int g1(int[] arr, int L, int R) {
        //对于先手来说，作为调用者什么也拿不到，这个是返回给先手的结果
        if (L == R) {
            return 0;
        }

        int p1 = f1(arr, L + 1, R);
        int p2 = f1(arr, L, R - 1);
        return Math.min(p1, p2);
    }

    private static int win2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int N = arr.length;
        int[][] fmdp = new int[N][N];
        int[][] gmdp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmdp[i][j] = -1;
                gmdp[i][j] = -1;
            }
        }

        int first = f2(arr, 0, N - 1, fmdp, gmdp);
        int second = g2(arr, 0, N - 1, fmdp, gmdp);
        return Math.max(first, second);
    }

    private static int f2(int[] arr, int L, int R, int[][] fmdp, int[][] gmdp) {
        if (fmdp[L][R] != -1)
            return fmdp[L][R];

        int ans;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmdp, gmdp);
            int p2 = arr[R] + g2(arr, L, R - 1, fmdp, gmdp);
            ans = Math.max(p1, p2);
        }

        fmdp[L][R] = ans;
        return ans;
    }

    private static int g2(int[] arr, int L, int R, int[][] fmdp, int[][] gmdp) {
        if (gmdp[L][R] != -1)
            return gmdp[L][R];

        int ans;
        if (L == R) {
            ans = 0;
        } else {
            int p1 = f2(arr, L + 1, R, fmdp, gmdp);
            int p2 = f2(arr, L, R - 1, fmdp, gmdp);
            ans = Math.min(p1, p2);
        }

        gmdp[L][R] = ans;
        return ans;
    }

    private static int win3(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;

        int[][] fmdp = new int[N][N];
        int[][] gmdp = new int[N][N];

        for (int i = 0; i < N; i++) {
            fmdp[i][i] = arr[i];
        }

        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                fmdp[L][R] = Math.max(arr[L] + gmdp[L + 1][R],
                        arr[R] + gmdp[L][R - 1]);
                gmdp[L][R] = Math.min(fmdp[L + 1][R], fmdp[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(fmdp[0][N - 1], gmdp[0][N - 1]);
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 1; i <= 10000; i++) {
            int[] arr = ArrayOperation.getRandomArray(10, 100);

            int result_1 = win1(arr);
            int result_2 = win2(arr);
            int result_3 = win3(arr);

            if (result_1 != result_2 || result_1 != result_3) {
                System.out.println("resul_1: " + result_1 + " result_2: " + result_2 + " result_3: " + result_3);
                ArrayOperation.printArray(arr);
                return;
            }
        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
