package Code_16;

//范围尝试模型特别在意讨论开头和结尾如何
// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
public class Code07_PalindromeSubsequence {

    // the longest sub-string
    public static int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) return 0;
        String original = new String(s);
        StringBuilder builder = new StringBuilder(s);
        String revers = builder.reverse().toString();
        return getLongestDp(original.toCharArray(), revers.toCharArray());
    }

    private static int getLongeset(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) return 0;
        char[] charArray_1 = s1.toCharArray();
        char[] charArray_2 = s2.toCharArray();
        return process(charArray_1, charArray_2, charArray_1.length - 1, charArray_2.length - 1);
    }

    private static int process(char[] s1, char[] s2, int last_1, int last_2) {
        if (last_1 == 0 && last_2 == 0) {
            return s1[last_1] == s2[last_2] ? 1 : 0;
        } else if (last_1 == 0) {
            return s1[last_1] == s2[last_2] ? 1 : process(s1, s2, last_1, last_2 - 1);
        } else if (last_2 == 0) {
            return s1[last_1] == s2[last_2] ? 1 : process(s1, s2, last_1 - 1, last_2);
        } else {
            int p1 = process(s1, s2, last_1, last_2 - 1);
            int p2 = process(s1, s2, last_1 - 1, last_2);
            int p3 = (s1[last_1] == s2[last_2] ? 1 : 0) + process(s1, s2, last_1 - 1, last_2 - 1);
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    private static int getLongestDp(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length][s2.length];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;

        for (int i = 1; i < s1.length; i++) {
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < s2.length; i++) {
            dp[0][i] = s1[0] == s2[i] ? 1 : dp[0][i - 1];
        }

        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                dp[i][j] = Math.max((s1[i] == s2[j] ? 1 : 0) + dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        return dp[s1.length - 1][s2.length - 1];
    }

    //the reveriuals
    public static int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) return 0;
        return process2(s.toCharArray(), 0, s.length() - 1);
    }

    private static int process2(char[] array, int left, int right) {
        if (left == right) return 1;
        if (left + 1 == right) return array[left] == array[right] ? 2 : 1;
        int p1 = process2(array, left + 1, right);
        int p2 = process2(array, left, right - 1);
        int p3 = (array[left] == array[right] ? 2 : 0) + process2(array, left + 1, right - 1);
        return Math.max(p1, Math.max(p2, p3));
    }

    public static int longestPalindromeSubseq3(String s) {
        if (s == null || s.isEmpty()) return 0;
        int N = s.length();

        char[] array = s.toCharArray();
        int[][] dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
            if (i + 1 < N) {
                dp[i][i + 1] = array[i] == array[i + 1] ? 2 : 1;
            }
        }

        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                dp[L][R] = Math.max(dp[L + 1][R], dp[L][R - 1]);
                int p3 = dp[L + 1][R - 1];
                if (array[L] == array[R]) {
                    p3 += 2;
                }
                dp[L][R] = Math.max(dp[L][R], p3);
            }
        }
        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        String s1 = "bbbab";
        int i = longestPalindromeSubseq3(s1);
        System.out.println(i);
    }


}
