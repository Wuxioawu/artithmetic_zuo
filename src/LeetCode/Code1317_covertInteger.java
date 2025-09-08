package LeetCode;


public class Code1317_covertInteger {
    public static int[] getNoZeroIntegers(int n) {
        for (int i = 1; i < n; i++) {
            int j = n - i;
            if(!checkContainZero(j) && !checkContainZero(i)) {
                return new int[]{i, j};
            }
        }
        return new int[]{0, 0};
    }

    private static boolean checkContainZero(int n) {
        while (n != 0) {
            if (n % 10 == 0) {
                return true;
            } else {
                n /= 10;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] noZeroIntegers = getNoZeroIntegers(1010);
        for (int i = 0; i < noZeroIntegers.length; i++) {
            System.out.print(noZeroIntegers[i] + " ");
        }
        System.out.println();
    }
}
