package Code_02;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * In an array, exactly two numbers appear odd times, all others appear even times.
 */
public class Code02_FindOddTwoNumber {

    public static int[] findOddTwoNumber(int[] arr) {
        if (ArrayOperation.isEmpty(arr)) return arr;

        int[] result = new int[2];

        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int rightOne = eor & (~eor + 1);

        int result_0 = 0;

        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                result_0 ^= arr[i];
            }
        }

        result[0] = result_0;
        result[1] = eor ^ result_0;
        return result;
    }

    public static int[] comparator(int[] arr) {
        if (ArrayOperation.isEmpty(arr)) return arr;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            if (!map.containsKey(j)) {
                map.put(j, 1);
            } else map.put(j, map.get(j) + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                list.add(entry.getKey());
            }
        }


        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void test() {

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] arrayOddTwoTime = getArrayOddTwoTime(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);

            int[] oddTwoNumber = findOddTwoNumber(arrayOddTwoTime);
            int[] comparator = comparator(arrayOddTwoTime);

            if(oddTwoNumber.length != comparator.length) {
                System.out.println("oddTwoNUmber.length :" + oddTwoNumber.length + "   comparator.length: " + comparator.length);
                System.out.println("oddTwoNumber.length != comparator.length");
                ArrayOperation.printArray(arrayOddTwoTime);
                ArrayOperation.printArray(comparator);
                ArrayOperation.printArray(oddTwoNumber);

                return;
            }

            if (oddTwoNumber.length != 2) {
                System.out.println("array.length != 2");
            }

            ArrayOperation.sort(oddTwoNumber);
            ArrayOperation.sort(comparator);

            if(oddTwoNumber[0] != comparator[0] || oddTwoNumber[1] != comparator[1]) {
                ArrayOperation.printArray(arrayOddTwoTime);
                ArrayOperation.printArray(comparator);
                ArrayOperation.printArray(oddTwoNumber);
                System.out.println(Constants.CODE_ERROR);
            }

        }
        System.out.println(Constants.NICE);
    }

    public static int[] getArrayOddTwoTime(int maxSize, int maxValue) {
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

        int randomElementOutArray = ArrayOperation.getRandomElementOutArray(arr, maxValue);

        int otherOddTime = NumberOperation.getRandomOddNumber(10);

        int[] newArr = new int[arr.length + otherOddTime];
        index = 0;

        for (index = 0; index < arr.length; index++) {
            newArr[index] = arr[index];
        }

        while (index < newArr.length) {
            newArr[index] = randomElementOutArray;
            index++;
        }

        ArrayOperation.shuffleArray(newArr);

        return newArr;
    }



    public static void main(String[] args) {
        test();
    }

}
