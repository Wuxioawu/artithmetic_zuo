package LeetCode;

public class Code152 {
    private static int rob(int[] nums) {
        return 0;
    }

    private static int process(int[] nums, int index) {
        if (index >= nums.length)
            return 0;
        int noRob = process(nums, index + 1);
        int rob = nums[index] + process(nums, index + 2);
        return Math.max(noRob, rob);
    }

    public static void main(String[] args) {

    }
}

