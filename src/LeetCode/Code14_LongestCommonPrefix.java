package LeetCode;


import java.util.Arrays;

public class Code14_LongestCommonPrefix {

    private static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);

        String start = strs[0];
        String end = strs[strs.length - 1];

        int index = 0;
        while (index < start.length() && index < end.length()) {
            if (start.charAt(index) == end.charAt(index)) {
                index++;
            } else {
                break;
            }
        }

        String longestString = start.length() > end.length() ? start : end;
        return longestString.substring(0, index);
    }

    public static void main(String[] args) {
        String[] strs = {"aaa", "aa", "aaa"};
        System.out.println(longestCommonPrefix(strs));
    }

}
