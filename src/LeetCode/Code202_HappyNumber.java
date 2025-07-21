package LeetCode;

import java.util.HashSet;
public class Code202_HappyNumber {

    public static boolean isHappy(int n) {

        HashSet<Integer> visited = new HashSet<>();

        while (!visited.contains(n)) {
            visited.add(n);
            n = calculate(n);
            if (n == 1) return true;
        }

        return false;
    }

    private static int calculate(int n) {
        int result = 0;
        while (n != 0) {
            int lastNum = n % 10;
            result += lastNum * lastNum;
            n /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(isHappy(n));
    }
}
