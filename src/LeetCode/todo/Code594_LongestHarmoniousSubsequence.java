package LeetCode.todo;

public class Code594_LongestHarmoniousSubsequence {

    public static int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int resultLength = 0;
        int length = 0;
        boolean isSame = true;

        //1, 3, 2, 2, 5, 2, 3, 7
        for (int i = 0; i < nums.length; i++) {
            int maxValue = nums[i] + 1;
            int minValue = nums[i] - 1;
            length++;

            for (int j = i; j < nums.length; j++) {
                if (nums[j] == maxValue || nums[j] == minValue || nums[j] == nums[i]) {
                    length++;
                    if (nums[j] != nums[i]) {
                        isSame = false;
                    }
                }
            }

            if (isSame) {
                continue;
            }
            resultLength = Math.max(length, resultLength);
            length = 0;
        }
        return resultLength;
    }

//    public static int findLHS2(int[] nums) {
//
//    }


    // for test
    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println(findLHS(nums));


    }
}
