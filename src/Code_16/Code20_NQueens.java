package Code_16;

import tools.Constants;
import tools.NumberOperation;

public class Code20_NQueens {

    private static int num1(int N) {
        if (N == 0 || N == 1) return N;
        int[] record = new int[N];
        return process1(0, record);
    }

    // return the ways
    private static int process1(int index, int[] record) {
        if (index == record.length) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < record.length; j++) {
            if (isValid(record, index, j)) {
                record[index] = j;
                res += process1(index + 1, record);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    private static int num2(int N) {
        if (N == 0 || N == 1) return N;

        // get the number which the from right to left calculate N bits
        int limit = N == 32 ? -1 : (1 << N) - 1;

        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int colLimit, int leftDial, int rightDial) {
        // if the colLimit equal the limit, which means find a useful ways
        if (colLimit == limit) return 1;
        // the bit is 1 can put the queens
        int position = limit & ~(colLimit | leftDial | rightDial);

        int mostRightOne = 0;
        int res = 0;
        while (position != 0) {
            mostRightOne = position & (~position + 1);
            position -= mostRightOne;
            res += process2(limit, colLimit | mostRightOne,
                    (leftDial | mostRightOne) << 1,
                    (rightDial | mostRightOne) >>> 1);
        }
        return res;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int N = NumberOperation.getRandomNumber(10);

            int rseult_1 = num1(N);
            int rseult_2 = num2(N);
            if (rseult_1 != rseult_2) {
                System.out.println("result_1: " + rseult_1 + " result_2: " + rseult_2);
                System.out.println("N: " + N);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
