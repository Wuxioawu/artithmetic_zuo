
package tools;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayOperation {

    // swap two element in an array with bounds checks
    public static void swap(int[] arr, int i, int j) {
        if (arr == null) throw new IllegalArgumentException("arr must not be null");
        if (i < 0 || j < 0 || i >= arr.length || j >= arr.length)
            throw new IndexOutOfBoundsException("index out of bounds");
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static boolean isSortable(int[] arr) {
        return arr != null && arr.length >= 2;
    }

    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }

    // create an array of a fixed size with values in [0, maxValue]
    public static int[] getArray(int size, int maxValue) {
        if (size <= 0) return new int[0];
        if (maxValue < 0) throw new IllegalArgumentException("maxValue must be >= 0");
        int[] arr = new int[size];
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextInt(maxValue + 1);
        }
        return arr;
    }

    /**
     * @param maxSize  Maximum length of the generated array (>= 0)
     * @param maxValue Maximum value among the generated data elements (>= 0)
     * @return the generated random array (may be empty)
     */
    public static int[] getRandomArray(int maxSize, int maxValue) {
        if (maxSize <= 0) return new int[0];
        if (maxValue < 0) throw new IllegalArgumentException("maxValue must be >= 0");
        // size in [0, maxSize]
        int size = ThreadLocalRandom.current().nextInt(maxSize + 1);
        return getArray(size, maxValue);
    }

    /**
     * @param arr1 first param
     * @param arr2 second param
     * @return is equal between arr1 and arr2
     */
    public static boolean isEqualArrays(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    /**
     * copy all elements of an array to another array
     *
     * @param arr original array
     * @return the new array which has same elements as original array (null -> null)
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) return null;
        return Arrays.copyOf(arr, arr.length);
    }

    public static void sort(int[] arr) {
        if (!isSortable(arr)) return;
        Arrays.sort(arr);
    }

    /**
     * @param test test the sort arithmetic
     */
    public static void testSort(TestExecution test) {
        int maxSize = 100;
        int maxValue = 100;
        int times = 1_000_000;

        for (int i = 0; i < times; i++) {
            int[] arr = getRandomArray(maxSize, maxValue);
            int[] newArr = copyArray(arr);

            test.sort(arr);
            sort(newArr);

            if (!isEqualArrays(arr, newArr)) {
                System.out.println("the array is not equal");
                printArray(arr);
                printArray(newArr);
                return;
            }
        }
        System.out.println("the array is equal");
    }

    public static boolean isIncludeNumber(int[] arr, int num) {
        if (arr == null) return false;
        for (int j : arr) if (j == num) return true;
        return false;
    }

    public static int getRandomElement(int[] arr) {
        if (arr == null || arr.length < 1) return -1;
        return arr[ThreadLocalRandom.current().nextInt(arr.length)];
    }

    public static int[] removeSameElements(int[] arr) {
        if (isEmpty(arr)) return arr;
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) set.add(num);
        int[] res = new int[set.size()];
        int i = 0;
        for (int v : set) res[i++] = v;
        return res;
    }

    public static void shuffleArray(int[] arr, int left, int right) {
        if (arr == null) return;
        int n = arr.length;
        if (n == 0) return;
        int l = Math.max(0, left);
        int r = Math.min(n - 1, right);
        if (l >= r) return;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i = r; i >= l; i--) {
            int index = rnd.nextInt(l, r + 1);
            swap(arr, index, i);
        }
    }

    // Generate a random element that is not present in the current array
    public static int getRandomElementOutArray(int[] arr, int value) {
        if (isEmpty(arr)) return Constants.UN_INVALID;
        if (value < 0) throw new IllegalArgumentException("value must be >= 0");
        Set<Integer> set = new HashSet<>();
        for (int num : arr) set.add(num);
        int candidate;
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        do {
            candidate = rnd.nextInt(value + 1);
        } while (set.contains(candidate));
        return candidate;
    }
}
