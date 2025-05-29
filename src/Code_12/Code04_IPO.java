package Code_12;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code04_IPO {

    private static class Program {
        int cost;
        int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    private static Program[] createPrograms(int[] costs, int[] profits) {
        if (costs == null || costs.length == 0 || profits == null || profits.length == 0)
            return null;
        Program[] programs = new Program[costs.length];
        for (int i = 0; i < costs.length; i++) {
            programs[i] = new Program(costs[i], profits[i]);
        }
        return programs;
    }

    private static int getTheMaxMoney(int[] cost, int[] profits, int K, int M) {
        Program[] programs = createPrograms(cost, profits);
        if (programs == null) return M;

        var costPriorityQueue = new PriorityQueue<Program>((o1, o2) -> o1.cost - o2.cost);
        var profitPriorityQueue = new PriorityQueue<Program>((o1, o2) -> o2.profit - o1.profit);

        int maxMoney = M;
        costPriorityQueue.addAll(Arrays.asList(programs));

        if (!costPriorityQueue.isEmpty() && costPriorityQueue.peek().cost > M) {
            return maxMoney;
        }

        for (int i = 0; i < K; i++) {
            while (costPriorityQueue.peek() != null && costPriorityQueue.peek().cost <= maxMoney) {
                Program poll = costPriorityQueue.poll();
                profitPriorityQueue.add(poll);
                if (costPriorityQueue.isEmpty()) break;
            }
            maxMoney += profitPriorityQueue.isEmpty() ? 0 : profitPriorityQueue.poll().profit;
        }
        return maxMoney;
    }

    private static int getTheMaxMoneyBruteForce(int[] cost, int[] profits, int K, int M) {
        Program[] programs = createPrograms(cost, profits);

        return process(programs, 0, K, M);
    }

    private static int process(Program[] programs, int currentK, int K, int currentM) {
        if (programs != null && programs.length == 0 || currentK >= K) return currentM;

        int maxMoney = currentM;
        for (int i = 0; programs != null && i < programs.length; i++) {
            if (currentM >= programs[i].cost) {
                Program[] remove_i = removeProgramsByIndex(programs, i);
                maxMoney = Math.max(maxMoney, process(remove_i, currentK + 1, K, currentM + programs[i].profit));
            }
        }
        return maxMoney;
    }

    private static Program[] removeProgramsByIndex(Program[] programs, int index) {
        Program[] newPrograms = new Program[programs.length - 1];
        if (index > 0) {
            System.arraycopy(programs, 0, newPrograms, 0, index);
        }
        if (index < programs.length - 1) {
            System.arraycopy(programs, index + 1, newPrograms, index, programs.length - index - 1);
        }
        return newPrograms;
    }

    // for test
    private static int[] getRandomArray(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = NumberOperation.getRandomNumber(maxValue);
        }
        return arr;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 1; i <= Constants.TEST_TIMES; i++) {
            System.out.println("the test time is " + i);
            int[] costs = ArrayOperation.getRandomArray(10, 10);
            int[] profits = getRandomArray(costs.length, 10);
            int k = NumberOperation.getRandomNumber(costs.length);
            int M = NumberOperation.getRandomNumber(10);

            int theMaxMoney = getTheMaxMoney(costs, profits, k, M);
            int theMaxMoneyByBruteForce = getTheMaxMoneyBruteForce(costs, profits, k, M);


            if (theMaxMoney != theMaxMoneyByBruteForce) {
                System.out.println("K: " + k + " M :" + M);
                System.out.print("cost   : ");
                ArrayOperation.printArray(costs);
                System.out.print("profit : ");
                ArrayOperation.printArray(profits);

                System.out.println("theMaxMoney: " + theMaxMoney + ", theMaxMoneyByBruteForce: " + theMaxMoneyByBruteForce);
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
