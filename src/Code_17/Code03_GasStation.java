package Code_17;

import tools.ArrayOperation;
import tools.Constants;

import java.util.LinkedList;

public class Code03_GasStation {
    private static boolean[] canCompleteCircuit_1(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) return null;

        int[] result = new int[gas.length];
        boolean[] canArrival = new boolean[gas.length];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < gas.length; i++) {
            result[i] = gas[i] - cost[i];
            if (result[i] >= 0) {
                list.addLast(i);
            } else {
                canArrival[i] = false;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int current_result = 0;
            int current_index = list.get(i);
            int sum_index = current_index;

            for (int j = 0; j < result.length; j++) {
                current_result += result[sum_index++ % result.length];
                if (current_result < 0) {
                    canArrival[current_index] = false;
                    break;
                }
            }
            if (current_result >= 0) {
                canArrival[current_index] = true;
            }
        }
        return canArrival;
    }

    private static boolean[] canCompleteCircuit_2(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) return null;

        int N = gas.length;
        int[] cover_sum = new int[N * 2];
        int[] distance = new int[N];

        for (int i = 0; i < N; i++) {
            distance[i] = gas[i] - cost[i];
        }
        cover_sum[0] = distance[0];
        for (int i = 1; i < cover_sum.length; i++) {
            cover_sum[i] = cover_sum[i - 1] + distance[i % N];
        }

        LinkedList<Integer> minQueue = new LinkedList<>();
        boolean[] canArrival = new boolean[N];
        for (int i = 0; i < cover_sum.length; i++) {
            while (!minQueue.isEmpty() && cover_sum[i] <= cover_sum[minQueue.getLast()]) {
                minQueue.removeLast();
            }
            minQueue.addLast(i);
            if (i >= N - 1 && i - N + 1 < canArrival.length) {
                if (cover_sum[minQueue.peekFirst()] - (i - N >= 0 ? cover_sum[i - N] : 0) >= 0) {
                    canArrival[i - N + 1] = true;
                } else {
                    canArrival[i - N + 1] = false;
                }

                if (i - N + 1 == minQueue.peekFirst()) {
                    minQueue.removeFirst();
                }
            }
        }
        return canArrival;
    }

    // for test

    private static boolean checkBooleanArray(boolean[] b1, boolean[] b2) {
        if (b1 == null && b2 == null) return true;
        if (b1 == null || b2 == null) return false;
        if (b1.length != b2.length) return false;
        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != b2[i]) return false;
        }
        return true;
    }

    private static void printArray(boolean[] b1) {
        for (int i = 0; i < b1.length; i++) {
            System.out.print(b1[i] + " ");
        }
        System.out.println();
    }

    private static void text() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 10000; i++) {
            int[] gas = ArrayOperation.getRandomArray(20, 10);
            int[] cost = ArrayOperation.getArray(gas.length, 10);
            boolean[] resul_1 = canCompleteCircuit_1(gas, cost);
            boolean[] resul_2 = canCompleteCircuit_2(gas, cost);
            if (!checkBooleanArray(resul_1, resul_2)) {
                printArray(resul_1);
                printArray(resul_2);

                ArrayOperation.printArray(gas);
                ArrayOperation.printArray(cost);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        text();
    }
}
