package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Code3 {

    public int lengthOfLongestSubstring2(String s) {
        int left = 0;
        int maxLength = -1;
        String subString = "";

        for (int i = 1; i <= s.length(); i++) {
            while (subString.contains("" + s.charAt(i - 1))) {
                maxLength = Math.max(maxLength, subString.length());
                subString = s.substring(++left, i - 1);
            }
            subString = s.substring(left, i);
        }
        return maxLength == -1 ? s.length() : Math.max(maxLength, subString.length());
    }

    public int lengthOfLongestSubstring1(String s) {
        if (s.length() == 1) return 1;
        int left = 0;
        int maxLength = -1;
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            while (set.contains(s.charAt(i))) {
                maxLength = Math.max(maxLength, set.size());
                set.remove(s.charAt(left++));
            }
            set.add(s.charAt(i));
        }
        return maxLength == -1 ? s.length() : Math.max(maxLength, set.size());
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) return 1;
        int left = 0;
        int maxLength = -1;
        int[] exist = new int[128];
        Arrays.fill(exist, -1);
        int size = 0;

        for (int i = 0; i < s.length(); i++) {
            while (exist[s.charAt(i)] != -1) {
                maxLength = Math.max(maxLength, i - left);
                exist[s.charAt(left++)]--;
                size--;
            }
            exist[s.charAt(i)]++;
            size++;
        }
        return maxLength == -1 ? s.length() : Math.max(maxLength, size);
    }

    public static void main(String[] args) {
        String s = "aab";
        int i1 = lengthOfLongestSubstring(s);
        System.out.println(i1);
    }
}
