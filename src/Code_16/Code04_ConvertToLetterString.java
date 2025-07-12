package Code_16;

import tools.Constants;
import tools.NumberOperation;

public class Code04_ConvertToLetterString {

    private static int number(String str) {
        if (str == null || str.isEmpty()) return 0;
        char[] charArray = str.toCharArray();
        return process(charArray, 0);
    }

    // define the 0 - i-1 already converted, calculate the result
    private static int process(char[] charArray, int index) {
        if (index == charArray.length) {
            return 1;
        }

        if (charArray[index] == '0') {
            return 0;
        }

        int way = process(charArray, index + 1);

        if (index + 1 < charArray.length && charArray[index] - 'a' * 10 + charArray[index + 1] < 27) {
            way += process(charArray, index + 2);
        }

        return way;
    }

    private static int dp1(String str) {
        if (str == null || str.isEmpty()) return 0;
        char[] charArray = str.toCharArray();
        int N = charArray.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (charArray[i] != '0') {
                dp[i] = dp[i + 1];
                if (i + 1 < charArray.length && charArray[i] - 'a' * 10 + charArray[i + 1] < 27) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    //for test
    private static String getTheRandomNumberString(int size, int maxValue) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int num = NumberOperation.getRandomNumber(maxValue);
            if (num == 0 && NumberOperation.isRandomGreaterThanValue(0.3)) {
                num = NumberOperation.getRandomNumber(maxValue);
            }
            str.append(num);
        }
        return str.toString();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 100; i++) {
            String str = getTheRandomNumberString(10, 10);
            int number = number(str);
            int dp1 = dp1(str);

            if (dp1 != number) {
                System.out.println(str);
                System.out.println("number: " + number + " dp1: " + dp1);
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
