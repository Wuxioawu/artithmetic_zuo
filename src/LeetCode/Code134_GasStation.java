package LeetCode;


public class Code134_GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int total_gas = 0;
        int current_gas = 0;

        for (int i = 0; i < gas.length; i++) {
            int distance_gas = gas[i] - cost[i];
            total_gas += distance_gas;
            current_gas += distance_gas;

            if (current_gas < 0) {
                current_gas = 0;
                start = i + 1;
            }
        }
        return total_gas < 0 ? -1 : start;
    }

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};

        int i = canCompleteCircuit(gas, cost);
        System.out.println(i);
    }
}
