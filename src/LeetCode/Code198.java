package LeetCode;

import java.util.Arrays;

public class Code198 {
    public static int rob(int[] nums) {
        return Math.max(process(nums, 1),process(Arrays.copyOf(nums, nums.length - 1), 0));
    }

    private static int process(int[] nums, int index) {
        if (index >= nums.length)
            return 0;
        int noRob = process(nums, index + 1);
        int rob = nums[index] + process(nums, index + 2);
        return Math.max(noRob, rob);
    }

    private static int dp(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int rob = rob(nums);
        System.out.println(rob);  //4
    }
}
