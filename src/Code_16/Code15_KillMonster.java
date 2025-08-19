package Code_16;

import tools.Constants;
import tools.NumberOperation;

// 枚举优化
public class Code15_KillMonster {

    private static double killRate1(int originalBlood, int looseBlood, int times) {
        if (originalBlood == 0 || looseBlood == 0 || times == 0) {
            return 0;
        }
        return process(originalBlood, looseBlood, times) / Math.pow(looseBlood + 1, times);
    }

    private static long process(int restBlood, int looseBlood, int restTimes) {
        if (restTimes == 0) {
            return restBlood <= 0 ? 1 : 0;
        }
        if (restBlood <= 0) {
            return (long) Math.pow(looseBlood + 1, restTimes);
        }
        long ways = 0;
        for (int i = 0; i <= looseBlood; i++) {
            ways += process(restBlood - i, looseBlood, restTimes - 1);
        }
        return ways;
    }

    private static double killRate2(int originalBlood, int looseBlood, int times) {
        if (originalBlood == 0 || looseBlood == 0 || times == 0) {
            return 0;
        }

        long[][] dp = new long[originalBlood + 1][times + 1];

        dp[0][0] = 1;
        for (int i = 1; i < times + 1; i++) {
            dp[0][i] = (long) Math.pow(looseBlood + 1, i);
        }

        for (int restBlood = 1; restBlood <= originalBlood; restBlood++) {
            for (int restTime = 1; restTime <= times; restTime++) {
                long ways = 0;
                for (int i = 0; i <= looseBlood; i++) {
                    if (restBlood - i >= 0) {
                        ways += dp[restBlood - i][restTime - 1];
                    } else {
                        ways += (long) Math.pow(looseBlood + 1, restTime - 1);
                    }
                }
                dp[restBlood][restTime] = ways;
            }
        }
        return dp[originalBlood][times] / Math.pow(looseBlood + 1, times);
    }

    private static double killRate3(int originalBlood, int looseBlood, int times) {
        if (originalBlood == 0 || looseBlood == 0 || times == 0) {
            return 0;
        }

        long[][] dp = new long[originalBlood + 1][times + 1];

        dp[0][0] = 1;
        for (int i = 1; i < times + 1; i++) {
            dp[0][i] = (long) Math.pow(looseBlood + 1, i);
        }

        for (int restBlood = 1; restBlood <= originalBlood; restBlood++) {
            for (int restTime = 1; restTime <= times; restTime++) {

                long ways = dp[restBlood - 1][restTime] + dp[restBlood][restTime - 1];
                if (restBlood - looseBlood - 1 >= 0) {
                    ways -= dp[restBlood - 1 - looseBlood][restTime - 1];
                } else {
                    ways -= (long) Math.pow(looseBlood + 1, restTime - 1);
                }
                dp[restBlood][restTime] = ways;
            }
        }

        return dp[originalBlood][times] / Math.pow(looseBlood + 1, times);
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {
            int blood = 8;
            NumberOperation.getRandomNumber(30);
            int looseBlood = 4;
            NumberOperation.getRandomNumber(30);
            int times = 4;
            NumberOperation.getRandomNumber(10);

            double result_1 = killRate2(blood, looseBlood, times);
            double result_2 = killRate3(blood, looseBlood, times);

            if (result_1 != result_2) {
                System.out.println("blood: " + blood + " looseBlood: " + looseBlood + " times: " + times);
                System.out.println("result_1: " + result_1 + " result_2: " + result_2);
                return;
            }
        }

        System.out.println(Constants.TEST_FINISH);
    }


    public static void main(String[] args) {
        test();
    }
}
