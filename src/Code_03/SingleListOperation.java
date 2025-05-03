package Code_03;

import tools.NumberOperation;

public class SingleListOperation {
    public static boolean isEmpty(SingleNode head) {
        return head == null;
    }

    public static boolean isOneNode(SingleNode head) {
        return head.next == null;
    }

    public static boolean isCanBeReserved(SingleNode head) {
        return !(isEmpty(head) || isOneNode(head));
    }

    public static SingleNode getRandomLinkedList(int maxSize, int maxValue) {
        int size = NumberOperation.getRandomNumber(maxSize);
        SingleNode head = new SingleNode(NumberOperation.getRandomNumber(maxValue));
        SingleNode current = head;
        for (int i = 0; i < size; i++) {
            SingleNode next = new SingleNode(NumberOperation.getRandomNumber(maxValue));
            current.next = next;
            current = next;
        }
        return head;
    }

    public static SingleNode copySingleLinkedList(SingleNode head) {
        if (isEmpty(head)) return null;
        SingleNode dummy = new SingleNode(-1);
        SingleNode current = dummy;
        SingleNode pointer = head;

        while (pointer != null) {
            current.next = new SingleNode(pointer.value);
            current = current.next;
            pointer = pointer.next;
        }

        return dummy.next;

    }

    public static void printSingleList(SingleNode head) {
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.value + "->");
            } else {
                System.out.print(head.value);
            }
            head = head.next;
        }
        System.out.println();
    }

    public static boolean isEqual(SingleNode linkedList1, SingleNode linkedList2) {
        if (linkedList1 == null && linkedList2 == null) return true;
        if (linkedList1 == null || linkedList2 == null) return false;

        while (linkedList1 != null && linkedList2 != null) {
            if (linkedList1.value != linkedList2.value) return false;
            linkedList1 = linkedList1.next;
            linkedList2 = linkedList2.next;
        }
        return linkedList1 == null && linkedList2 == null;
    }
}
