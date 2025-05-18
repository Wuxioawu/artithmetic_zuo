package Code_09;

import Code_03.SingleListOperation;
import Code_03.SingleNode;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;

public class Code02_IsPalindromeList {
    public static boolean isPalindrome(SingleNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // adjust whether the list is palindrome list
        SingleNode mid = getMid(head);
        SingleNode left = head;
        SingleNode right = reverseLinkedList(mid);
        SingleNode reverseRight = right;

        boolean result = true;
        while (left != right && left.next != null) {
            if (left.value != right.value) {
                result = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        SingleNode value = head;
        while (value.next != null && value.next.next != null) {
            value = value.next;
        }

        reverseRight = reverseLinkedList(reverseRight);
        if (reverseRight == value) {
            head = reverseRight;
        } else {
            value.next = reverseRight;
        }

        return result;
    }

    public static SingleNode reverseLinkedList(SingleNode head) {
        SingleNode current = head;
        SingleNode preNode = null;

        while (current != null) {
            SingleNode next = current.next;
            current.next = preNode;
            preNode = current;
            current = next;
        }

        return preNode;
    }

    public static SingleNode getMid(SingleNode head) {
        SingleNode fast = head;
        SingleNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static boolean isPalindromeByList(SingleNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ArrayList<SingleNode> nodes = new ArrayList<>();
        SingleNode current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }
        int left = 0;
        int right = nodes.size() - 1;
        while (left <= right) {
            if (nodes.get(left).value != nodes.get(right).value) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //for test
    public static SingleNode getPalindromeList(int maxSizeOfHalf, int maxValue) {
        int sizeOfHalf;
        do {
            sizeOfHalf = NumberOperation.getRandomNumber(maxSizeOfHalf);
        } while (sizeOfHalf == 0);

        int value = NumberOperation.getRandomNumber(maxValue);
        SingleNode leftHead = new SingleNode(value);
        SingleNode leftNode = leftHead;

        for (int i = 0; i < sizeOfHalf; i++) {
            value = NumberOperation.getRandomNumber(maxValue);
            leftNode.next = new SingleNode(value);
            leftNode = leftNode.next;
        }
        //copy a new list
        SingleNode rightHead = SingleListOperation.copySingleLinkedList(leftHead);
        //reverse the linkedList
        SingleNode rightNode = null;
        SingleNode currentNode = rightHead;
        while (currentNode != null && currentNode.next != null) {
            currentNode = currentNode.next;
            rightHead.next = rightNode;
            rightNode = rightHead;
            rightHead = currentNode;
        }
        rightHead.next = rightNode;
        leftNode.next = NumberOperation.isRandomGreaterThanValue(0.5d) ? rightNode : rightHead;
        return leftHead;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 1000; i++) {
            SingleNode head = NumberOperation.isRandomGreaterThanValue(0.5d) ?
                    getPalindromeList(5, 100) :
                    SingleListOperation.getRandomLinkedList(10, 100);

            boolean isPalindrome = isPalindrome(head);
            boolean isPalindromeByList = isPalindromeByList(head);

            if (isPalindrome != isPalindromeByList) {
                System.out.println(Constants.CODE_ERROR);
                SingleListOperation.printSingleList(head);
                System.out.println("isPalindromeByList: " + isPalindromeByList + ", isPalindrome: " + isPalindrome);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
