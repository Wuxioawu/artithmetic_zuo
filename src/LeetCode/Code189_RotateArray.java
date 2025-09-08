package LeetCode;

import java.util.LinkedList;


public class Code189_RotateArray {
    public void rotate(int[] nums, int k) {
        int N = nums.length;
        k = k % N;

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            list.addLast(nums[i]);
        }

        while (k-- != 0) {
            list.addFirst(list.removeLast());
        }

        for (int i = 0; i < N; i++) {
            nums[i] = list.removeFirst();
        }
    }

    public static void rotate2(int[] nums, int k) {
        int N = nums.length;
        k = k % N;
        int[] help = new int[k];
        int help_length = help.length;
        for (int i = N - 1; i >= 0 && help_length > 0; i--) {
            help[--help_length] = nums[i];
        }
        for (int i = N - 1; i >= k; i--) {
            nums[i] = nums[i - k];
        }
        for (int i = 0; i < help.length; i++) {
            nums[i] = help[i];
        }
    }

    public static void rotate3(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int left ,int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        rotate2(nums, 2);
    }
}
