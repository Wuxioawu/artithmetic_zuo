package LeetCode;

public class Code55 {

    public static boolean canJump(int[] nums) {
        int finalIndex = 0;
        int N = nums.length;
        for (int i = 0; i < N - 1; i++) {
            finalIndex = Math.max(finalIndex, i + nums[i]);
            if(finalIndex == i) {
                return false;
            }
        }
        return finalIndex >= N - 1;
    }

    public static void main(String[] args) {
        int[] nums = {0,2,3};
        boolean b = canJump(nums);
        System.out.println(b);

    }
}
