package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

public class Code219_ContainDuplicate {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }

    public static boolean containsNearbyDuplicate2(int[] nums, int k) {
        int index = 0;
        while (index < nums.length) {
            if (containSameElements(nums, index, Math.min(index + k, nums.length - 1))) {
                return true;
            } else {

                index++;
            }
        }
        return false;
    }

    private static boolean containSameElements(int[] nums, int strat, int end) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = strat; i <= end; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1,2,3};
        int k = 2;
        boolean b = containsNearbyDuplicate(nums, k);

        System.out.println(b);
    }
}
