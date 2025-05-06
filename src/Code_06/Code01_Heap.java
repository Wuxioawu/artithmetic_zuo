package Code_06;


import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.Objects;
import java.util.PriorityQueue;

public class Code01_Heap {

    public static class MyHeap {
        private final int[] nums;
        private final int capacity;
        private int size;

        public MyHeap(int capacity) {
            nums = new int[capacity];
            this.capacity = capacity;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getSize() {
            return size;
        }

        public boolean isFull() {
            return size >= capacity;
        }

        public void push(int value) {
            if (isFull()) return;
            nums[size] = value;
            heapInsert(nums, size++);
        }

        private void heapInsert(int[] arr, int index) {
            int parentIndex = (index - 1) / 2;
            while (arr[index] > arr[parentIndex]) {
                ArrayOperation.swap(arr, index, parentIndex);
                index = parentIndex;
                parentIndex = (index - 1) / 2;
            }
        }

        public int pop() {
            if (isEmpty()) return Constants.UN_INVALID;
            if (size == 1) return nums[--size];

            ArrayOperation.swap(nums, 0, --size);
            heapify(nums, 0, size);
            return nums[size];
        }

        private void heapify(int[] arr, int index, int heapSize) {
            if (index == heapSize - 1) return;

            int leftChildIndex = index * 2 + 1 < heapSize ? index * 2 + 1 : index;
            int largestChildIndex =
                    leftChildIndex + 1 < heapSize &&
                            nums[leftChildIndex] < nums[leftChildIndex + 1] ?
                            leftChildIndex + 1 : leftChildIndex;

            while (arr[index] < arr[largestChildIndex]) {
                ArrayOperation.swap(arr, index, largestChildIndex);

                index = largestChildIndex;
                leftChildIndex = index * 2 + 1 < heapSize ? index * 2 + 1 : index;

                largestChildIndex = leftChildIndex + 1 < heapSize &&
                        nums[leftChildIndex] < nums[leftChildIndex + 1] ?
                        leftChildIndex + 1 : leftChildIndex;

                if (largestChildIndex >= heapSize) break;
            }
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            MyHeap heap = new MyHeap(10);
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10, (o1, o2) -> o2 - o1);

            int operation = NumberOperation.getRandomNumber(3);
            int value = NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE);

            switch (operation) {
                case 0: {
                    heap.push(value);
                    priorityQueue.add(value);
                    break;
                }

                case 1: {
                    int heapPop = heap.pop();
                    Integer priorityPopInteger = priorityQueue.poll();
                    int priorityPop = Objects.isNull(priorityPopInteger) ?
                            Constants.UN_INVALID : priorityPopInteger;
                    if (heapPop != priorityPop) {
                        System.out.println("heap pop is wrong");
                        return;
                    }
                }

                case 2: {
                    if (priorityQueue.isEmpty() != heap.isEmpty()) {
                        System.out.println("the heap is empty is wrong");
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
