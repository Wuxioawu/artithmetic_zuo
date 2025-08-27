package Code_18;

import tools.ArrayOperation;
import tools.Constants;

import java.math.BigInteger;

public class Code06_SumOfSubarrayMinimums {

    private static int sumSubarrayMins1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int restSum = 0;
        for (int i = 0; i < arr.length; i++) {
            restSum += arr[i];
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                }
                restSum += min;
                restSum %= 1000000007;
            }
        }
        return restSum;
    }

    private static int sumSubarrayMins2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        BigInteger sumResult = new BigInteger("0");
        BigInteger modConst = new BigInteger("1000000007");
        int[] stack = new int[arr.length];
        int stackIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            while (stackIndex != -1 && arr[i] < arr[stack[stackIndex]]) {
                Integer pop = stack[stackIndex--];
                int leftIndex = stackIndex == -1 ? -1 : stack[stackIndex];
                int arrNum = (pop - leftIndex) % 1000000007 * (i - pop);
                arrNum %= 1000000007;
                sumResult = sumResult.add(new BigInteger(Integer.toString(arrNum * arr[pop])));
                sumResult = sumResult.mod(modConst);
            }
            stack[++stackIndex] = i;
        }
        while (stackIndex != -1) {
            Integer pop = stack[stackIndex--];
            int leftIndex = stackIndex == -1 ? 0 : stack[stackIndex];
            int left = pop - leftIndex;
            int right = arr.length - pop;
            int arrNum = left * right;
            if (stackIndex == -1) {
                right--;
                arrNum = ((arr.length + 1) * arr.length / 2) - ((left + 1) * left / 2 + (right + 1) * right / 2);
                arrNum %= 1000000007;
            }
            sumResult = sumResult.add(new BigInteger(Integer.toString(arrNum * arr[pop])));
            sumResult = sumResult.mod(modConst);
        }
        return sumResult.intValue();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000000; i++) {
            int[] arr = ArrayOperation.getRandomArray(1000, 1000);
            int result_1 = sumSubarrayMins1(arr);
            int result_2 = sumSubarrayMins2(arr);
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
