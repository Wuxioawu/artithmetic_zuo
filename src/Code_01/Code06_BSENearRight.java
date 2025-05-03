package Code_01;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

public class Code06_BSENearRight {

    public static int getBSNearRight(int[] arr, int target) {
        if (arr == null || arr.length < 1) return Constants.UN_INVALID;

        int left = 0,right = arr.length - 1, index = Constants.NOT_FIND;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if(arr[mid] <= target) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }


    public static int getNearRightByFor(int[] arr, int target) {
        if (arr == null || arr.length < 1) return Constants.UN_INVALID;

        for (int i = arr.length -1; i >= 0; i--) {
            if (arr[i] <= target) return i;
        }

        return Constants.NOT_FIND;
    }


    // for test
    public static void test() {
        int maxSize = 100;
        int maxValue = 100;
        int times = 1000000;

        for (int i = 0; i < times; i++) {
            int[] array = ArrayOperation.getRandomArray(maxSize, maxValue);
            ArrayOperation.sort(array);

            int target = NumberOperation.isRandomGreaterThanValue(0.5) ?
                    NumberOperation.getRandomNumber(maxValue) :
                    ArrayOperation.getRandomElement(array);

            int resultByBSN = getBSNearRight(array, target);
            int resultByFor = getNearRightByFor(array, target);

            if (resultByBSN != resultByFor) {
                ArrayOperation.printArray(array);
                System.out.println(target);
                System.out.println("resultByBSN = " + resultByBSN + "   resultByFor = " + resultByFor);
                return;
            }

        }

        System.out.println("Nice");
    }

    public static void main(String[] args) {
        test();
    }

}
