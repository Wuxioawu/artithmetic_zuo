package Code_01;

import tools.ArrayOperation;
import tools.NumberOperation;

import java.util.Arrays;

public class Code04_BSExist {

    /**
     * find whether the array include a target
     * @param arr
     * @param target
     * @return
     */
    public static boolean exist(int[] arr, int target) {
        if(arr == null || arr.length == 0) return false;

        int left = 0, right = arr.length - 1, mid = 0;

        while(left <= right) {
            mid = left + ((right - left) >> 1);
            if(arr[mid] == target) {
                return true;
            }

            if(arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    //for test
    public static void test() {
        int maxSize = 100;
        int maxValue = 100;
        int times = 1000000;

        for (int i = 0; i < times; i++) {
            int[] array = ArrayOperation.getRandomArray(maxSize, maxValue);
            if (!ArrayOperation.isSortable(array)) continue;
            Arrays.sort(array);
            int target = NumberOperation.getRandomNumberIncludeValue(maxValue);
            if(! (ArrayOperation.isIncludeNumber(array, target) == exist(array, target))) {
                System.out.println("the array is not equal");
                return;
            }
        }
        System.out.println("Nice");
    }


    public static void main(String[] args) {
        test();
    }

}
