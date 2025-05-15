package Code_07;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.*;

/**
 * emotion: I want to change this world.
 */

public class Code02_EveryStepShowBoss {
    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;

        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }

        @Override
        public String toString() {

            return "{id =" + id +
                    " buy = " + buy +
                    ", enterTime=" + enterTime + "} ";

//            return "Customer{" +
//                    "id=" + id +
//                    ", buy=" + buy +
//                    ", enterTime=" + enterTime +
//                    ", isRewarding=" + isRewarding +
//                    '}';
        }
    }

    public static class TargetOperation {
        private final HashMap<Integer, Customer> customerMap;
        private final HeapGreater<Customer> witting;
        private final HeapGreater<Customer> rewarding;

        private final int limit;

        public TargetOperation(int limit) {
            customerMap = new HashMap<>();
            witting = new HeapGreater<>((o1, o2) -> o2.buy - o1.buy == 0 ? o1.enterTime - o2.enterTime : o2.buy - o1.buy);
            rewarding = new HeapGreater<>((o1, o2) -> o2.buy - o1.buy == 0 ? o2.enterTime - o1.enterTime : o2.buy - o1.buy);
            this.limit = limit;
        }

        public void operate(int id, boolean isBuy, int eventTime) {
            if (!customerMap.containsKey(id) && !isBuy) {
                return;
            }

            boolean isDeleted = false;
            Customer customer;

            if (customerMap.containsKey(id)) {
                customer = customerMap.get(id);
                if (isBuy) {
                    customer.buy++;
                    customer.enterTime = eventTime;
                } else {
                    customer.buy--;
                    customer.enterTime = eventTime;
                    if (customer.buy == 0) {
                        customerMap.remove(id);
                        isDeleted = true;
                    }
                }
            } else {
                //is not contain in evenTimeMap, and isBuy
                customer = new Customer(id, 1, eventTime);
                customerMap.put(id, customer);
            }
            // adjust from waiting list to rewarding list

            if (!witting.contains(customer) && !rewarding.contains(customer)) {
                witting.push(customer);
            } else {
                if (isDeleted) {
                    if (rewarding.contains(customer)) {
                        rewarding.remove(customer);
                    } else {
                        witting.remove(customer);
                    }
                }
            }
            adjustRewarding(eventTime);
        }

        // according to the rules to modify the rewarding list and
        private void adjustRewarding(int eventTime) {
            if (witting.isEmpty()) {
                return;
            }

            if (rewarding.size() < limit) {
                Customer pop = witting.pop();
                pop.enterTime = eventTime;
                rewarding.push(pop);
                return;
            }

            Customer rewardingPeek = rewarding.peek();
            Customer wittingPeek = witting.peek();

            if (rewardingPeek.buy <= wittingPeek.buy) {
                rewardingPeek.enterTime = eventTime;
                wittingPeek.enterTime = eventTime;

                rewarding.pop();
                rewarding.push(wittingPeek);

                witting.pop();
                witting.push(rewardingPeek);
            }
        }

        public List<Customer> getRewadingList() {
            List<Customer> resultList = new ArrayList<>();
            List<Customer> rewadingList = rewarding.getAllElements();
            for (Customer customer : rewadingList) {
                Customer newCustomer = new Customer(customer.id, customer.buy, customer.enterTime);
                resultList.add(newCustomer);
            }
            return resultList;
        }
    }

    public static List<List<Customer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Customer>> result = new ArrayList<>();
        TargetOperation targetOperation = new TargetOperation(k);

        for (int i = 0; i < arr.length; i++) {
            targetOperation.operate(arr[i], op[i], i);
            result.add(targetOperation.getRewadingList());
        }
        return result;
    }

    /**
     * in the reward rang, first compare the buy times, and then compare their eventTime later
     * in the witting rang, first compare the buy time, and then put the item with eventTime early
     * <p>
     * deal with the invalided event
     * for any item, it should be stayed in reward range or witting range.
     * <p>
     * need to change the event time, when change the position.
     * need to delete one which buy == 0,
     */

    public static List<List<Customer>> simulation(int[] arr, boolean[] op, int k) {

        ArrayList<Customer> rewardList = new ArrayList<>();
        ArrayList<Customer> waitingList = new ArrayList<>();
        HashMap<Integer, Customer> buyTimeMap = new HashMap<>();

        List<List<Customer>> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            if (!op[i] && !buyTimeMap.containsKey(arr[i])) {
                //invalided
                ArrayList<Customer> currentResult = copyList(rewardList);
                result.add(currentResult);
                continue;
            }

            Customer customer;
            boolean deleted = false;

            if (!buyTimeMap.containsKey(arr[i]) && op[i]) {
                // directly into the waiting list
                customer = new Customer(arr[i], 1, i);
                buyTimeMap.put(arr[i], customer);
            } else {
                customer = buyTimeMap.get(arr[i]);
                if (op[i]) {
                    customer.buy++;
                    customer.enterTime = i;
                } else {
                    if (customer.buy == 1) {
                        buyTimeMap.remove(customer.id);
                        deleted = true;
                    } else {
                        customer.buy--;
                        customer.enterTime = i;
                    }
                }
            }

            // adjust waiting list

            if (!waitingList.contains(customer) && !rewardList.contains(customer) && !deleted) {
                waitingList.add(customer);
            } else {
                //deleted one element
                if (deleted) {
                    if (rewardList.contains(customer)) {
                        rewardList.remove(customer);
                    } else {
                        waitingList.remove(customer);
                    }
                }
            }
            changePosition(waitingList, rewardList, k, i);

            // add the current rewarding list
            ArrayList<Customer> currentResult = copyList(rewardList);
            result.add(currentResult);
        }


        return result;
    }

    private static ArrayList<Customer> copyList(ArrayList<Customer> list) {
        ArrayList<Customer> result = new ArrayList<>();
        for (Customer customer : list) {
            Customer newCustomer = new Customer(customer.id, customer.buy, customer.enterTime);
            result.add(newCustomer);
        }
        return result;
    }

    public static void changePosition(List<Customer> waittingList, List<Customer> rewardingList, int k, int eventTime) {

        if (rewardingList.size() < k) {
            Customer rightFromWitting = getRightFromWitting(waittingList);
            if (rightFromWitting == null) return;
            rightFromWitting.enterTime = eventTime;
            rewardingList.add(rightFromWitting);
            waittingList.remove(rightFromWitting);
            return;
        }

        Customer waitingTarget = getRightFromWitting(waittingList);
        if (waitingTarget == null) return;

        Customer rewardingTarget = getRightFromRewarding(rewardingList);

        if (rewardingTarget.buy > waitingTarget.buy) {
            return;
        }

        int indexReward = rewardingList.indexOf(rewardingTarget);
        waitingTarget.enterTime = eventTime;
        rewardingList.set(indexReward, waitingTarget);
        int indexWaiting = waittingList.indexOf(waitingTarget);
        rewardingTarget.enterTime = eventTime;
        waittingList.set(indexWaiting, rewardingTarget);

    }

    public static Customer findTheEarly(List<Customer> rewardList, int buyTime) {
        Customer result = null;
        int index = 0;

        do {
            result = rewardList.get(index);
        } while (!(rewardList.get(index++).buy == buyTime));

        for (int i = 0; i < rewardList.size(); i++) {
            result = rewardList.get(i).buy == buyTime && result.enterTime < rewardList.get(i).enterTime
                    ? rewardList.get(i) : result;
        }
        return result;
    }

    // get the current right element int waiting list
    public static Customer getRightFromWitting(List<Customer> list) {
        if (list == null || list.isEmpty()) return null;
        Customer rightCustomer = list.getFirst();
        Comparator<Customer> comparator = (o1, o2) -> o2.buy - o1.buy == 0 ? o1.enterTime - o2.enterTime : o2.buy - o1.buy;

        for (int i = 1; i < list.size(); i++) {
            int compare = comparator.compare(rightCustomer, list.get(i));
            if (compare < 0) {
                rightCustomer = list.get(i);
            }
        }
        return rightCustomer;
    }

    public static Customer getRightFromRewarding(List<Customer> list) {
        Customer rightCustom = list.getFirst();
        Comparator<Customer> comparator = (o1, o2) -> o1.buy - o2.buy == 0 ? o1.enterTime - o2.enterTime : o1.buy - o2.buy;

        for (int i = 1; i < list.size(); i++) {
            int compare = comparator.compare(rightCustom, list.get(i));
            if (compare > 0) {
                rightCustom = list.get(i);
            }
        }
        return rightCustom;
    }

    // for test
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] len, boolean[] op) {
            this.arr = len;
            this.op = op;
        }

        public void print() {
            for (int j : arr) {
                System.out.print(j + " ");
            }
            System.out.println();

            for (boolean b : op) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

    }

    public static Data getRandomData(int maxUserNum, int maxEventTimes) {

        int[] randomArray = ArrayOperation.getRandomArray(maxUserNum, maxEventTimes);

        assert randomArray != null;
        boolean[] op = new boolean[randomArray.length];

        for (int i = 0; i < op.length; i++) {
            op[i] = NumberOperation.isRandomGreaterThanValue(0.7d);
        }

        return new Data(randomArray, op);
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1000; i++) {

            Data testData = new Data(new int[]{9, 3, 10, 5, 10, 2, 9, 8, 8},
                    new boolean[]{true, true, true, false, true, true, true, true, true});

            Data randomData = testData;
            getRandomData(10, 10);
            int k = 4;
            NumberOperation.getRandomNumber(10);

            // the reward list every eventTime
            List<List<Customer>> simulation = simulation(randomData.arr, randomData.op, k);

            List<List<Customer>> topK = topK(randomData.arr, randomData.op, k);
            if (!equalList(simulation, topK)) {
                System.out.println(Constants.CODE_ERROR);
                randomData.print();
                printRewardList(simulation);
                printRewardList(topK);
                return;
            }

        }
        System.out.println(Constants.TEST_FINISH);
    }

    private static boolean equalList(List<List<Customer>> list1, List<List<Customer>> list2) {
        if (list1 == null && list2 == null) return true;
        if (list1 == null || list2 == null) return false;
        if (list1.size() != list2.size()) return false;

        Comparator<Customer> comparator = (o1, o2) -> o1.id - o2.id == 0 ? o2.enterTime - o1.enterTime : o1.id - o2.id;

        for (int i = 0; i < list1.size(); i++) {
            List<Customer> customerList1 = list1.get(i);
            List<Customer> customerList2 = list2.get(i);

            customerList1.sort(comparator);
            customerList2.sort(comparator);

            for (int j = 0; j < customerList1.size(); j++) {
                Customer customer1 = customerList1.get(j);
                Customer customer2 = customerList2.get(j);

                if (!isEqualCustom(customer1, customer2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isEqualCustom(Customer customer1, Customer customer2) {
        if (customer1 == null && customer2 == null) return true;
        if (customer1 == null || customer2 == null) return false;
        if (customer1.id == customer2.id &&
                customer1.buy == customer2.buy &&
                customer1.enterTime == customer2.enterTime) {
            return true;
        }
        return false;
    }

    private static void printRewardList(List<List<Customer>> list) {
        System.out.println("---------------------------------------");
        for (List<Customer> customers : list) {
            for (Customer customer : customers) {
                System.out.print(customer);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("---------------------------------------");
    }

    public static void main(String[] args) {
        test();
    }
}




















