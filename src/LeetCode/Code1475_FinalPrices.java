package LeetCode;


import java.util.Arrays;
import java.util.Stack;

public class Code1475_FinalPrices {
    public static int[] finalPrices(int[] prices) {
        if (prices == null || prices.length == 0) return new int[0];
        int[] stack = new int[prices.length];
        int top = -1;

        int[] result = new int[prices.length];
        int index = 0;
        for (int i = 0; i < prices.length; i++) {
            if (top == -1) {
                stack[++top] = prices[i];
                continue;
            }

            while (top != -1 && prices[i] <= stack[top]) {
                result[i - 1] = stack[top--] - prices[i];
            }

            stack[++top] = prices[i];
        }
        int temp = 0;
        while (temp <= top) {
            result[index++] = stack[temp++];
        }

        return result;
    }

    public static int[] finalPrices2(int[] prices) {
        if (prices == null || prices.length == 0) return new int[0];
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[prices.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < prices.length; i++) {
            if (!stack.isEmpty()) {
                Stack<Integer> tempStack = new Stack<>();
                int step = 0;
                while (!stack.isEmpty() && prices[i] <= stack.peek()) {
                    tempStack.push(stack.pop() - prices[i]);
                    step++;
                }
                int start = i - step;
                while (!tempStack.isEmpty()) {
                    result[start++] = tempStack.pop();
                }
            }
            stack.push(prices[i]);
        }

        for (int i = result.length - 1; i >= 0 && !stack.isEmpty(); i--) {
            if (result[i] == -1) {
                result[i] = stack.pop();
            }
        }

        return result;
    }

    public static int[] finalPrices3(int[] prices) {
        if (prices == null || prices.length == 0) return new int[0];
        int[] result = new int[prices.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                }
            }
        }

        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] == -1) {
                result[i] = prices[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] prices = {8, 4, 6, 2, 3};
        int[] result = finalPrices3(prices);
        //[3,6,1,5,0,0,4,5,4]
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
