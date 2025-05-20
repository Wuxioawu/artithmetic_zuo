package LeetCode;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/put-marbles-in-bags/">...</a>
 */
public class Code2552_PutMarbles {
    public long putMarbles(int[] weights, int k) {

        long[] sumTwoNum = new long[weights.length - 1];

        for (int i = 0; i < weights.length - 1; i++) {
            sumTwoNum[i] = weights[i] + weights[i + 1];
        }

        Arrays.sort(sumTwoNum);

        long minSum = 0;
        long maxSum = 0;

        for (int i = 0; i < k - 1; i++) {
            minSum += sumTwoNum[i];
            maxSum += sumTwoNum[sumTwoNum.length - 1 - i];
        }

        return maxSum - minSum;
    }
}
