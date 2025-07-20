package LeetCode.todo;

import java.util.Arrays;

public class Code169_MajorityElements {

    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;

        if (nums.length == 1) return nums[0];

        Arrays.sort(nums);
        int step = 0;
        int majorityElement = 0;

        for (int i = 0; i < nums.length; i++) {
            int index = i;
            while (index + 1 < nums.length && nums[i] == nums[index + 1]) {
                index++;
            }

            if (index - i > step) {
                step = index - i;
                majorityElement = nums[i];
                System.out.println("majorityElement: " + majorityElement + "step: " + step);
            }

            i = index;
        }
        return majorityElement;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int i = majorityElement(nums);
        System.out.println(i);

    }
}
