package Code_03;

import tools.NumberOperation;

public class DoubleListOperation {

    public static boolean isCanBeReversed(DoubleNode head) {
        return !isEmpty(head) && !isOnlyOneNode(head);
    }

    public static boolean isEmpty(DoubleNode head) {
        return head == null;
    }

    public static boolean isOnlyOneNode(DoubleNode head) {
        return head != null && head.next == null && head.last == null;
    }

    public static DoubleNode getRandomLinkedList(int maxSize, int maxValue) {
        DoubleNode head = new DoubleNode(NumberOperation.getRandomNumber(maxValue));
        head.next = new DoubleNode(NumberOperation.getRandomNumber(maxValue));
        DoubleNode next = head.next;
        next.last = head;
        int size = NumberOperation.getRandomNumber(maxSize);
        while (size-- > 0) {
            DoubleNode temp = new DoubleNode(NumberOperation.getRandomNumber(maxValue));
            next.next = temp;
            temp.last = next;
            next = temp;
        }
        return head;
    }

    public static void printList(DoubleNode head) {
        while (head != null) {
            if (head.last == null) {
                System.out.print("null ");
            }
            System.out.print("<- " + head.value + " ->");
            if (head.next == null) {
                System.out.print(" null");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static DoubleNode copyList(DoubleNode head) {
        if(isEmpty(head) || isOnlyOneNode(head)) {return head;}

        DoubleNode copyHead = new DoubleNode(head.value);
        DoubleNode copyTail = copyHead;
        while (head.next != null) {
            DoubleNode temp = new DoubleNode(head.next.value);
            copyTail.next = temp;
            temp.last = copyTail;
            copyTail = temp;
            head = head.next;
        }
        return copyHead;
    }

    public static boolean isEqual(DoubleNode head1, DoubleNode head2) {
        if (head1 == null && head2 == null) return true;
        if (head1 == null || head2 == null) return false;

        while (head1 != null && head2 != null) {
            if (head1.value != head2.value) return false;
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }
}
