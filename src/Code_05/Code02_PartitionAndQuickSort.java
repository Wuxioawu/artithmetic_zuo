package Code_05;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

public class Code02_PartitionAndQuickSort {
    /**
     * Place elements ≤ x on the left
     * Place elements > x on the right
     */
    public static void putLeftUpperThanX(int[] arr, int value) {
        if (ArrayOperation.isEmpty(arr)) return;
        int largeIndex = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= value) {
                ArrayOperation.swap(arr, ++largeIndex, i);
            }
        }
    }

    public static boolean testPutLeftUpperThanX(int[] arr, int value) {
        if (ArrayOperation.isEmpty(arr)) return true;

        int index = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                index = i;
                break;
            }
        }

        for (int i = index; i >= 0; i--) {
            if (arr[i] > value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Elements < x on the left
     * Elements == x in the middle
     * Elements > x on the right
     */
    public static void putEqualMid(int[] arr, int value) {
        if (ArrayOperation.isEmpty(arr)) return;
        int lessIndex = -1;
        int moreIndex = arr.length;
        int index = 0;

        while (index < arr.length && lessIndex < moreIndex) {
            if (arr[index] > value && index < moreIndex) {
                ArrayOperation.swap(arr, --moreIndex, index);
                continue;
            }

            if (arr[index] < value && index > lessIndex) {
                ArrayOperation.swap(arr, ++lessIndex, index);
                continue;
            }
            index++;
        }
    }

    public static boolean testPutEqualMid(int[] arr, int value) {
        if (ArrayOperation.isEmpty(arr)) return true;

        int right = arr.length - 1;
        int left = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                right = i;
                break;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= value) {
                left = i;
                break;
            }
        }

        if (left > right) {
            return false;
        }

        while (left < right) {
            if (arr[left] != value) {
                return false;
            }
            left++;
        }
        return true;
    }

    /**
     * return the two index of the equal ranges.
     * use the arr[right] as value
     */
    public static int[] partition(int[] arr, int left, int right) {
        if (ArrayOperation.isEmpty(arr)) return null;
        if (left == right) return new int[]{left, right};

        int moreIndex = right;
        int lessIndex = left - 1;
        int index = left;

        while (index < right && lessIndex < moreIndex) {
            if (moreIndex > index && arr[index] > arr[right]) {
                ArrayOperation.swap(arr, --moreIndex, index);
                continue;
            }

            if (lessIndex < index && arr[index] < arr[right]) {
                ArrayOperation.swap(arr, ++lessIndex, index);
                continue;
            }
            index++;
        }

        ArrayOperation.swap(arr, moreIndex, right);
        return new int[]{lessIndex + 1, moreIndex};
    }

    public static boolean testPartition(int[] arr, int left, int right, int[] result) {
        if (left == right || ArrayOperation.isEmpty(result)) return true;

        int windowL = result[0];
        int windowR = result[1];

        while (left < windowL) {
            if (arr[left] > arr[windowL]) {
                return false;
            }
            left++;
        }

        while (right > windowR) {
            if (arr[right] < arr[windowR]) {
                return false;
            }
            right--;
        }

        for (int i = left; i <= right; i++) {
            if (arr[i] != arr[windowR]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Each recursion handles only one pivot at a time.
     */
    public static void quickSortVersionTwo(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;
        processTwo(arr, 0, arr.length - 1);
    }

    public static void processTwo(int[] arr, int left, int right) {
        if (left >= right) return;
        int[] result = partition(arr, left, right);

        if (ArrayOperation.isEmpty(result)) return;

        processTwo(arr, left, result[0] - 1);
        processTwo(arr, result[1] + 1, right);
    }

    /**
     * Partition returns the [equalStart, equalEnd] region.
     * Recursively apply on < x region and > x region.
     */
    public static void quickSortVersionOne(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;
        processOne(arr, 0, arr.length - 1);

    }

    public static void processOne(int[] arr, int left, int right) {
        if (left >= right) return;

        int index = getLeftIndex(arr, left, right);
        processOne(arr, left, index - 1);
        processOne(arr, index + 1, right);

    }

    public static int getLeftIndex(int[] arr, int left, int right) {
        if (ArrayOperation.isEmpty(arr) || left > right) return -1;
        if (left == right) return arr[left];


        int index = left;
        int lessIndex = left - 1;

        while (index < right) {
            if (arr[index] <= arr[right]) {
                ArrayOperation.swap(arr, ++lessIndex, index);
            }
            index++;
        }

        ArrayOperation.swap(arr, ++lessIndex, right);
        return lessIndex;
    }

    public static void quickSortVersionThree(int[] arr) {
        if (!ArrayOperation.isSortable(arr)) return;
        processThree(arr, 0, arr.length - 1);
    }

    public static void processThree(int[] arr, int left, int right) {
        if (left >= right) return;

        int randomIndex = left + NumberOperation.getRandomNumber(right - left + 1);
        ArrayOperation.swap(arr, randomIndex, right);

        int[] result = partition(arr, left, right);

        processThree(arr, left, result[0] - 1);
        processThree(arr, result[1] + 1, right);
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);

            int[] copyArray = ArrayOperation.copyArray(array);
            int[] originalArray = ArrayOperation.copyArray(array);

            quickSortVersionThree(array);
            ArrayOperation.sort(copyArray);

            if (!ArrayOperation.isEqualArrays(copyArray, array)) {
                System.out.println("array: ");
                ArrayOperation.printArray(array);

                System.out.println("copy array: ");
                ArrayOperation.printArray(copyArray);

                System.out.println("original array: ");
                ArrayOperation.printArray(originalArray);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
