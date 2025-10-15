package Code_21;

import java.util.Random;

public class Code01_Manacher {

    public static int manacher(String str) {
        char[] arr = dealWithString(str);
        int maxResult = 0;
        int N = arr.length;
        int[] P = new int[arr.length];
        int center = 0;
        int right = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < right) {
                int mirror = 2 * center - i;
                P[i] = Math.min(P[mirror], right - i);
            }
            while (i + P[i] + 1 < N && i - P[i] - 1 >= 0 && arr[i + P[i] + 1] == arr[i - P[i] - 1]) {
                P[i]++;
            }
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
            maxResult = Math.max(maxResult, P[i]);
        }
        return maxResult;
    }

    private static char[] dealWithString(String str) {
        if (str == null || str.isEmpty()) return new char[0];
        char[] chars = str.toCharArray();
        char[] newArr = new char[chars.length * 2 + 1];
        for (int i = 0, j = 0; i < newArr.length; i++) {
            newArr[i] = (i % 2 == 0) ? '#' : chars[j++];
        }
        return newArr;
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + random.nextInt(26)); // a-z
            sb.append(c);
        }
        return sb.toString();
    }

    private static void testOnce(int length) {
        String s = generateRandomString(length);
        int result = manacher(s);
        System.out.println("Input: " + s);
        System.out.println("Longest palindrome length: " + result);
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            int length = 5 + new Random().nextInt(10); // 字符串长度在 5~15 之间
            testOnce(length);
        }
    }
}
