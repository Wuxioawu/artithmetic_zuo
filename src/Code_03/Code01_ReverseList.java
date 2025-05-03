package Code_03;

import tools.Constants;

import java.util.ArrayList;
import java.util.Stack;

public class Code01_ReverseList {

    public static SingleNode reverseSingleNodeList(SingleNode head) {
        if (!SingleListOperation.isCanBeReserved(head)) return head;

        SingleNode pre = null;
        SingleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static DoubleNode reverseDoubleNodeList(DoubleNode head) {
        if (!DoubleListOperation.isCanBeReversed(head)) return head;
        DoubleNode mid = head;
        DoubleNode end = null;
        while (head != null) {
            head = head.next;
            mid.next = end;
            mid.last = head;
            end = mid;
            mid = head;
        }

        return end;
    }

    public static SingleNode reverseByAPI(SingleNode head) {
        Stack<SingleNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        head = stack.pop();
        SingleNode pre = null;
        SingleNode next = head;

        while (!stack.isEmpty()) {
            pre = stack.pop();
            pre.next = null;
            next.next = pre;
            next = pre;
        }
        return head;
    }

    public static DoubleNode reverseByAPI(DoubleNode head) {
        if (!DoubleListOperation.isCanBeReversed(head)) return head;

        ArrayList<DoubleNode> doubleNodeList = new ArrayList<>();
        while (head != null) {
            doubleNodeList.add(head);
            head = head.next;
        }
        DoubleNode newHead = doubleNodeList.getLast();
        doubleNodeList.removeLast();
        newHead.last = null;
        DoubleNode midNode = newHead;
        DoubleNode preNode = null;
        while (!doubleNodeList.isEmpty()) {
            preNode = doubleNodeList.getLast();
            doubleNodeList.removeLast();
            midNode.next = preNode;
            preNode.last = midNode;
            midNode = preNode;
            preNode.next = null;
        }
        return newHead;
    }

    public static void testSingleList() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            SingleNode randomLinkedList = SingleListOperation.getRandomLinkedList(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            SingleNode copySingleLinkedList = SingleListOperation.copySingleLinkedList(randomLinkedList);

            SingleNode reverseList = reverseSingleNodeList(randomLinkedList);
            SingleNode reverseByAPI = reverseByAPI(copySingleLinkedList);

            if (!SingleListOperation.isEqual(reverseList, reverseByAPI)) {
                SingleListOperation.printSingleList(reverseList);
                SingleListOperation.printSingleList(reverseByAPI);
                System.out.println(Constants.CODE_ERROR);
            }

        }
        System.out.println(Constants.NICE);
    }

    public static void testDoubleList() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            DoubleNode randomLinkedList = DoubleListOperation.getRandomLinkedList(Constants.TEST_MAX_SIZE, Constants.TEST_MAX_VALUE);
            DoubleNode doubleNodeCopyList = DoubleListOperation.copyList(randomLinkedList);

            DoubleNode reverseByAPI = reverseByAPI(randomLinkedList);
            DoubleNode reverseList = reverseDoubleNodeList(doubleNodeCopyList);

            if (!DoubleListOperation.isEqual(reverseList, reverseByAPI)) {
                DoubleListOperation.printList(randomLinkedList);

                DoubleListOperation.printList(reverseList);
                DoubleListOperation.printList(reverseByAPI);

                System.out.println(Constants.CODE_ERROR);
            }
        }
        System.out.println(Constants.NICE);

    }

    public static void main(String[] args) {
        testSingleList();
//        testDoubleList();
    }
}
