package LeetCode;


import java.util.Arrays;

public class Code27_RemoveElement {

    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        Arrays.sort(nums, 0, index);
        return index;
    }

    private static void sort(int[] nums, int i , int j) {
        Arrays.sort(nums, i, j);
    }

    public static void main(String[] args) {
    }
}
