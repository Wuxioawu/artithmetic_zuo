package Code_03;

import tools.Constants;
import tools.NumberOperation;

import java.util.Objects;
import java.util.Stack;

/**
 * Implement a special stack that supports push(), pop(),
 * and getMin() — all in O(1) time.
 */
public class Code05_GetMinStack {

    public static class MyStackMinValue_1 {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MyStackMinValue_1() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int value) {
            stack.push(value);
            if (minStack.isEmpty() || value <= minStack.peek()) {
                minStack.push(value);
            }
        }

        public int pop() {
            if (stack.isEmpty()) return Constants.UN_INVALID;

            Integer popValue = stack.pop();
            if (!minStack.isEmpty() && Objects.equals(popValue, minStack.peek())) {
                minStack.pop();
            }
            return popValue;
        }

        public int getMin() {
            return !minStack.isEmpty() ? minStack.peek() : Constants.NOT_FIND;
        }
    }

    public static class MyStackMinValue_2 {
        Stack<Integer> stack;

        public MyStackMinValue_2() {
            stack = new Stack<>();
        }

        public void push(int value) {
            stack.push(value);
        }

        public int pop() {
            if (stack.isEmpty()) return Constants.UN_INVALID;
            return stack.pop();
        }

        public int getMinValue() {
            if (stack.isEmpty()) return Constants.NOT_FIND;
            int minValue = Constants.UN_INVALID;
            for (int i = stack.size() - 1; i >= 0; i--) {
                if (minValue >= stack.get(i)) minValue = stack.get(i);
            }
            return minValue;
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {

            MyStackMinValue_1 stack1 = new MyStackMinValue_1();
            MyStackMinValue_2 stack2 = new MyStackMinValue_2();

            int randomNum = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);
            int operation = (int) (Math.random() * 3);

            switch (operation) {
                case 0: {
                    stack1.push(randomNum);
                    stack2.push(randomNum);
                    break;
                }
                case 1: {
                    int result1 = stack1.pop();
                    int result2 = stack2.pop();
                    if (result1 != result2) {
                        System.out.println("result1=" + result1 + " result2=" + result2);
                        System.out.println(Constants.CODE_ERROR);
                        return;
                    }
                }
                case 2: {
                    int minValue1 = stack1.getMin();
                    int minValue2 = stack2.getMinValue();
                    if (minValue1 != minValue2) {
                        System.out.println("minValue1=" + minValue1 + " minValue2=" + minValue2);
                        System.out.println(Constants.CODE_ERROR);
                        return;
                    }
                }
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
