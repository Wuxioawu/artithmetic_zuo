package LeetCode;


import java.util.Arrays;

public class Code2517 {
    public static int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = 0;
        int right = price[price.length - 1] - price[0];
        int ans = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (findValue(k, price, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private static boolean findValue(int k, int[] price, int mid) {
        int count = 1;
        int start = price[0];
        for (int i = 1; i < price.length; i++) {
            if (price[i] - start >= mid) {
                start = price[i];
                count++;
                if (count >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,3,1};
        int k = 2;
        int i1 = maximumTastiness(height, k);
        System.out.println(i1);

    }
}
