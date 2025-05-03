package Code_01;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

public class Code05_BSNearLeft {

    public static int getBSNearLeft(int[] nums, int target) {
        if (nums == null || nums.length == 0) return Constants.UN_INVALID;
        int left = 0,right = nums.length - 1, index = Constants.NOT_FIND;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if(nums[mid] >= target){
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    public static int findNearLeft(int[] nums, int target) {
        if (nums == null || nums.length == 0) return Constants.UN_INVALID;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return Constants.NOT_FIND;
    }

    // for test
    public static void test() {
        int maxValue = 100;
        int maxSize = 100;
        int times = 10000000;

        for (int i = 0; i < times; i++) {
            int[] array = ArrayOperation.getRandomArray(maxSize, maxValue);
            ArrayOperation.sort(array);
            int target = NumberOperation.isRandomGreaterThanValue(0.5) ?
                    NumberOperation.getRandomNumberIncludeValue(maxValue) :
                    ArrayOperation.getRandomElement(array);
            if(target == -1) continue;

            int BSNResult = getBSNearLeft(array, target);
            int findResult = findNearLeft(array, target);

            if(BSNResult != findResult){

                ArrayOperation.printArray(array);
                System.out.println("target = " + target);
                System.out.println("BSNResult = " + BSNResult + " findResult = " + findResult);
                System.out.println("The code is error");

                return;
            }
        }
        System.out.println("Nice");

    }

    public static void main(String[] args) {
        test();
    }
}
