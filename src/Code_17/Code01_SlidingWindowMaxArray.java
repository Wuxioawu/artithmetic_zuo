package Code_17;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {

    private static int[] getMaxWindow1(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w == 0 || arr.length <= w) return new int[0];
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        int originalIndex = 0;
        while (index < result.length) {
            int max = Integer.MIN_VALUE;
            for (int i = originalIndex; i < originalIndex + w; i++) {
                max = Math.max(max, arr[i]);
            }
            result[index++] = max;
            originalIndex++;
        }
        return result;
    }

    private static int[] getMaxWindow2(int[] arr, int w) {
        if (arr == null || arr.length == 0 || w == 0 || arr.length <= w) return new int[0];
        int[] result = new int[arr.length - w + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);

            if (i - w == queue.peekFirst()) {
                queue.removeFirst();
            }
            if (i >= w - 1) {
                result[index++] = arr[queue.peekFirst()];
            }
        }
        return result;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 10000; i++) {
            int[] arr = ArrayOperation.getRandomArray(30, 100);
            int m = NumberOperation.getRandomNumber(10);

            int[] result_1 = getMaxWindow1(arr, m);
            int[] result_2 = getMaxWindow2(arr, m);

            if (!ArrayOperation.isEqualArrays(result_1, result_2)) {
                System.out.println("the result is: ");
                ArrayOperation.printArray(result_1);
                ArrayOperation.printArray(result_2);
                System.out.println("the original arr is: ");
                ArrayOperation.printArray(arr);
                System.out.println("m: " + m);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
