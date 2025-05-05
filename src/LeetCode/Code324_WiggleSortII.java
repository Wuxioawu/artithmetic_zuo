package LeetCode;

import tools.ArrayOperation;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/wiggle-sort-ii/description/">...</a>
 */

public class Code324_WiggleSortII {
    public static void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        Arrays.sort(nums);

        int[] help = new int[nums.length];
        int lowIndex = 0;
        int highIndex = nums.length / 2;
        int index = 0;

        while (index < nums.length) {
            help[index++] = nums[lowIndex++];
            help[index++] = nums[highIndex++];
        }

        System.arraycopy(help, 0, nums, 0, nums.length);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2, 2, 3, 1};
        wiggleSort(nums);
        ArrayOperation.printArray(nums);
    }

}