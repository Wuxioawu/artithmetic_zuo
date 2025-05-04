package Code_03;

import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code06_TwoStackImplementQueue {

    public static class MyQueue {
        Stack<Integer> stack;
        Stack<Integer> tempStack;

        public MyQueue() {
            stack = new Stack<>();
            tempStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
        }

        public int poll() {
            if (!tempStack.isEmpty()) {
                return tempStack.pop();
            }

            if (stack.isEmpty()) return Constants.NOT_FIND;

            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }

            return tempStack.pop();
        }
    }


    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int randomNum = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);
            MyQueue myQueue = new MyQueue();
            Queue<Integer> apiQueue = new LinkedList<>();

            int operation = (int)(Math.random() * 2);
            switch (operation) {
                case 0: {
                    myQueue.push(randomNum);
                    apiQueue.add(randomNum);
                    break;
                }
                case 1: {
                    if (!apiQueue.isEmpty() && (myQueue.poll() == apiQueue.poll())) {
                        System.out.println(Constants.CODE_ERROR);
                        return;
                    }
                    break;
                }
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
