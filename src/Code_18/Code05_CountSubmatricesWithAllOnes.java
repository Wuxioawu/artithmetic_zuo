package Code_18;

import tools.Constants;
import tools.NumberOperation;

import java.util.Stack;

// get the number of sub rectangles
public class Code05_CountSubmatricesWithAllOnes {

    // use the system stack
    private static int numSubmat1(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        int[] helpArray = new int[mat[0].length];
        int sumAllRectang = 0;
        for (int[] its : mat) {
            // get the mat[i] array
            for (int j = 0; j < its.length; j++) {
                helpArray[j] = its[j] == 0 ? 0 : its[j] + helpArray[j];
            }

            Stack<Integer> stack = new Stack<>();
            for (int k = 0; k < helpArray.length; k++) {
                while (!stack.isEmpty() && helpArray[k] <= helpArray[stack.peek()]) {
                    Integer pop = stack.pop();
                    int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                    int height = helpArray[pop] - Math.max(leftIndex != -1 ? helpArray[leftIndex] : 0, helpArray[k]);
                    int width = k - leftIndex - 1;
                    sumAllRectang += (((width + 1) * width) / 2) * height;
                }
                stack.push(k);
            }
            int maxHeightIndex = stack.isEmpty() ? 0 : stack.peek();
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int height = helpArray[pop] - (leftIndex != -1 ? helpArray[leftIndex] : 0);
                int width = maxHeightIndex - leftIndex;
                sumAllRectang += (((width + 1) * width) / 2) * height;
            }
        }
        return sumAllRectang;
    }

    private static int numSubmat2(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;
        int[] helpArray = new int[mat[0].length];
        int sumAllRectang = 0;
        for (int[] its : mat) {
            // get the mat[i] array
            for (int j = 0; j < its.length; j++) {
                helpArray[j] = its[j] == 0 ? 0 : its[j] + helpArray[j];
            }

            int[] stack = new int[helpArray.length];
            int stackIndex = -1;
            for (int k = 0; k < helpArray.length; k++) {
                while (stackIndex != -1 && helpArray[k] <= helpArray[stack[stackIndex]]) {
                    int pop = stack[stackIndex--];
                    int leftIndex = stackIndex == -1 ? -1 : stack[stackIndex];
                    int height = helpArray[pop] - Math.max(leftIndex != -1 ? helpArray[leftIndex] : 0, helpArray[k]);
                    int width = k - leftIndex - 1;
                    sumAllRectang += (((width + 1) * width) / 2) * height;
                }
                stack[++stackIndex] = k;
            }
            int maxHeightIndex = stack[stackIndex];
            while (stackIndex != -1) {
                int pop = stack[stackIndex--];
                int leftIndex = stackIndex == -1 ? -1 : stack[stackIndex];
                int height = helpArray[pop] - (leftIndex != -1 ? helpArray[leftIndex] : 0);
                int width = maxHeightIndex - leftIndex;
                sumAllRectang += (((width + 1) * width) / 2) * height;
            }
        }
        return sumAllRectang;
    }

    // for test
    private static int[][] getRandomArray(int maxSize) {
        int N = NumberOperation.getRandomNumber(maxSize);
        int M = NumberOperation.getRandomNumber(maxSize);
        int[][] res = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j] = NumberOperation.isRandomGreaterThanValue(0.3) ? 1 : 0;
            }
        }
        return res;
    }

    private static void print(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {
            int[][] array = getRandomArray(10);
            int result_1 = numSubmat1(array);
            int result_2 = numSubmat2(array);
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
