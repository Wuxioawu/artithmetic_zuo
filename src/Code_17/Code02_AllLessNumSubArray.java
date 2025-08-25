package Code_17;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

    private static int num_1(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) return 0;
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                if (max - min <= sum) {
                    result++;
                }
            }
        }
        return result;
    }

    private static int num_2(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) return 0;

        LinkedList<Integer> minQueue = new LinkedList<>();
        LinkedList<Integer> maxQueue = new LinkedList<>();

        int result = 0;
        int R = 0;
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length) {
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[R]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[R]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(R);
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > sum) {
                    break;
                } else R++;
            }

            result += R - L;

            if (minQueue.peekFirst() == L) {
                minQueue.pollFirst();
            }
            if (maxQueue.peekFirst() == L) {
                maxQueue.pollFirst();
            }
        }
        return result - arr.length;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayOperation.getRandomArray(20, 100);
            int m = NumberOperation.getRandomNumber(20);
            int result_1 = num_1(arr, m);
            int result_2 = num_2(arr, m);
            if (result_1 != result_2) {
                System.out.println("result_1 = " + result_1 + ", result_2 = " + result_2);
                ArrayOperation.printArray(arr);
                System.out.println("m = " + m);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
