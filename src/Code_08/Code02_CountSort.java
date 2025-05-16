package Code_08;

import tools.ArrayOperation;
import tools.Constants;

public class Code02_CountSort {
    //only for number with regular, example, for 0 -200
    public static void countSort(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;

        int maxValue = Integer.MIN_VALUE;
        for (int j : arr) {
            maxValue = Math.max(maxValue, j);
        }

        int[] counts = new int[maxValue + 1];

        for (int j : arr) {
            counts[j]++;
        }

        int[] help = new int[arr.length];
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i]-- != 0) {
                help[index++] = i;
            }
        }
        System.arraycopy(help, 0, arr, 0, help.length);
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] random = ArrayOperation.getRandomArray(100, 100);
            int[] copy = ArrayOperation.copyArray(random);
            countSort(random);
            ArrayOperation.sort(copy);
            if (!ArrayOperation.isEqualArrays(random, copy)) {
                System.out.println(Constants.CODE_ERROR);
                ArrayOperation.printArray(random);
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
