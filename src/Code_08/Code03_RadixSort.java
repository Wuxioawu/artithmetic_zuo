package Code_08;

import tools.ArrayOperation;
import tools.Constants;

import java.util.Arrays;

public class Code03_RadixSort {
    public static void radixSort(int[] arr) {

        if (!ArrayOperation.isSortable(arr)) return;

        int maxValue = Integer.MIN_VALUE;
        for (int k : arr) {
            maxValue = Math.max(maxValue, k);
        }

        int times = getBit(maxValue);
        int[] counts = new int[10];
        int[] help = new int[arr.length];

        for (int i = 1; i <= times; i++) {

            for (int k : arr) {
                int index = getValueBit(k, i);
                counts[index]++;
            }

            for (int j = 1; j < counts.length; j++) {
                counts[j] = counts[j] + counts[j - 1];
            }

            //put int help
            for (int j = arr.length - 1; j >= 0; j--) {
                int valueBit = getValueBit(arr[j], i);
                counts[valueBit]--;
                help[counts[valueBit]] = arr[j];
            }

            Arrays.fill(counts, 0);
            System.arraycopy(help, 0, arr, 0, help.length);
        }
    }

    // // Get the digit at position d (1 = units place) from number x
    public static int getValueBit(int num, int index) {
        int result = 0;
        for (int i = 0; i < index; i++) {
            result = num % 10;
            num = num / 10;
        }
        return result;
    }

    public static int getBit(int num) {
        int res = 0;
        while (num != 0) {
            res++;
            num = num / 10;
        }
        return res;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(10, 10);
            int[] copy = ArrayOperation.copyArray(array);

            radixSort(array);
            ArrayOperation.sort(copy);

            if (!ArrayOperation.isEqualArrays(array, copy)) {
                System.out.println(Constants.CODE_ERROR);
                ArrayOperation.printArray(array);
                ArrayOperation.printArray(copy);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
