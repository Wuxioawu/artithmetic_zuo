package LeetCode;

import java.util.Stack;

/**
 * <a href="https://leetcode.com/problems/valid-parentheses/description/?envType=problem-list-v2&envId=stack">...</a>
 */
public class Code20_ValidParentheses {

    public static boolean isValid(String s) {
        if (s.isEmpty()) return true;
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(' || arr[i] == '{' || arr[i] == '[') {
                stack.push(arr[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!check(stack.pop(), arr[i])) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean check(Character c1, Character c2) {
        if (c1 == '(' && c2 == ')') return true;
        if (c1 == '{' && c2 == '}') return true;
        if (c1 == '[' && c2 == ']') return true;
        return false;
    }

    public static boolean isValid2(String s) {
        if (s.isEmpty()) return true;
        char[] array = s.toCharArray();
        char[] stack = new char[array.length];
        int top = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(' || array[i] == '{' || array[i] == '[') {
                stack[++top] = array[i];
            } else {
                if (top == -1) return false;
                char c = stack[top--];
                if (!check(c, array[i])) return false;
            }
        }

        return top == -1;
    }

    public static void main(String[] args) {
        String s = "[";
        System.out.println(isValid(s));
    }
}
