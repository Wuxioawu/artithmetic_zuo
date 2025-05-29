package Code_12;

import tools.ArrayOperation;
import tools.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Code03_LessMoneySplitGold {

    public static int getLessMoneySplitGold(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int j : cost) {
            queue.add(j);
        }

        int result = 0;
        while (queue.size() > 1) {
            int cur = queue.poll() + queue.poll();
            result += cur;
            queue.add(cur);
        }
        return result;
    }

    // the wrong greedy strategy
    public static int getLessMoneySplitGoldOtherWay(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        Arrays.sort(cost);
        int[] temp = new int[cost.length - 1];
        temp[0] = cost[0] + cost[1];
        for (int i = 1; i < cost.length - 1; i++) {
            temp[i] = cost[i + 1] + temp[i - 1];
        }
        int result = 0;

        for (int j : temp) {
            result += j;
        }
        return result;
    }

    private static int getLessMoneySplitGoldByBruteForce(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < cost.length; i++) {
            list.add(cost[i]);
        }
        return process(list, 0);
    }

    private static int process(List<Integer> cost, int preCost) {
        if (cost.size() == 1) return preCost;

        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < cost.size(); i++) {
            for (int j = i + 1; j < cost.size(); j++) {
                minValue = Math.min(minValue,
                        process(mergeByIndex(cost, i, j), cost.get(i) + preCost + cost.get(j)));
            }
        }
        return minValue;
    }

    private static List<Integer> mergeByIndex(List<Integer> cost, int index_i, int index_j) {
        if (cost.size() == 1) return cost;

        List<Integer> newList = new ArrayList<>(cost);
        newList.remove(index_j);
        newList.remove(index_i);

        newList.add(cost.get(index_i) + cost.get(index_j));
        return newList;
    }

    // for test
    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 100; i++) {
            System.out.println("start: the test time is : " + i);
            int[] randomArray = ArrayOperation.getRandomArray(10, 10);
            int[] copyArray = ArrayOperation.copyArray(randomArray);
            int lessMoneySplitGold = getLessMoneySplitGold(randomArray);

            int lessMoneySplitGoldByBruteForce = getLessMoneySplitGoldByBruteForce(copyArray);

            if (lessMoneySplitGold != lessMoneySplitGoldByBruteForce) {
                ArrayOperation.printArray(copyArray);
                System.out.println("lessMoneySplitGold: " + lessMoneySplitGold + " lessMoneySplitGoldByBruteForce: " + lessMoneySplitGoldByBruteForce);
                return;
            }
            System.out.println("finish: the test time is : " + i);
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
