package Code_02;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.HashMap;
import java.util.Map;

public class Code01_FindOddTimes {
    public static int findOddTimes(int[] arr) {
        if (ArrayOperation.isEmpty(arr)) return Constants.UN_INVALID;

        int eor = arr[0];

        for (int i = 1; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;

    }

    /**
     * @param maxSize
     * @param maxValue
     * @return one number appear odd times, and other numbers appear even times.
     */
    public static int[] getArrayOddTime(int maxSize, int maxValue) {
        int[] arr = new int[NumberOperation.getRandomOddNumber(maxSize)];
        int oodTime = NumberOperation.getRandomOddNumber(10);
        int oddTimeNumber = NumberOperation.getRandomOddNumber(maxValue);
        int index = 0;
        while (index < arr.length && index < oodTime) {
            arr[index] = oddTimeNumber;
            index++;
        }
        while (index < arr.length) {
            int evenTime = NumberOperation.getRandomEvenNumber(10);
            int evenTimeNumber = NumberOperation.getRandomEvenNumber(maxValue);
            evenTime = index + evenTime;
            while (index < arr.length && index < evenTime) {
                arr[index] = evenTimeNumber;
                index++;
            }
        }

        ArrayOperation.shuffleArray(arr);

        return arr;
    }

    public static int comparator(int[] arr) {
        if (ArrayOperation.isEmpty(arr)) return Constants.UN_INVALID;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1)
                return entry.getKey();
        }

        return Constants.UN_INVALID;
    }

    //for test

    public static void test() {
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] arr = getArrayOddTime(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int resultOne = findOddTimes(arr);
            int resultTest = comparator(arr);
            if (resultOne != resultTest) {
                System.out.println(Constants.CODE_ERROR);
                ArrayOperation.printArray(arr);
                System.out.println("resultOne = " + resultOne + ", resultTest = " + resultTest);
                return;
            }
        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }


}
