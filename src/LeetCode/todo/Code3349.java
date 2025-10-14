package LeetCode.todo;

import java.util.Arrays;
import java.util.List;

public class Code3349 {

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {

        StringBuilder target = new StringBuilder();
        for (int i = 1; i < nums.size(); i++) {
            target.append(nums.get(i) - nums.get(i - 1) > 0 ? "1" : "0");
        }

        String one = "";
        String two = "";

        for (int i = 0; i < k * 2; i++) {
            one += "1";
            if (i == k) {
                two += "0";
                continue;
            }
            two += "1";
        }

        return target.toString().contains(one) || target.toString().contains(two);

    }

    public static boolean hasIncreasingSubarrays2(List<Integer> nums, int k) {
        for (int i = 0; i < nums.size(); i++) {
            if (isStrickIncrease(nums, i, i + k - 1) && isStrickIncrease(nums, i + k, i + 2 * k - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStrickIncrease(List<Integer> nums, int start, int end) {
        if(end >= nums.size()) return false;
        if (start == end) return true;
        if (start > end)  return false;
        for (int i = start + 1; i <= end; i++) {
            if (nums.get(i) <= nums.get(i - 1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,4,4,4,5,6,7};
        List<Integer> list = Arrays.asList(arr);
        boolean b = hasIncreasingSubarrays2(list, 5);
        System.out.println(b);
    }
}
