package Code_09;

import Code_03.SingleListOperation;
import Code_03.SingleNode;
import tools.Constants;

import java.util.ArrayList;

public class Code01_LinkedListMid {


    /**
     * odds node: return the mid node
     * even node: return the upper mid node
     */
    public static SingleNode getTheMidUpper(SingleNode head) {
        if (head == null || head.next == null) return head;
        SingleNode fast = head;
        SingleNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static SingleNode getTheMidByArrayListUpper(SingleNode head) {
        if (head == null || head.next == null) return head;
        ArrayList<SingleNode> arrayList = new ArrayList<>();

        SingleNode node = head;
        while (node != null) {
            arrayList.add(node);
            node = node.next;
        }

        int mid = (arrayList.size() - 1) / 2;
        return arrayList.get(mid);
    }

    /**
     * odds node: return the mid node
     * even mode: return the lower mid node
     */
    public static SingleNode getTheMidLower(SingleNode head) {
        if (head == null || head.next == null) return head;
        SingleNode fast = head;
        SingleNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast.next == null ? slow : slow.next;
    }

    public static SingleNode getTheMidByListLower(SingleNode head) {
        if (head == null || head.next == null) return head;
        ArrayList<SingleNode> arrayList = new ArrayList<>();
        SingleNode node = head;
        while (node != null) {
            arrayList.add(node);
            node = node.next;
        }

        int mid = (arrayList.size() - 1) / 2 + (arrayList.size() % 2 == 0 ? 1 : 0);
        return arrayList.get(mid);
    }

    public static SingleNode getTheMidBeforeUpper(SingleNode head) {
        if (head == null || head.next == null) return head;
        SingleNode fast = head;
        SingleNode slow = head;
        SingleNode value = slow;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            value = slow;
            slow = slow.next;

        }
        return value;
    }

    public static SingleNode getTheMidByListBeforeUpper(SingleNode head) {
        if (head == null || head.next == null) return head;

        ArrayList<SingleNode> arrayList = new ArrayList<>();
        SingleNode node = head;
        while (node != null) {
            arrayList.add(node);
            node = node.next;
        }

        int mid = Math.max((arrayList.size() - 1) / 2 - 1, 0);
        return arrayList.get(mid);
    }

    public static SingleNode getTheMidBeforeLower(SingleNode head) {
        if (head == null || head.next == null) return head;
        SingleNode fast = head;
        SingleNode slow = head;
        SingleNode value = slow;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            value = slow;
            slow = slow.next;
        }

        return fast.next == null ? value : slow;
    }

    public static SingleNode getTheMidByListBeforeLower(SingleNode head) {
        if (head == null || head.next == null) return head;

        ArrayList<SingleNode> arrayList = new ArrayList<>();
        SingleNode node = head;
        while (node != null) {
            arrayList.add(node);
            node = node.next;
        }
        int mid = (arrayList.size() - 1) / 2 + (arrayList.size() % 2 == 0 ? 1 : 0);
        return arrayList.get(Math.max(mid - 1, 0));
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            SingleNode head = SingleListOperation.getRandomLinkedList(100, 100);

            SingleNode mid = getTheMidBeforeLower(head);
            SingleNode midByArrayList = getTheMidByListBeforeLower(head);

            if (mid != midByArrayList) {
                System.out.println(Constants.CODE_ERROR);
                SingleListOperation.printSingleList(head);
                System.out.println(mid.value);
                System.out.println(midByArrayList.value);
                return;
            }


        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
