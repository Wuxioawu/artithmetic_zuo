package LeetCode;

import java.util.Arrays;
import java.util.List;

public class Code3350 {
    public static int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        if (n <= 1) return 1;
        boolean[] isIncreasing = new boolean[n];
        isIncreasing[0] = false;
        for (int i = 1; i < n; i++) {
            isIncreasing[i] = nums.get(i) > nums.get(i - 1);
        }
        int[] P = new int[n];
        int center = 0, right = 0, maxK = 1;
        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;
            if (i < right) {
                P[i] = Math.min(P[mirror], right - i);
            }
            while (i - P[i] - 1 >= 0 && i + P[i] + 1 < n
                    && isIncreasing[i - P[i] - 1] && isIncreasing[i + P[i] + 1]) {
                P[i]++;
            }
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
            maxK = Math.max(maxK, P[i] + 1);
            int t = Integer.MIN_VALUE;
        }

        return maxK;
    }

    private static int isRight(boolean[] isIncreasing, int index) {
        int step = 1;
        while ((index - step > 0 && index + step < isIncreasing.length) && isIncreasing[index - step] && isIncreasing[index + step]) {
            step++;
        }
        return step;
    }

    private static int maxIncreasingSubarrays2(List<Integer> nums) {
        int currentStep = 1;
        int preStep = 0;
        int ans = 0;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                currentStep++;
            } else {
                preStep = currentStep;
                currentStep = 1;
            }

            ans = Math.max(ans, Math.max(currentStep / 2, Math.min(currentStep, preStep)));
        }
        return ans;
    }


    public static void main(String[] args) {
        Integer[] nums = {-10,14,17};
        List<Integer> list = Arrays.asList(nums);
        int i1 = maxIncreasingSubarrays(list);
        System.out.println(i1);
    }
}
