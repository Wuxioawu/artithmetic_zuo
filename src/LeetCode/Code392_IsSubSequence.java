package LeetCode;

public class Code392_IsSubSequence {

    public static boolean isSubsequence(String s, String t) {
        int t_index = 0;
        int s_index = 0;
        while (s_index < s.length() && t_index < t.length()) {
            char s_char = s.charAt(s_index);
            char t_char = t.charAt(t_index);
            if (s_char == t_char) {
                s_index++;
            }
            t_index++;
        }
        return s_index == s.length();
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";

        boolean subsequence = isSubsequence(s, t);
        System.out.println(subsequence);


    }
}
