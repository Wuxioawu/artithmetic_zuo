package LeetCode;

public class Code209 {
    public static int minSubArrayLen(int target, int[] nums) {
        // get the preSum
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;

        while (right < nums.length) {
            if (preSum[right] - preSum[left] >= target) {
                ans = Math.min(right - left, ans);
                left++;
            } else {
                right++;
            }
        }
        return ans;
    }

    public static int minSubArrayLen2(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                minLength = Math.min(minLength, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int i1 = minSubArrayLen(7, nums);
        System.out.println(i1);
    }
}
