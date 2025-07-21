package LeetCode;

public class Code383_RansomNote {

    public static boolean canConstruct(String ransomNote, String magazine) {
        int ran_index = 0;
        while (ran_index < ransomNote.length()) {
            String currentChar = String.valueOf(ransomNote.charAt(ran_index));
            if (magazine.contains(currentChar)) {
                magazine = magazine.replaceFirst(currentChar, "");
                ran_index++;
            } else {
                return false;
            }
        }
        return ran_index == ransomNote.length();
    }

    private static boolean canConstruct2(String ransomNote, String magazine) {
        int[] count = new int[26];

        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            count[ransomNote.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
    }
}
