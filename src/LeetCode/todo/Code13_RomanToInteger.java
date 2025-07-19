package LeetCode.todo;

import java.util.HashMap;

// https://leetcode.com/problems/roman-to-integer/description/?envType=problem-list-v2&envId=string
public class Code13_RomanToInteger {

    public static int romanToInt(String s) {
        if (s.length() > 15 || s.isEmpty()) return -1;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int result = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            result += map.get(c);
            char next = s.charAt(i + 1);

            if (c == 'I' && (next == 'V' || next == 'X')) {
                result -= map.get(c) * 2;
            } else if (c == 'X' && (next == 'L' || next == 'C')) {
                result -= map.get(c) * 2;
            } else if (c == 'C' && (next == 'D' || next == 'M')) {
                result -= map.get(c) * 2;
            }
        }

        result += map.get(s.charAt(s.length() - 1));

        return result;
    }

    public static void main(String[] args) {
        int i = romanToInt("MCMXCIV");
        System.out.println(i);
    }
}
