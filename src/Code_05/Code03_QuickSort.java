package Code_05;

import tools.ArrayOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Recursive and unRecursive
 */
public class Code03_QuickSort {

    public static void quickSort(int[] nums) {
        if (!ArrayOperation.isSortable(nums)) return;
        process(nums, 0, nums.length - 1);
    }

    public static void process(int[] nums, int left, int right) {
        if (left >= right) return;

        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        ArrayOperation.swap(nums, right, randomIndex);

        int[] partition = partition(nums, left, right);
        if (partition == null) return;
        process(nums, left, partition[0] - 1);
        process(nums, partition[0] + 1, right);
    }

    public static int[] partition(int[] array, int left, int right) {
        if (left > right) return null;
        if (left == right) return new int[]{array[left], array[right]};

        int windowL = left - 1;
        int windowR = right;
        int index = 0;

        while (index < right) {
            if (windowR > index && array[index] > array[right]) {
                ArrayOperation.swap(array, --windowR, index);
                continue;
            }

            if (windowL < index && array[index] < array[right]) {
                ArrayOperation.swap(array, ++windowL, index);
                continue;
            }
            index++;
        }
        ArrayOperation.swap(array, Math.max(0, windowR), right);
        return new int[]{windowL + 1, windowR};
    }


    public static class QuickSortEntity {
        public int leftIndex;
        public int rightIndex;

        public QuickSortEntity(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }
    }

    //non-recursive use stack to realise
    public static void quickSortByStack(int[] array) {
        if (!ArrayOperation.isSortable(array)) return;
        ArrayOperation.swap(array, NumberOperation.getRandomNumber(array.length - 1), array.length - 1);

        int[] partition = partition(array, 0, array.length - 1);

        if (partition == null) return;

        int leftIndex = partition[0];
        int rightIndex = partition[1];

        Stack<QuickSortEntity> stack = new Stack<>();
        stack.push(new QuickSortEntity(0, leftIndex - 1));
        stack.push(new QuickSortEntity(rightIndex + 1, array.length - 1));

        while (!stack.isEmpty()) {
            QuickSortEntity entity = stack.pop();
            if (entity.leftIndex < entity.rightIndex) {

                ArrayOperation.swap(array, entity.leftIndex + NumberOperation.getRandomNumber(entity.rightIndex - entity.leftIndex + 1), entity.rightIndex);
                partition = partition(array, entity.leftIndex, entity.rightIndex);
                if (partition == null) break;
                leftIndex = partition[0];
                rightIndex = partition[1];
                stack.push(new QuickSortEntity(entity.leftIndex, leftIndex - 1));
                stack.push(new QuickSortEntity(rightIndex + 1, entity.rightIndex));
            }

        }


    }

    //non-recursive use queue to realise
    public static void quickSortByQueue(int[] array) {
        if (!ArrayOperation.isSortable(array)) return;

        ArrayOperation.swap(array, NumberOperation.getRandomNumber(array.length - 1), array.length - 1);
        int[] partition = partition(array, 0, array.length - 1);
        if (partition == null) return;
        int leftIndex = partition[0];
        int rightIndex = partition[1];
        Queue<QuickSortEntity> queue = new LinkedList<>();
        queue.offer(new QuickSortEntity(0, leftIndex - 1));
        queue.offer(new QuickSortEntity(rightIndex + 1, array.length - 1));
        while (!queue.isEmpty()) {
            QuickSortEntity entity = queue.poll();
            if (entity.leftIndex < entity.rightIndex) {
                ArrayOperation.swap(array, entity.leftIndex + NumberOperation.getRandomNumber(entity.rightIndex - entity.leftIndex), entity.rightIndex);
                partition = partition(array, entity.leftIndex, entity.rightIndex);
                if (partition == null) break;
                leftIndex = partition[0];
                rightIndex = partition[1];
                queue.offer(new QuickSortEntity(entity.leftIndex, leftIndex - 1));
                queue.offer(new QuickSortEntity(rightIndex + 1, entity.rightIndex));
            }
        }
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int[] array = ArrayOperation.getRandomArray(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            int[] copyArray = ArrayOperation.copyArray(array);
            int[] originalArray = ArrayOperation.copyArray(array);

            quickSortByQueue(array);
            ArrayOperation.sort(copyArray);

            if (!ArrayOperation.isEqualArrays(array, copyArray)) {
                System.out.println("array");
                ArrayOperation.printArray(array);
                System.out.println("copyArray");
                ArrayOperation.printArray(copyArray);
                System.out.println("originalArray");
                ArrayOperation.printArray(originalArray);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
