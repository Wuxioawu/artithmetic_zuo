package Code_16;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// 推导位置依赖
public class Code13_CoinsWaySameValueSamePapper {

    private static int[][] coverMoneyArray(int[] arr) {
        HashMap<Integer, Integer> moneyMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (moneyMap.containsKey(arr[i])) {
                moneyMap.put(arr[i], moneyMap.get(arr[i]) + 1);
            } else {
                moneyMap.put(arr[i], 1);
            }
        }

        int[][] money_num = new int[2][moneyMap.size()];

        int index = 0;
        for (int money : moneyMap.keySet()) {
            money_num[0][index] = money;
            money_num[1][index] = moneyMap.get(money);
            index++;
        }
        return money_num;
    }

    private static int coinsWay1(int[] arr, int aim) {
        int[][] money_num = coverMoneyArray(arr);
        int[] coins = money_num[0].clone();
        int[] nums = money_num[1].clone();

        return process(coins, nums, 0, aim);
    }

    private static int process(int[] moneys, int[] nums, int index, int rest) {
        if (rest < 0) return 0;
        if (index == moneys.length) {
            return rest == 0 ? 1 : 0;
        }
        int result = 0;
        for (int num = 0; num <= nums[index]; num++) {
            result += process(moneys, nums, index + 1, rest - num * moneys[index]);
        }
        return result;
    }

    private static int coinsWay2(int[] arr, int aim) {
        int[][] money_num = coverMoneyArray(arr);

        int[] coins = money_num[0].clone();
        int[] nums = money_num[1].clone();

        int[][] dp = new int[coins.length + 1][aim + 1];
        dp[coins.length][0] = 1;

        for (int index = coins.length - 1; index >= 0; index--) {
            for (int rest = aim; rest >= 0; rest--) {
                for (int num = 0; num <= nums[index]; num++) {
                    if (rest - num * coins[index] >= 0) {
                        dp[index][rest] += dp[index + 1][rest - num * coins[index]];
                    }
                }
            }
        }
        return dp[0][aim];
    }

    // for test
    private static int[] creatArray(int maxNum, int maxTypes) {
        ArrayList<Integer> arr = new ArrayList<>();

        int[] moneysType = {1, 2, 5, 10, 20, 50, 100};

        while (maxTypes-- > 0) {

            int currentMoney = moneysType[NumberOperation.getRandomNumber(moneysType.length)];
            int times = NumberOperation.getRandomNumber(maxNum);
            for (int i = 0; i < times; i++) {
                arr.add(currentMoney);
            }
        }
        return arr.stream().mapToInt(i -> i).toArray();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {
            int[] arr = creatArray(5, 7);
            int aim = NumberOperation.getRandomNumber(100);

            int result_1 = coinsWay1(arr, aim);
            int result_2 = coinsWay2(arr, aim);
            if (result_1 != result_2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("aim: " + aim);
                System.out.println("result_1: " + result_1 + "      result_2: " + result_2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
