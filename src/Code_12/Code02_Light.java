package Code_12;

import tools.Constants;
import tools.NumberOperation;

public class Code02_Light {

    private static int getTheMostLights(String input) {
        char[] charArray = input.toCharArray();
        int lights = 0;
        int index = 0;
        while (index < charArray.length) {
            if (charArray[index] == 'X') {
                index++;
            } else {
                lights++;
                if (index != charArray.length - 1) {
                    index += charArray[index + 1] == 'X' ? 2 : 3;
                } else {
                    index++;
                }
            }
        }
        return lights;
    }

    private static int getTheMostLightsByOther(String input) {
        char[] charArray = input.toCharArray();
        int lights = 0;
        int index = 0;

        while (index < charArray.length) {
            if (charArray[index] == 'X') {
                index++;
            } else {
                index++;
                int pointNum = 0;
                while (index < charArray.length && charArray[index] == '.') {
                    index++;
                    pointNum++;
                }
                lights += pointNum / 3 + 1;
            }
        }
        return lights;
    }


    // for test
    private static String getRandomInput(int maxSize) {
        int size = NumberOperation.getRandomNumber(maxSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(NumberOperation.isRandomGreaterThanValue(0.4d) ? "." : "X");
        }
        return sb.toString();
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            String input = getRandomInput(10);

            int theMostLights = getTheMostLights(input);
            int theMostLightsByBruteForce = getTheMostLightsByOther(input);

            if (theMostLights != theMostLightsByBruteForce) {
                System.out.println(Constants.CODE_ERROR);
                System.out.println(input);
                System.out.println("theMostLights: " + theMostLights + " theMostLightsByBruteForce:" + theMostLightsByBruteForce);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
