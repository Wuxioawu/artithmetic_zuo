package LeetCode.todo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Code2273_FindResult {
    public static void main(String[] args) {
        String[] words = new String[]{"a", "az"};
        List<String> result = removeAnagrams(words);
        System.out.println(result);

    }

    public static List<String> removeAnagrams(String[] words) {
        List<String> res = new LinkedList<>(Arrays.asList(words));
        int index = 1;
        while (!res.isEmpty() && index < res.size()) {
            if (isAnagram(res.get(index - 1),  res.get(index))) {
                res.remove(index);
            } else {
                index++;
            }
        }
        return res;
    }

    private static boolean isAnagram(String word1, String word2) {
        int[] arr = new int[26];

        String maxLength = word1.length() > word2.length() ? word1 : word2;
        String minLength = maxLength.equals(word1) ? word2 : word1;

        for (int i = 0; i < minLength.length(); i++) {
            int index = minLength.charAt(i) - 'a';
            arr[index]++;
        }

        for (int i = 0; i < maxLength.length(); i++) {
            int index = maxLength.charAt(i) - 'a';
            arr[index]--;
            if (arr[index] < 0) return false;
        }
        return true;
    }
}
