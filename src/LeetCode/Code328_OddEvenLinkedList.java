package LeetCode;


public class Code328_OddEvenLinkedList {

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

    public static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode oddIndexNode = head;
        ListNode evenIndexNode = head.next;
        ListNode halfNode = evenIndexNode;
        while(evenIndexNode != null && evenIndexNode.next != null) {
            oddIndexNode.next = evenIndexNode.next;
            oddIndexNode = evenIndexNode.next;

            evenIndexNode.next = evenIndexNode.next.next;
            evenIndexNode = evenIndexNode.next;
        }
        oddIndexNode.next = halfNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        oddEvenList(head);
    }
}
