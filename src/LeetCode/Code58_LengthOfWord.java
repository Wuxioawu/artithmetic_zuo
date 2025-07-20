package LeetCode;

public class Code58_LengthOfWord {

    public static int lengthOfLastWord(String s) {
        int lastWordLength = 0;
        char[] charArray = s.toCharArray();

        for (int i = charArray.length - 1; i >= 0; i--) {
            char currentChar = charArray[i];

            while (currentChar >= 'A' && currentChar <= 'Z' || currentChar >= 'a' && currentChar <= 'z') {
                lastWordLength++;
                if (i == 0) break;
                currentChar = charArray[--i];
            }

            if (lastWordLength != 0) break;
        }

        return lastWordLength;
    }

    public static void main(String[] args) {
        String s = " a";
        int i = lengthOfLastWord(s);
        System.out.println(i);
    }
}
