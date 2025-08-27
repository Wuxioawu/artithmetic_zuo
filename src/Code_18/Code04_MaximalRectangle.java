package Code_18;

import tools.Constants;
import tools.NumberOperation;

import java.util.Stack;

// as two largedt rectang
public class Code04_MaximalRectangle {

    // wrong
    private static int maximalRectangle1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] currentArray = new int[matrix[0].length];

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                currentArray[j] = matrix[i][j] == '0' ? 0 : currentArray[j] + 1;
            }

            // get array the Rectangle Area
            int currentMax = Integer.MIN_VALUE;
            int resultMax = Integer.MIN_VALUE;
            for (int currentIndex = 0; currentIndex < currentArray.length; currentIndex++) {
                if (currentArray[currentIndex] == 0) {
                    continue;
                }
                currentMax = currentArray[currentIndex];
                int currentMinValue = currentArray[currentIndex];

                for (int t = currentIndex + 1; t < currentArray.length; t++) {
                    if (currentArray[t] == 0) {
                        break;
                    }
                    if (currentArray[t] < currentMinValue) {
                        currentMinValue = currentArray[t];
                    }
                    resultMax = Math.max(resultMax, currentMinValue * (t - currentIndex + 1));
                }
                resultMax = Math.max(resultMax, currentMax);
            }

            maxArea = Math.max(maxArea, resultMax);
        }
        return maxArea == Integer.MIN_VALUE ? 0 : maxArea;
    }

    private static int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] sumArray = new int[matrix[0].length];
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sumArray[j] = matrix[i][j] == '0' ? 0 : sumArray[j] + 1;
            }
            // get the result
            Stack<Integer> stack = new Stack<>();
            for (int currentIndex = 0; currentIndex < sumArray.length; currentIndex++) {
                while (!stack.isEmpty() && sumArray[stack.peek()] > sumArray[currentIndex]) {
                    Integer pop = stack.pop();
                    int calculate = (currentIndex - 1 - (!stack.isEmpty() ? stack.peek() : -1)) * sumArray[pop];
                    maxArea = Math.max(Math.max(sumArray[currentIndex], calculate), maxArea);
                }
                stack.push(currentIndex);
            }

            while (!stack.isEmpty()) {
                int index = stack.pop();
                int calculate = Math.max(maxArea, sumArray[index] * (sumArray.length - 1 - (stack.isEmpty() ? -1 : stack.peek())));
                maxArea = Math.max(sumArray[index], calculate);
            }
        }
        return maxArea;
    }

    private static int maximalRectangle3(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] sumArray = new int[matrix[0].length];
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sumArray[j] = matrix[i][j] == '0' ? 0 : sumArray[j] + 1;
            }
            // get the result
            int[] stack = new int[sumArray.length];
            int stackIndex = -1;

            for (int currtentIndex = 0; currtentIndex < sumArray.length; currtentIndex++) {
                while (stackIndex != -1 && sumArray[stack[stackIndex]] > sumArray[currtentIndex]) {
                    int index = stack[stackIndex--];
                    int distance = currtentIndex - 1 - (stackIndex == -1 ? -1 : stack[stackIndex]);
                    maxArea = Math.max(Math.max(maxArea, sumArray[index] * distance), sumArray[currtentIndex]);
                }
                stack[++stackIndex] = currtentIndex;
            }
            while (stackIndex != -1) {
                int index = stack[stackIndex--];
                int distance = sumArray.length - 1 - (stackIndex == -1 ? -1 : stack[stackIndex]);
                maxArea = Math.max(Math.max(maxArea, sumArray[index] * distance), sumArray[index]);
            }
        }
        return maxArea;
    }

    // for test
    private static char[][] getRandomArray(int maxSize) {
        int N = NumberOperation.getRandomNumber(maxSize);
        int M = NumberOperation.getRandomNumber(maxSize);
        char[][] res = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j] = NumberOperation.isRandomGreaterThanValue(0.3) ? '1' : '0';
            }
        }
        return res;
    }

    private static void print(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {
            char[][] array = getRandomArray(10);
            int result_1 = maximalRectangle3(array);
            int result_2 = maximalRectangle2(array);
            if (result_1 != result_2) {
                System.out.println("result_1: " + result_1 + ", result_2: " + result_2);
                print(array);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
