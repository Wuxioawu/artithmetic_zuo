package Code_12;

import tools.Constants;
import tools.NumberOperation;

import java.util.*;

public class Code01_LowestLexicography {

    public static String lowestLexicography(String[] texts) {
        if (texts == null || texts.length == 0) return "";

        String[] copyArray = texts.clone();
        Arrays.sort(copyArray, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder result = new StringBuilder();
        for (String s : copyArray) {
            result.append(s);
        }
        return result.toString();
    }

    // by brute force
    public static String lowestLexicography2(String[] texts) {
        if (texts == null || texts.length == 0) return "";
        return process(texts).getFirst();
    }

    private static TreeSet<String> process(String[] texts) {
        TreeSet<String> result = new TreeSet<>();
        if (texts == null || texts.length == 0) {
            result.add("");
            return result;
        }
        for (int i = 0; i < texts.length; i++) {
            String value = texts[i];
            TreeSet<String> process = process(removeValueByIndex(texts, i));
            for (String s : process) {
                result.add(value + s);
            }
        }
        return result;
    }

    private static String[] removeValueByIndex(String[] texts, int index) {
        if (texts == null || texts.length == 0) return new String[0];
        ArrayList<String> stringsList = new ArrayList<>();

        for (int i = 0; i < texts.length; i++) {
            if (i != index) {
                stringsList.add(texts[i]);
            }
        }
        return stringsList.toArray(new String[0]);
    }

    // for test

    private static void printStringArray(String[] strings) {
        System.out.print("[");
        for (int i = 0; i < strings.length; i++) {
            System.out.print(strings[i]);
            if (i != strings.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
    }

    public static String[] getRandomStringArray(int length, int maxSizeOneString) {
        int size = NumberOperation.getRandomNumber(length);
        String[] stringArray = new String[size];
        for (int i = 0; i < size; i++) {
            stringArray[i] = getRandomString(maxSizeOneString);
        }
        return stringArray;
    }

    public static String getRandomString(int maxSize) {
        int size;
        do {
            size = NumberOperation.getRandomNumber(maxSize);
        } while (size == 0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            boolean isUpper = new Random().nextBoolean();
            char letter = (char) (isUpper ? 'A' : 'a' + NumberOperation.getRandomNumber(26));
            stringBuilder.append(letter);
        }
        return stringBuilder.toString();
    }

    private static boolean isSame(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;

        if (s1.length() != s2.length()) return false;

        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();

        for (int i = 0; i < s1Chars.length; i++) {
            if (s1Chars[i] != s2Chars[i]) return false;
        }
        return true;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            String[] stringArray = getRandomStringArray(10, 5);

            String s1 = lowestLexicography(stringArray);
            String s2 = lowestLexicography2(stringArray);
            System.out.println("the test time is :" + i);

            if (!isSame(s1, s2)) {
                printStringArray(stringArray);
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time is " + (endTime - startTime));
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
