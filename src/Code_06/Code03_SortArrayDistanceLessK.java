package Code_06;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.PriorityQueue;

/**
 *
 */
public class Code03_SortArrayDistanceLessK {

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (!ArrayOperation.isSortable(arr)) return;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int index = 0;

        while (index < Math.min(arr.length, k)) {
            priorityQueue.add(arr[index++]);
        }

        int leftIndex = 0;
        while (!priorityQueue.isEmpty() && index < arr.length) {
            Integer poll = priorityQueue.poll();
            arr[leftIndex++] = poll;
            priorityQueue.add(arr[index++]);
        }

        while (!priorityQueue.isEmpty()) {
            arr[leftIndex++] = priorityQueue.poll();
        }
    }

    public static void getArray(int[] sortedArray, int k) {
        if (ArrayOperation.isEmpty(sortedArray) || k < 1) return;
        int index = 0;
        while (index < sortedArray.length) {
            int lastIndex = Math.min(index + k, sortedArray.length);
            ArrayOperation.shuffleArray(sortedArray, index, lastIndex - 1);
            index += k;
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int k = NumberOperation.getRandomNumber(10);
            ArrayOperation.sort(array);
            getArray(array, k);


            int[] copyArray = ArrayOperation.copyArray(array);
            int[] originalArray = ArrayOperation.copyArray(array);

            sortedArrDistanceLessK(array, k);
            ArrayOperation.sort(copyArray);

            if (!ArrayOperation.isEqualArrays(array, copyArray)) {
                ArrayOperation.printArray(array);
                ArrayOperation.printArray(copyArray);

                ArrayOperation.printArray(originalArray);
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
