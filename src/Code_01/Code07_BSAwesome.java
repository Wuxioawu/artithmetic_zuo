package Code_01;

import tools.ArrayOperation;
import tools.Constants;

public class Code07_BSAwesome {

    /**
     * find an element in the array that is smaller than its neighbors
     * @param arr a original array
     * @return index of the original arr and arr[index] is the target number
     */
    public static int getLessIndex(int[] arr) {
        if(arr == null || arr.length <1 ) return Constants.UN_INVALID;

        if(arr.length == 1 || arr[0] < arr[1]) return 0;
        if(arr[arr.length-1] < arr[arr.length -2] ) return arr.length-1;

        int left = 1,right = arr.length - 2, index = Constants.NOT_FIND;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);

            if(arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if(arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }

        }
        return left;
    }


    //for test
    public static boolean isRight(int[] arr, int index) {
      if(index == Constants.UN_INVALID) return true;

      if(arr.length <=1) return true;

      if(index == 0) return arr[index] < arr[index + 1];
      if(index == arr.length - 1) return arr[index] < arr[index - 1];

      return arr[index] < arr[index + 1] && arr[index] > arr[index - 1];
    }


    public static void test() {
        for (int i = 0; i < Constants.TEST_TIMES; i ++) {
            int[] arr = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE,Constants.TEST_MAX_VALUE);

            arr = ArrayOperation.removeSameElements(arr);
            ArrayOperation.sort(arr);

            int lessIndex = getLessIndex(arr);
            if(!isRight(arr, lessIndex)) {
                ArrayOperation.printArray(arr);
                System.out.println(lessIndex);
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);

    }

    public static void main(String[] args) {
        test();
    }

}




















