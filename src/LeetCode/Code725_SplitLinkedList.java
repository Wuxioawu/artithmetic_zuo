package LeetCode;

public class Code725_SplitLinkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null) return new ListNode[k];
        ListNode current = head;
        int countNode = 1;
        while (current.next != null) {
            countNode++;
            current = current.next;
        }

        ListNode last = null;
        ListNode[] result = new ListNode[k];
        current = head;
        int singleLength = (k > countNode) ? 1 : (countNode / k);
        int restNode = (k > countNode) ? 0 : (countNode % k);
        int index = 0;

        while (current != null && index < k) {
            result[index++] = current;
            int countPart = singleLength + (restNode <= 0 ? 0 : 1);
            restNode--;

            while (countPart-- > 0) {
                last = current;
                current = current.next;
            }

            last.next = null;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next = new ListNode(10);
        splitListToParts(head, 3);
    }
}
