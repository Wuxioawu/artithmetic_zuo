package Interview.amazon;

import java.util.Arrays;

/**
 * link:<a href="https://www.reddit.com/r/leetcode/comments/1j96wui/amazon_oa_question/">...</a>
 * leecode :https://leetcode.com/problems/put-marbles-in-bags/description/
 * */

public class FindPartitionCost {

    public static int[] findPartition(int[] cost, int k) {
        if (cost == null || cost.length == 0) return null;
        if (k <= 0 || k > cost.length) return null;

        int[] sumTwoNum = new int[cost.length - 1];

        for (int i = 0; i < cost.length - 1; i++) {
            sumTwoNum[i] = cost[i] + cost[i + 1];
        }

        Arrays.sort(sumTwoNum);

        int minSum = cost[0] + cost[cost.length - 1];
        int maxSum = cost[0] + cost[cost.length - 1];

        for (int i = 0; i < k - 1; i++) {
            minSum += sumTwoNum[i];
            maxSum += sumTwoNum[sumTwoNum.length - 1 - i];
        }

        return new int[]{minSum, maxSum};
    }
}
