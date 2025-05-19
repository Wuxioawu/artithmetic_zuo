package Code_09;

import Code_03.SingleListOperation;
import Code_03.SingleNode;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;

public class Code03_SmallerEqualBigger {

    public static SingleNode partition(SingleNode head, int value) {
        if (head == null || head.next == null) return head;

        SingleNode lessHead = null;
        SingleNode lessTail = null;

        SingleNode equalHead = null;
        SingleNode equalTail = null;

        SingleNode greaterHead = null;
        SingleNode greaterTail = null;

        SingleNode current = head;

        while (current != null) {
            SingleNode next = current.next;

            SingleNode operationHead;
            SingleNode operationTail;

            if (current.value < value) {
                operationHead = lessHead;
                operationTail = lessTail;
            } else if (current.value == value) {
                operationHead = equalHead;
                operationTail = equalTail;
            } else {
                operationHead = greaterHead;
                operationTail = greaterTail;
            }

            if (operationHead == null) {
                operationHead = current;
            } else {
                operationTail.next = current;
            }

            operationTail = current;

            current.next = null;
            current = next;
        }

        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalHead;
        }

        if (equalTail != null) {
            equalTail.next = greaterHead;
        }

        return lessTail == null ? equalHead : lessHead;
    }

    public static SingleNode partitionByList(SingleNode head, int value) {
        if (head == null || head.next == null) return head;

        ArrayList<SingleNode> lessNodes = new ArrayList<>();
        ArrayList<SingleNode> equalNodes = new ArrayList<>();
        ArrayList<SingleNode> greaterNodes = new ArrayList<>();

        SingleNode next = head;
        while (next != null) {
            SingleNode current = next;
            next = next.next;
            if (current.value == value) {
                equalNodes.add(current);
            } else if (current.value < value) {
                lessNodes.add(current);
            } else {
                greaterNodes.add(current);
            }
            current.next = null;
        }

        lessNodes.addAll(equalNodes);
        lessNodes.addAll(greaterNodes);

        for (int i = 0; i < lessNodes.size() - 1; i++) {
            lessNodes.get(i).next = lessNodes.get(i + 1);
        }
        return lessNodes.getFirst();
    }

    //for test
    public static boolean isPartitionRight(SingleNode head, int value) {
        if (head == null || head.next == null) return true;

        SingleNode current = head;

        while (current != null) {
            if (current.value == value) {
                break;
            }
            current = current.next;
        }

        while (current != null) {
            if (current.value > value) {
                break;
            }

            if (current.value < value) {
                return false;
            }
            current = current.next;
        }

        while (current != null) {
            if (current.value <= value) {
                return false;
            }
            current = current.next;
        }

        return true;
    }

    public static int randomValueFromList(SingleNode head) {
        if (head == null) {
            return -1;
        }
        if (head.next == null) {
            return head.value;
        }

        SingleNode next = head;
        int index = 0;
        while (next != null) {
            next = next.next;
            index++;
        }
        int randomStep = NumberOperation.getRandomNumber(index);
        next = head;
        for (int i = 0; next.next != null && i < randomStep; i++) {
            next = next.next;
        }

        return next.value;

    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            SingleNode head = SingleListOperation.getRandomLinkedList(10, 100);
            int value = randomValueFromList(head);

            SingleNode partition1 = partition(head, value);
//            SingleNode partition2 = partitionByList(head, value);

            if (!isPartitionRight(partition1, value)) {
                SingleListOperation.printSingleList(head);
                System.out.println(value);
                SingleListOperation.printSingleList(partition1);
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
