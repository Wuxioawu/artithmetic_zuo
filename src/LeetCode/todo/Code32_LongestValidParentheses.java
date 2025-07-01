package LeetCode.todo;

//todo: use the 单调栈
/**
 * <a href="https://leetcode.com/problems/longest-valid-parentheses/?envType=problem-list-v2&envId=stack">...</a>
 */
public class Code32_LongestValidParentheses {

    public static int longestValidParentheses(String s) {
        if (s.isEmpty()) return 0;
        char[] array = s.toCharArray();

        int maxCount = 0;
        int index = 0;
        while (index < array.length - 1) {
            int count = 0;
            while (index < array.length - 1 && array[index] == '(' && isParentheses(array[index], array[index + 1])) {
                count += 2;
                index += 2;
            }
            index++;
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    private static boolean isParentheses(char c1, char c2) {
        if (c1 == '(' && c2 == ')') return true;
        return false;
    }

    public static void main(String[] args) {
        String s = "()(()";
        int i = longestValidParentheses(s);
        System.out.println(i);
    }
}
