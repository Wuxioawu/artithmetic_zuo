package Code_15;

import tools.Constants;
import tools.NumberOperation;

import java.util.*;

public class Code02_PrintAllSubsequences {


    private static List<String> getTheSubsequencesByRecursion(String originalString) {
        ArrayList<String> result = new ArrayList<>();
        if (originalString == null || originalString.isEmpty()) return result;
        char[] charArray = originalString.toCharArray();
        process(charArray, 0, result, "");
        return result;
    }

    private static void process(char[] chars, int index, List<String> result, String path) {
        if (index >= chars.length) {
            result.add(path);
            return;
        }

        process(chars, index + 1, result, path);
        process(chars, index + 1, result, path + chars[index]);
    }

    private static List<String> getTheSubsequences(String originalString) {
        ArrayList<String> result = new ArrayList<>();
        int n = originalString.length();
        int total = 1 << n;

        for (int i = 0; i < total; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(originalString.charAt(j));
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    private static String getRandomString(int maxLength) {
        int randomLength = NumberOperation.getRandomNumber(maxLength);
        /*
          more than faster compared with StringBuffet, But it's not thread-safe.
         */

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < randomLength; i++) {
            int random = NumberOperation.getRandomNumber(26);
            stringBuilder.append((char) ('a' + random));
        }
        return stringBuilder.toString();
    }

    private static boolean checkEquality(List<String> list1, List<String> list2) {
        if (list1.isEmpty() && list2.isEmpty()) return true;
        if (list1.isEmpty() || list2.isEmpty()) return false;

        if (list1.size() != list2.size()) return false;

        for (int i = 0; i < list1.size(); i++) {
            /*
             * == compared the memory address
             * equals compared the content
             */
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {
            String random = getRandomString(5);

            List<String> theSubsequencesByRecursion = getTheSubsequencesByRecursion(random);
            List<String> theSubsequences = getTheSubsequences(random);

            Collections.sort(theSubsequences);
            Collections.sort(theSubsequencesByRecursion);

            if (!checkEquality(theSubsequences, theSubsequences)) {
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
