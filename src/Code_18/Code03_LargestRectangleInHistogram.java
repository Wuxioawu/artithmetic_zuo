package Code_18;

import tools.ArrayOperation;
import tools.Constants;

import java.util.Stack;

public class Code03_LargestRectangleInHistogram {

    private static int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        if (heights.length == 1) return heights[0];
        int allMaxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] == 0) {
                continue;
            }
            int minArea = heights[i];
            allMaxArea = Math.max(allMaxArea, minArea);
            for (int j = i + 1; j < heights.length; j++) {
                if (heights[j] == 0) break;
                if (heights[j] < minArea) {
                    minArea = heights[j];
                }
                allMaxArea = Math.max(Math.max(minArea * (j - i + 1), allMaxArea), heights[j]);
            }
        }
        return allMaxArea == Integer.MIN_VALUE ? 0 : allMaxArea;
    }

    private static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int maxAllArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int index = stack.pop();
                int calculate = Math.max(maxAllArea, heights[index] * (i - 1 - (stack.isEmpty() ? -1 : stack.peek())));
                maxAllArea = Math.max(heights[i], calculate);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int calculate = Math.max(maxAllArea, heights[index] * (heights.length - 1 - (stack.isEmpty() ? -1 : stack.peek())));
            maxAllArea = Math.max(heights[index], calculate);
        }
        return maxAllArea;
    }

    // use the array to make stack
    private static int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int allMaxArea = Integer.MIN_VALUE;
        int[] stack = new int[heights.length];
        int stackIndex = -1;

        for (int i = 0; i < heights.length; i++) {
            while (stackIndex != -1 && heights[stack[stackIndex]] > heights[i]) {
                int index = stack[stackIndex--];
                int distance = i - 1 - (stackIndex == -1 ? -1 : stack[stackIndex]);
                allMaxArea = Math.max(Math.max(allMaxArea, heights[index] * distance), heights[i]);
            }
            stack[++stackIndex] = i;
        }
        while (stackIndex != -1) {
            int index = stack[stackIndex--];
            int distance = heights.length - 1 - (stackIndex == -1 ? -1 : stack[stackIndex]);
            allMaxArea = Math.max(Math.max(allMaxArea, heights[index] * distance), heights[index]);
        }
        return allMaxArea;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 100000; i++) {
            int[] arr =
            ArrayOperation.getRandomArray(10, 10);
            int result_1 = largestRectangleArea2(arr);
            int result_2 = largestRectangleArea3(arr);
            if (result_1 != result_2) {
                System.out.println("result_1 = " + result_1 + ", result_2 = " + result_2);
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
