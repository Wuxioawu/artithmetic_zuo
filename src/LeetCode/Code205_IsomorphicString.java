package LeetCode;

import java.util.HashMap;

public class Code205_IsomorphicString {
    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> s_to_t_map = new HashMap<>();
        HashMap<Character, Character> t_to_s_map = new HashMap<>();
        int s_index = 0;
        int t_index = 0;
        while (s_index < s.length() && t_index < t.length()) {
            char s_char = s.charAt(s_index);
            char t_char = t.charAt(t_index);
            if (!s_to_t_map.containsKey(s_char) && !t_to_s_map.containsKey(t_char)) {
                s_to_t_map.put(s_char, t_char);
                t_to_s_map.put(t_char, s_char);
            }
            s_index++;
            t_index++;
        }
        s_index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (s_index < s.length()) {
            char s_char = s.charAt(s_index++);
            stringBuilder.append(s_to_t_map.get(s_char));
        }

        return stringBuilder.toString().equals(t);
    }

    private static boolean isIsomorphic2(String s, String t) {
        int[][] s_to_t_map = new int[256][256];
        int[][] t_to_s_map = new int[256][256];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                s_to_t_map[i][j] = -1;
                t_to_s_map[i][j] = -1;
            }
        }

        int s_index = 0;
        int t_index = 0;
        while (s_index < s.length() && t_index < t.length()) {
            char s_char = s.charAt(s_index);
            char t_char = t.charAt(t_index);
            if (s_to_t_map[s_char][t_char] == -1 && t_to_s_map[t_char][s_char] == -1) {
                s_to_t_map[s_char][t_char] = 1;
                t_to_s_map[t_char][s_char] = 1;
            }
        }

        s_index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (s_index < s.length()) {
            char s_char = s.charAt(s_index++);
            char the = findThe(s_to_t_map[s_char]);
            stringBuilder.append(the);
        }
        return stringBuilder.toString().equals(t);
    }

    private static char findThe(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                return (char) i;
            }
        }
        return '\0';
    }


    public static void main(String[] args) {
        String s = "badc";
        String t = "baba";

        boolean isomorphic = isIsomorphic(s, t);
        System.out.println(isomorphic);
    }
}
