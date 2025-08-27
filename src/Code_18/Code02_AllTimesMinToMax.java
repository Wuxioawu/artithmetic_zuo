package Code_18;

import tools.ArrayOperation;
import tools.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code02_AllTimesMinToMax {

    private static int max1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int result = Integer.MIN_VALUE;

        //get the pixSum
        int[] sumArray = new int[arr.length];
        sumArray[0] = arr[0];
        for (int j = 1; j < sumArray.length; j++) {
            sumArray[j] = arr[j] + sumArray[j - 1];
        }

        for (int i = 0; i < arr.length; i++) {
            int currentResult = arr[i] * arr[i];
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                int subSum = sumArray[j] - (i > 0 ? sumArray[i - 1] : 0);
                min = Math.min(min, arr[j]);
                currentResult = Math.max(currentResult, subSum * min);
            }
            result = Math.max(result, currentResult);
        }
        return result;
    }

    private static int max2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        Stack<List<Integer>> stack = new Stack<>();
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().getLast()]) {
                List<Integer> popValue = stack.pop();
                int currentIndex = popValue.getFirst();
                int subSum = preSum[i - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek().getLast()]);

                result = Math.max(result, subSum * arr[currentIndex]);
            }
            if (stack.isEmpty() || arr[i] > arr[stack.peek().getLast()]) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(i);
                stack.push(temp);
            } else {
                stack.peek().addLast(i);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popValue = stack.pop();
            int currentIndex = popValue.getFirst();
            int subSum = preSum[preSum.length - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek().getLast()]);
            result = Math.max(result, subSum * arr[currentIndex]);
        }

        return result;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int[] arr = ArrayOperation.getRandomArray(10, 10);
            int result_1 = max1(arr);
            int result_2 = max2(arr);
            if (result_1 != result_2) {
                System.out.println("result_1: " + result_1 + ", result_2: " + result_2);
                ArrayOperation.printArray(arr);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
