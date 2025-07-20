package LeetCode;

public class Code125_ValidPalindrome {

    public static boolean isPalindrome(String s) {
        char[] charArray = removeNoChar(s).toCharArray();

        int start = 0, end = charArray.length - 1;

        while (start <= end) {
            if (charArray[start++] != charArray[end--]) {
                return false;
            }
        }
        return true;
    }

    private static String removeNoChar(String s) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = charArray[i];
            if (currentChar <= 'Z' && currentChar >= 'A') {
                sb.append(Character.toLowerCase(currentChar));
            } else if (currentChar <= 'z' && currentChar >= 'a') {
                sb.append(currentChar);
            } else if (currentChar >= '0' && currentChar <= '9') {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }

    // other methods
    private static boolean isPalindrome2(String s) {
        int start = 0, end = s.length() - 1;

        while (start <= end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);

            if (Character.isLetterOrDigit(startChar) && Character.isLetterOrDigit(endChar)) {
                if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                    return false;
                }
                start++;
                end--;
                continue;
            }

            if (!Character.isLetterOrDigit(startChar)) {
                start++;
            }

            if (!Character.isLetterOrDigit(endChar)) {
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome2(s));
    }
}
