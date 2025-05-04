package Code_02;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * 4. In an array: one number appears exactly K times, while all others appear M times (M > 1, K < M).
 * Find and return the number appearing K times, using O(1) extra space and O(N) time.
 */
public class Code04_KM {
    public static int getKNumber(int[] arr, int k, int m) {
        if (ArrayOperation.isEmpty(arr)) return Constants.UN_INVALID;

        //  Store the binary representation of the sum of all elements in the array
        int[] bitInt = new int[32];

        for (int value : arr) {
            for (int j = 0; j < 32; j++) {
                if (((value >> j) & 1) != 0) {
                    bitInt[j]++;
                }
            }
        }

        int ans = 0;
        // bitInt: all number of representing m times and all number of representing K times
        for (int i = 0; i < bitInt.length; i++) {
            bitInt[i] = bitInt[i] % m;
            if (bitInt[i] != 0) {
                // todo: remember the operation
                ans |= (1 << i);
            }
        }

        return ans;
    }


    public static int getKNumberByMap(int[] arr, int k, int m) {
        if (ArrayOperation.isEmpty(arr)) return Constants.UN_INVALID;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int value : arr) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == k) {
                return entry.getKey();
            }
        }

        return 0;
    }

    // only one number presents k time, other numbers represents M times. M > K, M > 1
    public static int[] getRandomKMArray(int kindsAllNumbers, int timesK, int timesM) {

        int[] arr = new int[timesK + timesM * (kindsAllNumbers - 1)];
        int index;
        for (index = 0; index < timesM * (kindsAllNumbers - 1); ) {
            int numbersM = ArrayOperation.getRandomElementOutArray(arr, 10);
            for (int k = 0; k < timesM; k++) {
                arr[index++] = numbersM;
            }
        }

        int numbersK = ArrayOperation.getRandomElementOutArray(arr, 10);

        for (; index < arr.length; index++) {
            arr[index] = numbersK;
        }
        ArrayOperation.shuffleArray(arr);

        return arr;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {

            int kindsAllNumbers, timesK, timesM;
            do {
                kindsAllNumbers = NumberOperation.getRandomNumber(10);
                timesK = NumberOperation.getRandomNumber(10);
                timesM = NumberOperation.getRandomNumber(10);
            } while (kindsAllNumbers < 3 || timesM < 2 || timesK >= timesM);

            int[] randomKMArray = getRandomKMArray(kindsAllNumbers, timesK, timesM);

            int kNumberByBit = getKNumber(randomKMArray, timesK, timesM);
            int kNumberByMap = getKNumberByMap(randomKMArray, timesK, timesM);

            if (kNumberByBit != kNumberByMap) {
                System.out.println("kindsAllNumbers: " + kindsAllNumbers + " timesK: " + timesK + " timesM: " + timesM);
                ArrayOperation.printArray(randomKMArray);
                System.out.println(kNumberByBit);
                System.out.println(kNumberByMap);
                return;
            }
        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
