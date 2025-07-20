package LeetCode;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/?envType=study-plan-v2&envId=top-interview-150
public class Code121_BestTimeToSellStock {

    public static int maxProfit(int[] prices) {

        int maxProfit = 0;
        int buyPrices = prices[0];

        for (int i = 1; i < prices.length; i++) {
            buyPrices = Math.min(buyPrices, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - buyPrices);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {1,2};
        int i = maxProfit(prices);
        System.out.println(i);
    }

}
