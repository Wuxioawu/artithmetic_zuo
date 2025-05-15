package Code_07;

import tools.Constants;
import tools.NumberOperation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {
    public static int maxCover1(int[][] lines) {
        if (lines == null || lines.length == 0) return 0;
        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int maxResult = Integer.MIN_VALUE;

        for (int[] line : lines) {
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= line[0]) {
                priorityQueue.poll();
            }

            priorityQueue.add(line[1]);
            maxResult = Math.max(maxResult, priorityQueue.size());

        }
        return maxResult;
    }

    public static int maxCoverByFor(int[][] lines) {
        if (lines == null || lines.length == 0) return 0;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int[] line : lines) {
            minValue = Math.min(minValue, line[0]);
            maxValue = Math.max(maxValue, line[1]);
        }

        int max_result = 0;
        while (minValue < maxValue) {
            int currentMax = 0;
            for (int[] line : lines) {
                float mid = minValue + 0.5f;
                if (line[0] < mid && line[1] > mid)
                    currentMax++;
            }
            minValue++;
            max_result = Math.max(max_result, currentMax);
        }
        return max_result;
    }

    public static int[][] createArray(int maxSize, int maxValue, int step) {
        int size = NumberOperation.getRandomNumber(maxSize);
        int[][] array = new int[size][2];

        for (int i = 0; i < size; i++) {
            array[i][0] = NumberOperation.getRandomNumber(maxValue);
            int rightIndex;
            do {
                rightIndex = array[i][0] + NumberOperation.getRandomNumber(step);
            } while (rightIndex == array[i][0]);
            array[i][1] = rightIndex;
        }
        return array;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[][] lines = createArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE, 100);
            int result1 = maxCover1(lines);
            int result2 = maxCoverByFor(lines);
            if (result1 != result2) {
                System.out.println("result1: " + result1 + " result2: " + result2);
                printArray(lines);
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void printArray(int[][] array) {
        for (int[] line : array) {
            for (int value : line) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        test();
    }
}
