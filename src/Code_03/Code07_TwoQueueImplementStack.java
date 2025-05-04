package Code_03;

import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack {

    public static class MyStack {
        Queue<Integer> queue;
        Queue<Integer> tempQueue;

        public MyStack() {
            queue = new LinkedList<>();
            tempQueue = new LinkedList<>();
        }

        public void push(int x) {
            queue.add(x);
        }

        public int pop() {
            if (queue.isEmpty()) return Constants.UN_INVALID;

            for (int i = queue.size() - 2; i >= 0; i--) {
                tempQueue.add(queue.remove());
            }
            int value = queue.remove();
            for (int i = tempQueue.size() - 1; i >= 0; i--) {
                queue.add(tempQueue.remove());
            }
            return value;
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_MAX_VALUE; i++) {
            int randomNum = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);

            MyStack myStack = new MyStack();
            Stack<Integer> apiStack = new Stack<>();

            int operation = (int) (Math.random() * 2);

            switch (operation) {
                case 0: {
                    myStack.push(randomNum);
                    apiStack.push(randomNum);
                    break;
                }
                case 1: {
                    if (!apiStack.isEmpty() && (myStack.pop() != apiStack.pop())) {
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
