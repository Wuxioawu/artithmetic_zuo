package Code_08;

import tools.ArrayOperation;
import tools.Constants;

public class Code05_ShellSort {
    public static void shellSort(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;
        int step = arr.length / 2;
        while (step > 0) {
            for (int k = 0; k < arr.length; k++) {
                for (int j = 0; j < arr.length; j += step) {
                    if (j + step >= arr.length) break;
                    if (arr[j] > arr[j + step]) {
                        ArrayOperation.swap(arr, j, j + step);
                    }
                }
            }
            step /= 2;
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] numbers = ArrayOperation.getRandomArray(100, 100);
            int[] copy = ArrayOperation.copyArray(numbers);
            shellSort(numbers);
            ArrayOperation.sort(copy);
            if (!ArrayOperation.isEqualArrays(numbers, copy)) {
                System.out.println(Constants.CODE_ERROR);
                ArrayOperation.printArray(numbers);
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
