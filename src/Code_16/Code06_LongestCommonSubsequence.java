package Code_16;

// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code06_LongestCommonSubsequence {

    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        return process(text1.toCharArray(), text2.toCharArray(), text1.length() - 1, text2.length() - 1);
    }

    private static int process(char[] text1, char[] text2, int last_1, int last_2) {
        if (last_1 == 0 && last_2 == 0) {
            return text1[last_1] == text2[last_2] ? 1 : 0;
        } else if (last_1 == 0) {
            return text1[last_1] == text2[last_2] ? 1 : process(text1, text2, last_1, last_2 - 1);
        } else if (last_2 == 0) {
            return text1[last_1] == text2[last_2] ? 1 : process(text1, text2, last_1 - 1, last_2);
        } else {
            int p1 = process(text1, text2, last_1, last_2 - 1);
            int p2 = process(text1, text2, last_1 - 1, last_2);
            int p3 = (text1[last_1] == text2[last_2] ? 1 : 0) + process(text1, text2, last_1 - 1, last_2 - 1);
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    private static int longestCommonSubsequence2(String text1, String text2) {
        if (text1.isEmpty() || text2.isEmpty()) {
            return 0;
        }
        int[][] dp = new int[text1.length()][text2.length()];

        char[] charArray_1 = text1.toCharArray();
        char[] charArray_2 = text2.toCharArray();

        dp[0][0] = charArray_1[0] == charArray_2[0] ? 1 : 0;

        for (int j = 1; j < text1.length(); j++) {
            dp[j][0] = charArray_1[j] == charArray_2[0] ? 1 : dp[j - 1][0];
        }

        for (int i = 1; i < text2.length(); i++) {
            dp[0][i] = charArray_2[i] == charArray_1[0] ? 1 : dp[0][i - 1];
        }

        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                dp[i][j] = Math.max((charArray_1[i] == charArray_2[j] ? 1 : 0) + dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }


    public static void main(String[] args) {
        /**
         * Input: text1 = "abc", text2 = "def"
         * Output: 3
         */
        String text1 = "abc";
        String text2 = "def";

        int i = longestCommonSubsequence2(text1, text2);
        System.out.println(i);
    }
}
