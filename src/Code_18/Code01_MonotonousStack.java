package Code_18;

import tools.ArrayOperation;
import tools.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code01_MonotonousStack {

    public static int[][] getNearLessNoRepeat1(int[] arr) {
        if (arr.length == 0) return new int[0][0];
        int[][] result = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++) {
            int leftMin = -1;
            for (int left = i - 1; left >= 0; left--) {
                if (arr[left] < arr[i]) {
                    leftMin = left;
                    break;
                }
            }

            int rightMin = -1;
            for (int right = i + 1; right < arr.length; right++) {
                if (arr[right] < arr[i]) {
                    rightMin = right;
                    break;
                }
            }

            result[i][0] = leftMin;
            result[i][1] = rightMin;
        }

        return result;
    }

    public static int[][] getNearLessNoRepeat2(int[] arr) {
        if (arr.length == 0) return new int[0][0];
        Stack<List<Integer>> stack = new Stack<>();
        int[][] result = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().getLast()]) {
                List<Integer> pop = stack.pop();
                for (int j = 0; j < pop.size(); j++) {
                    Integer index = pop.get(j);
                    result[index][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                    result[index][1] = i;
                }
            }

            if (stack.isEmpty() || arr[i] > arr[stack.peek().getLast()]) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else {
                stack.peek().addLast(i);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            for (int j = 0; j < pop.size(); j++) {
                result[pop.get(j)][0] = stack.isEmpty() ? -1 : stack.peek().getLast();
                result[pop.get(j)][1] = stack.isEmpty() ? -1 : stack.peek().getLast();
            }
        }

        return result;
    }

    //for test

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean checkArray(int[][] arrFirst, int[][] arrSecond) {
        if (arrFirst == null && arrSecond == null) return true;
        if (arrFirst == null || arrSecond == null) return false;

        if (arrFirst.length != arrSecond.length || arrFirst[0].length != arrSecond[0].length) return false;

        for (int i = 0; i < arrFirst.length; i++) {
            for (int j = 0; j < arrFirst[0].length; j++) {
                if (arrFirst[i][j] != arrSecond[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            int[] arr = {3, 7, 8, 21, 13, 11, 10, 10, 3, 3};
            ArrayOperation.getRandomArray(10, 20);

            int[][] nearLessNoRepeat1 = getNearLessNoRepeat1(arr);
            int[][] nearLessNoRepeat2 = getNearLessNoRepeat2(arr);

            if (!checkArray(nearLessNoRepeat1, nearLessNoRepeat2)) {
                System.out.println(Constants.CODE_ERROR);
                ArrayOperation.printArray(arr);
                print(nearLessNoRepeat1);
                print(nearLessNoRepeat2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
