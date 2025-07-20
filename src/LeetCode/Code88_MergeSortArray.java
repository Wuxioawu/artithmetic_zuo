package LeetCode;

import java.util.Arrays;

// https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
public class Code88_MergeSortArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int start = m == 0 ? 0 : m;

        for (int i = start, j = 0; i < nums1.length && j < nums2.length; i++) {
            nums1[i] = nums2[j++];
        }
        Arrays.sort(nums1);
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n != 0 || m == 0) {
            System.arraycopy(nums2, 0, nums1, m, n);
            Arrays.sort(nums1);
        }
    }

    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int index = nums1.length - 1;
        int i, j;

        for (i = m - 1, j = nums2.length - 1; i >= 0 && j >= 0; ) {
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];

        }

        while (j >= 0) {
            nums1[index--] = nums2[j--];
        }
        while (i >= 0) {
            nums1[index--] = nums1[i--];
        }
    }

    public static void main(String[] args) {
        int[] num1 = {1,2,3,0,0,0};
        int[] num2 = {2,5,6};
        int n = 3;
        int m = 3;
        merge3(num1, m, num2, n);
        System.out.println(Arrays.toString(num1));
    }
}
