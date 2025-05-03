package Code_03;

import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class Code03_DoubleEndQueueToStackAndQueue {

    public static class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> last;

        public Node(T data) {
            this.data = data;
        }
    }

    public static class DoubleEndQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T data) {
            if (data == null) {
                return;
            }

            Node<T> node = new Node<>(data);
            if (head == null) {
                head = tail = node;
                return;
            }
            node.next = head;
            head.last = node;
            head = node;
        }

        public void addFromTail(T data) {
            if (data == null) {
                return;
            }

            Node<T> node = new Node<>(data);
            if (tail == null) {
                tail = head = node;
            }
            node.last = tail;
            tail.next = node;
            tail = node;
        }

        public T popFromHead() {
            if (isEmpty()) {
                return null;
            }
            Node<T> value = head;
            head = head.next;
            head.last = null;

            value.next = null;
            value.last = null;
            return value.data;
        }

        public T popFromTail() {
            if (isEmpty()) {
                return null;
            }
            Node<T> value = tail;
            tail = tail.last;
            tail.next = null;

            value.last = null;
            value.next = null;
            return value.data;
        }

        public boolean isEmpty() {
            return head == null && tail == null;
        }
    }

    public static class MyQueue<T> {
        private final DoubleEndQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndQueue<>();
        }

        public void push(T data) {
            queue.addFromHead(data);
        }

        public T poll() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static class MyStack<T> {
        private final DoubleEndQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndQueue<>();
        }

        public void push(T data) {
            queue.addFromHead(data);
        }

        public T pop() {
            return queue.popFromTail();

        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static void testDoubleEndQueue() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            DoubleEndQueue<Integer> queue = new DoubleEndQueue<>();
            LinkedList<Integer> list = new LinkedList<>();

            int operation = (int)(Math.random() * 5);
            int randomValue = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);
            switch (operation) {
                case 0: {
                    queue.addFromHead(randomValue);
                    list.addFirst(randomValue);
                    break;
                }
                case 1: {
                    queue.addFromTail(randomValue);
                    list.addLast(randomValue);
                    break;
                }
                case 2: {
                    Integer resultQueue = queue.popFromHead();
                    Integer resultList = list.isEmpty() ? null : list.getFirst();
                    if (!Objects.equals(resultQueue, resultList)) {
                        System.out.println("The DoubleEnd queue popFromHead is wrong");
                        return;
                    }
                    break;
                }
                case 3: {
                    Integer resultQueue = queue.popFromTail();
                    Integer resultList = list.isEmpty() ? null : list.getLast();
                    if (!Objects.equals(resultQueue, resultList)) {
                        System.out.println("The DoubleEnd queue popFromTail is wrong");
                        return;
                    }
                    break;
                }
                case 4: {
                    if (queue.isEmpty() != list.isEmpty()) {
                        System.out.println("The DoubleEnd queue isEmpty is wrong");
                        return;
                    }
                    break;
                }
            }
        }
        System.out.println(Constants.NICE);
    }

    public static void testMyQueue() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            MyQueue<Integer> queue = new MyQueue<>();
            LinkedList<Integer> list = new LinkedList<>();
            int operation = (int)(Math.random() * 3);
            int randomValue = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);
            switch (operation) {
                case 0: {
                    queue.push(randomValue);
                    list.addFirst(randomValue);
                    break;
                }
                case 1: {
                    Integer valueQueue = queue.poll();
                    Integer valueList = list.isEmpty() ? null : list.getFirst();
                    if (!Objects.equals(valueQueue, valueList)) {
                        System.out.println("The MyQueue poll is wrong");
                        return;
                    }
                    break;
                }
                case 2: {
                    if (queue.isEmpty() != list.isEmpty()) {
                        System.out.println("The MyQueue isEmpty is wrong");
                        return;
                    }
                    break;
                }
            }
        }
        System.out.println(Constants.NICE);
    }

    public static void testMyStack() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            MyStack<Integer> stack = new MyStack<>();
            Stack<Integer> list = new Stack<>();
            int operation = (int)(Math.random() * 3);
            int randomValue = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);
            switch (operation) {
                case 0: {
                    stack.push(randomValue);
                    list.push(randomValue);
                    break;
                }
                case 1: {
                    Integer valueStack = stack.pop();
                    Integer valueList = list.isEmpty()? null : list.pop();
                    if (!Objects.equals(valueStack, valueList)) {
                        System.out.println("The MyStack poll is wrong");
                        return;
                    }
                    break;
                }
                case 2: {
                    if (stack.isEmpty() != list.isEmpty()) {
                        System.out.println("The MyStack isEmpty is wrong");
                        return;
                    }
                    break;
                }
            }
        }
        System.out.println(Constants.NICE);
    }

    public static void main(String[] args) {
//        testDoubleEndQueue();
//        testMyQueue();
        testMyStack();
    }
}
