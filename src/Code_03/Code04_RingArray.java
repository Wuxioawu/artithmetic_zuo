package Code_03;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;

public class Code04_RingArray {

    public static class MyQueue {
        int[] array;
        int size;
        int head;
        int tail;

        public MyQueue(int limit) {
            array = new int[limit];
            size = 0;
            head = 0;
        }

        public boolean push(int number) {
            if (isFull()) return false;
            size++;
            array[head] = number;
            head = getNextIndex(head);
            return true;
        }

        public int poll() {
            if (isEmpty()) return Constants.UN_INVALID;
            int value = array[tail];
            tail = getNextIndex(tail);
            size--;
            return value;
        }

        private int getNextIndex(int i) {
            return i == array.length - 1 ? 0 : i + 1;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }

        public int getSize() {
            return size;
        }
    }

    public static class MyStack {
        int[] array;
        int size;
        int pointer;

        public MyStack(int limit) {
            array = new int[limit];
            size = 0;
            pointer = -1;
        }

        public void push(int number) {
            if (isFull()) return;
            array[++pointer] = number;
            size++;
        }

        public int pop() {
            if (isEmpty()) return Constants.UN_INVALID;
            size--;
            return array[pointer--];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }
        public int getSize() {
            return size;
        }
    }

    public static void testMyQueue() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int randomNum = NumberOperation.getRandomOddNumber(Constants.TEST_MAX_VALUE);
            int size = 0;
            do {
                size = NumberOperation.getRandomNumber(Constants.TEST_MAX_SIZE);
            } while (size == 0);

            MyQueue myQueue = new MyQueue(size);
            ArrayBlockingQueue<Integer> apiQueue = new ArrayBlockingQueue<>(size);

            int operation = (int)(Math.random() * 4);
            switch (operation) {
                case 0: {
                    if (myQueue.push(randomNum) != apiQueue.offer(randomNum)) {
                        System.out.println("myQueue push is wrong");
                        return;
                    }
                    break;
                }
                case 1: {
                    if(!apiQueue.isEmpty() && myQueue.poll() != apiQueue.poll()) {
                        System.out.println("myQueue poll is wrong");
                        return;
                    }
                    break;
                }
                case 2: {
                    if (myQueue.isEmpty() != apiQueue.isEmpty()) {
                        System.out.println("myQueue isEmpty is wrong!");
                        return;
                    }
                    break;
                }
                case 3: {
                    if (myQueue.getSize() != apiQueue.size()) {
                        System.out.println("myQueue getSize is wrong!");
                        return;
                    }
                }
            }
        }
        System.out.println(Constants.NICE);
    }

    public static void testMyStack() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int randomNum = NumberOperation.getRandomOddNumber(Constants.TEST_MAX_VALUE);
            int size = 0;
            do {
                size = NumberOperation.getRandomNumber(Constants.TEST_MAX_SIZE);
            } while (size == 0);

            MyStack myStack = new MyStack(size);
            ArrayDeque<Integer> apiStack = new ArrayDeque<>(size);

            int operation = (int)(Math.random() * 4);
            switch (operation) {
                case 0: {
                    myStack.push(randomNum);
                    apiStack.push(randomNum);
                    break;
                }
                case 1: {
                    if(!apiStack.isEmpty() && (myStack.pop() != apiStack.pop())) {
                        System.out.println("myStack pop is wrong");
                        return;
                    }
                   break;
                }
                case 2: {
                    if(myStack.isEmpty() != apiStack.isEmpty()) {
                        System.out.println("myStack is empty");
                        return;
                    }
                    break;
                }
                case 3: {
                    if (myStack.getSize() != apiStack.size()) {
                        System.out.println("myStack getSize is wrong");
                        return;
                    }
                    break;
                }
            }
        }
        System.out.println(Constants.NICE);
    }

    public static void main(String[] args) {
//        testMyQueue();
        testMyStack();
    }

}
