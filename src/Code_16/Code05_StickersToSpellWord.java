package Code_16;

import tools.Constants;
import tools.NumberOperation;

public class Code05_StickersToSpellWord {



    private static String[] getStringArray(int maxSize, int oneElementLength, int letterScan) {
        int N = NumberOperation.getRandomNumber(maxSize);
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = getRandomString(oneElementLength, letterScan);
        }
        return str;
    }

    private static String getRandomString(int maxSize, int letterScan) {
        StringBuilder sb = new StringBuilder();
        int size = NumberOperation.getRandomNumber(maxSize);
        for (int i = 0; i < size; i++) {
            char a = (char) (NumberOperation.getRandomNumber(letterScan) + 'a');
            sb.append(a);
        }
        return sb.toString();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 10; i++) {
            String[] stickers = getStringArray(10, 5, 15);
            String target = getRandomString(20, 15);

        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
