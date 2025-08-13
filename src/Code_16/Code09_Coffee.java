package Code_16;

import java.util.Comparator;
import java.util.PriorityQueue;

//直接考虑最差情况，直接冲到最大范围
public class Code09_Coffee {

    static class Machines {
        int currentTime;
        int workTime;

        public Machines(int currentTime, int workTime) {
            this.currentTime = currentTime;
            this.workTime = workTime;
        }
    }

    /**
     * @param arr the time of coffee machine
     * @param N   how many people
     * @param a   coffee machine time
     * @param b   coffee evaporation time
     * @return
     */
    public static int minTime(int[] arr, int N, int a, int b) {
        // first get the time of every person drinked the coffee
        PriorityQueue<Machines> priorityQueue = new PriorityQueue<>(new Comparator<Machines>() {

            @Override
            public int compare(Machines o1, Machines o2) {
                return (o2.currentTime + o2.workTime) - (o1.currentTime + o1.workTime);
            }
        });

        for (int i = 0; i < arr.length; i++) {
            priorityQueue.add(new Machines(0, arr[i]));
        }

        // finish the drink coffee times.
        int[] drinks = new int[N];
        for (int i = 0; i < arr.length; i++) {
            Machines poll = priorityQueue.poll();
            poll.currentTime = poll.currentTime + poll.workTime;
            drinks[i] = poll.currentTime;
            priorityQueue.add(poll);
        }

        return bestTime1(drinks, a, b, 0, 0);
    }

    /*
        drinks: the short time which the customs finish their drinks
        wash: it's the wash cup machine need time
        air: the coffee cup evaporation time
        index: the current
     */
    private static int bestTime1(int[] drinks, int wash, int air, int index, int machinesFreeTime) {
        if (index == drinks.length) return 0;

        // use machine
        int currentTimeMachine = Math.max(drinks[index], machinesFreeTime) + wash;
        int lastTimeMachine = Math.max(bestTime1(drinks, wash, air, index + 1, currentTimeMachine), currentTimeMachine);

        // evaporation
        int currentTimeAir = drinks[index] + air;
        int lastTimeAir = Math.max(bestTime1(drinks, wash, air, index + 1, machinesFreeTime), currentTimeAir);

        return Math.min(lastTimeAir, lastTimeMachine);
    }

    private static int bestTime2(int[] drinks, int wash, int air) {
        int machinesFreeTime = drinks[0] + wash;

        // the best worst situation
        for (int i = 1; i < drinks.length; i++) {
            machinesFreeTime = Math.max(drinks[i], machinesFreeTime) + wash;
        }
        int N = drinks.length;
        // create dp
        int[][] dp = new int[N + 1][machinesFreeTime + 1];

        for (int i = N - 1; i < drinks.length; i++) {
            for (int free = 0; free < machinesFreeTime + 1; free++) {

                int currentTimeMachine = Math.max(drinks[i], free) + wash;
                if (currentTimeMachine > machinesFreeTime) {
                    break;
                }
                int lastTimeMachine = Math.max(dp[i + 1][currentTimeMachine], currentTimeMachine);

                // evaporation
                int lastTimeAir = Math.max(dp[i + 1][free], drinks[i] + air);
                dp[i][free] = Math.min(lastTimeAir, lastTimeMachine);
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {

    }
}
