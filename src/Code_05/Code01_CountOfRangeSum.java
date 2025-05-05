package Code_05;

/**
 * Given an integer array nums and two integers lower and upper,
 * return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 * return the number of subarrays whose sum lies within the range [lower, upper].
 * <p>
 * <a href="https://leetcode.com/problems/count-of-range-sum/">leetCode</a>
 */
public class Code01_CountOfRangeSum {

    public static int getCountOfRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        // get range sum
        long[] sumArr = new long[nums.length];
        sumArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sumArr[i] = sumArr[i - 1] + nums[i];
        }
        return process(sumArr, 0, nums.length - 1, lower, upper);

    }

    public static int process(long[] sumArr, int left, int right, int lower, int upper) {
        if (left == right) {
            return sumArr[left] >= lower && sumArr[left] <= upper ? 1 : 0;
        }
        int mid = left + (right - left) / 2;
        return process(sumArr, left, mid, lower, upper) +
                process(sumArr, mid + 1, right, lower, upper) +
                merge(sumArr, left, mid, right, lower, upper);
    }

    public static int merge(long[] sumArr, int left, int mid, int right, int lower, int upper) {
        int result = 0;
        int windowL = left;
        int windowR = left;

        for (int i = mid + 1; i <= right; i++) {
            long min = sumArr[i] - upper;
            long max = sumArr[i] - lower;

            while (windowR <= mid && sumArr[windowR] <= max) {
                windowR++;
            }

            while (windowL <= mid && sumArr[windowL] < min) {
                windowL++;
            }
            result += Math.max(0, windowR - windowL);
        }

        long[] temp = new long[right - left + 1];

        int index = 0;
        windowL = left;
        windowR = mid + 1;

        while (windowL <= mid && windowR <= right) {
            temp[index++] = sumArr[windowL] <= sumArr[windowR] ? sumArr[windowL++] : sumArr[windowR++];
        }

        while (windowL <= mid) {
            temp[index++] = sumArr[windowL++];
        }
        while (windowR <= right) {
            temp[index++] = sumArr[windowR++];
        }

        for (int i = 0; i < temp.length; i++) {
            sumArr[left + i] = temp[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 5, -1};

        int lower = -2;
        int upper = 2;

        System.out.println(getCountOfRangeSum(arr, lower, upper));

    }
}
