package Code_16;

import tools.Constants;
import tools.NumberOperation;

import java.util.HashMap;

//https://leetcode.com/problems/stickers-to-spell-word
public class Code05_StickersToSpellWord {
    public static int minStickers1(String[] stickers, String target) {
        if (stickers == null || target == null || stickers.length == 0 || target.isEmpty()) return -1;
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * return: the rest
     * input:
     */
    private static int process1(String[] stickers, String target) {
        if (target.isEmpty()) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            String rest = minus(target, stickers[i]);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String target, String sticker) {
        char[] targetCharArray = target.toCharArray();
        char[] stickerCharArray = sticker.toCharArray();
        int[] statistic = new int[26];
        for (int i = 0; i < targetCharArray.length; i++) {
            statistic[targetCharArray[i] - 'a']++;
        }
        for (int i = 0; i < stickerCharArray.length; i++) {
            statistic[stickerCharArray[i] - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < statistic.length; i++) {
            if (statistic[i] > 0) {
                for (int j = 0; j < statistic[i]; j++) {
                    sb.append((char) ('a' + i));
                }
            }
        }
        return sb.toString();
    }

    private static int minStickers2(String[] stickers, String target) {
        if (stickers == null || target == null || stickers.length == 0 || target.isEmpty()) return -1;
        int[][] numberStickers = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] stickerArray = stickers[i].toCharArray();
            for (int j = 0; j < stickerArray.length; j++) {
                numberStickers[i][stickerArray[j] - 'a']++;
            }
        }
        int ans = process2(numberStickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] stickers, String target) {
        if (target.isEmpty()) return 0;

        int min = Integer.MAX_VALUE;

        char[] targetCharArray = target.toCharArray();
        int[] statisticTarget = new int[26];

        for (int i = 0; i < targetCharArray.length; i++) {
            statisticTarget[targetCharArray[i] - 'a']++;
        }

        for (int i = 0; i < stickers.length; i++) {

            int[] sticker = stickers[i];
            // the first stickers contain the target[0]
            if (sticker[targetCharArray[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (statisticTarget[j] > 0) {
                        int num = statisticTarget[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            sb.append((char) ('a' + j));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(process2(stickers, rest), min);
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static int minStickers3(String[] stickers, String target) {
        if (stickers == null || target == null || stickers.length == 0 || target.isEmpty()) return -1;
        int[][] numStickers = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            char[] stickerArray = stickers[i].toCharArray();
            for (int j = 0; j < stickerArray.length; j++) {
                numStickers[i][stickerArray[j] - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        int ans = process3(numStickers, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    private static int process3(int[][] stickers, String target, HashMap<String, Integer> dp) {
        if (target.isEmpty()) return 0;
        if (dp.containsKey(target)) return dp.get(target);

        int min = Integer.MAX_VALUE;

        char[] targetArray = target.toCharArray();
        int[] stickTarget = new int[26];

        for (int i = 0; i < targetArray.length; i++) {
            stickTarget[targetArray[i] - 'a']++;
        }

        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            if (sticker[targetArray[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    int num = stickTarget[j] - sticker[j];
                    for (int k = 0; k < num; k++) {
                        sb.append((char) ('a' + j));
                    }
                }
                String rest = sb.toString();
                min = Math.min(process3(stickers, rest, dp), min);
            }
        }

        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }


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
        for (int i = 0; i < 1; i++) {
            String[] stickers = getStringArray(10, 5, 15);
            String target = getRandomString(20, 15);

            System.out.println(target);
            System.out.println("--------------------------");
            for (String sticker : stickers) {
                System.out.println(sticker);
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        /**
         * Input: stickers = ["with","example","science"], target = "thehat"
         * Output: 3
         *
         * Input: stickers = ["notice","possible"], target = "basicbasic"
         * Output: -1
         */

        String[] stickers = {"with", "example", "science"};
        String target = "thehat";

        int i = minStickers3(stickers, target);
        System.out.println(i);

//      String minus = minus(target, stickers[1]);
//      System.out.println(minus);

    }


}
