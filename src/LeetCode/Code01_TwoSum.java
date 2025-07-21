package LeetCode;

import java.util.HashMap;

public class Code01_TwoSum {


    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3};
        int target = 6;

        int[] ints = twoSum(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }

    }
}
