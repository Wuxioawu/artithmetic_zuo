package Code_11;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.List;

public class Code09_MaxHappy {

    static class Employee {
        public int happy;
        List<Employee> subordinates;

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }
    }

    static class Info {
        int come_happy;
        int no_come_happy;
        int max_happy;

        public Info(int come_happy, int no_come_happy, int max_happy) {
            this.come_happy = come_happy;
            this.no_come_happy = no_come_happy;
            this.max_happy = max_happy;
        }
    }

    public static int getMaxHappy1(Employee root) {
        if (root == null) return 0;
        return processGetMaxHappy(root).max_happy;
    }

    private static Info processGetMaxHappy(Employee root) {
        if (root == null) return new Info(0, 0, 0);

        int come_happy = root.happy;
        int no_come_happy = 0;

        for (Employee employee : root.subordinates) {
            Info nexInfo = processGetMaxHappy(employee);
            come_happy += nexInfo.no_come_happy;
            no_come_happy += Math.max(nexInfo.come_happy, nexInfo.no_come_happy);
        }

        int max_happy = Math.max(come_happy, no_come_happy);
        return new Info(come_happy, no_come_happy, max_happy);
    }

    public static int getMaxHappy2(Employee root) {
        if (root == null) return 0;
        return processGetMaxHappy2(root, false);
    }

    private static int processGetMaxHappy2(Employee root, boolean up) {
        if (root == null) return 0;
        if (up) {
            int ans = 0;
            for (Employee employee : root.subordinates) {
                ans += processGetMaxHappy2(employee, false);
            }
            return ans;
        } else {
            int p1 = root.happy;
            int p2 = 0;
            for (Employee employee : root.subordinates) {
                p1 += processGetMaxHappy2(employee, true);
                p2 += processGetMaxHappy2(employee, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    private static Employee getRandomEmployees(int maxDepth, int maxValue, int maxSize) {
        int depth = NumberOperation.getRandomNumber(maxDepth);
        return process(1, depth, maxValue, maxSize);
    }

    private static Employee process(int currentDepth, int maxDepth, int maxValue, int maxSize) {
        if (currentDepth > maxDepth) {
            return null;
        }
        int value = NumberOperation.getRandomNumber(maxValue);
        ArrayList<Employee> subordinates = new ArrayList<>();

        int size = NumberOperation.getRandomNumber(maxSize);
        for (int i = 0; i < size; i++) {
            subordinates.add(process(currentDepth + 1, maxDepth, maxValue, maxSize));
        }
        return new Employee(value, subordinates);
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            Employee root = getRandomEmployees(5, 100, 10);
            int maxHappy = getMaxHappy1(root);
            int maxHappyByBruteForce = getMaxHappy2(root);
            System.out.println("the test time is : " + i);
            if (maxHappy != maxHappyByBruteForce) {
                System.out.println(Constants.CODE_ERROR);
                System.out.println(maxHappy);
                System.out.println(maxHappyByBruteForce);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
