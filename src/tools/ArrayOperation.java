package tools;

import java.util.*;

public class ArrayOperation {

    // swap two element in a array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void printArray(int[] arr) {
        if (arr == null) return;
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static boolean isSortable(int[] arr) {
        return arr != null && arr.length >= 2;
    }

    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length < 1;
    }

    /**
     * @param maxSize  Maximum length of the generated array
     * @param maxValue Maximum value among the generated data elements
     * @return the generated random array
     */
    public static int[] getRandomArray(int maxSize, int maxValue) {
        if (maxSize < 1) return null;

        int[] arr = new int[NumberOperation.getRandomNumberIncludeValue(maxSize)];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = NumberOperation.getRandomNumberIncludeValue(maxValue) + 1;
        }
        return arr;
    }

    /**
     * @param arr1 first param
     * @param arr2 second param
     * @return is equal between arr1 and arr2
     */
    public static boolean isEqualArrays(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) return true;
        if (arr1 == null || arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    /**
     * copy all elements of a array to another array
     *
     * @param arr original array
     * @return the new array which is same elements with original array
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) return null;
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
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
        int times = 1000000;

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
        for (int j : arr)
            if (j == num) {
                return true;
            }
        return false;
    }


    public static int getRandomElement(int[] arr) {
        if (arr == null || arr.length < 1) return -1;

        return arr[NumberOperation.getRandomNumber(arr.length)];
    }

    public static int[] removeSameElements(int[] arr) {
        if (isEmpty(arr)) return arr;

        Set<Integer> set = new LinkedHashSet<>();

        for (int num : arr) {
            set.add(num);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void shuffleArray(int[] arr, int left, int right) {
        if (isEmpty(arr)) return;
        Random random = new Random();
        for (int i = right; i >= left; i--) {
            int index = left + random.nextInt(right - left + 1);
            swap(arr, index, i);
        }
    }

    // Generate a random element that is not present in the current array
    public static int getRandomElementOutArray(int[] arr, int value) {
        if (isEmpty(arr)) return Constants.UN_INVALID;

        Set<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }
        int candidate = Constants.UN_INVALID;
        do {
            candidate = NumberOperation.getRandomNumberIncludeValue(value);
        } while (set.contains(candidate));
        return candidate;
    }
}






















