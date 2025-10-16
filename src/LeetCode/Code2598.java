package LeetCode;

import java.util.HashMap;

public class Code2598 {
    public static int findSmallestInteger1(int[] nums, int value) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int result = Math.abs(nums[i]) % value;
                nums[i] = result == 0 ? 0 : value - result;
            } else {
                nums[i] = nums[i] % value;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int current = 0;
        int mod = 0;
        while (!map.isEmpty()) {
            if (map.containsKey(mod) && map.get(mod) > 0) {
                map.put(mod, map.get(mod) - 1);
            } else {
                break;
            }
            current++;
            mod = current % value;
        }
        return current;
    }


    public static int findSmallestInteger(int[] nums, int value) {
        int[] count = new int[value];

        for (int num : nums) {
            int mod = ((num % value) + value) % value;
            count[mod]++;
        }

        int mex = 0;
        while (count[mex % value]-- > 0) mex++;
        return mex;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 3, 2, 4, 2, 1, 1, 0, 4};
        int val = 5;
        int smallestInteger = findSmallestInteger(nums, val);
        System.out.println(smallestInteger);
    }
}
