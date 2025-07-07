package Code_15;

import tools.Constants;
import tools.NumberOperation;

import java.util.Stack;

public class Code03_ReverseStackUsingRecursive {

    private static void reserveStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int element = f(stack);
        reserveStack(stack);
        stack.push(element);
    }

    private static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.empty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    private static void addRandomElement(Stack<Integer> stack, int maxSize, int maxValue) {
        int size = NumberOperation.getRandomNumber(maxSize);
        for (int i = 0; i < size; i++) {
            int value = NumberOperation.getRandomNumber(maxValue);
            stack.push(value);
        }
    }

    private static boolean checkSameStack(Stack<Integer> stack1, Stack<Integer> stack2) {
        if (stack1.isEmpty() && stack2.isEmpty()) return true;
        if (stack1.isEmpty() || stack2.isEmpty()) return false;
        if (stack1.size() != stack2.size()) return false;

        for (int i = 0; i < stack1.size(); i++) {
            if (stack1.get(i) != stack2.get(i)) return false;
        }
        return true;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            Stack<Integer> stack = new Stack<>();
            addRandomElement(stack, 10, 10);

            Stack<Integer> reverseStack = new Stack<>();
            reverseStack.addAll(stack);

            reserveStack(stack);
            reserveStack(stack);

            if (!checkSameStack(stack, reverseStack)) {
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
