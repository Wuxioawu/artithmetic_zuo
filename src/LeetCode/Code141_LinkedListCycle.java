package LeetCode;

public class Code141_LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) return false;
        ListNode fast = head.next.next;
        ListNode slow = head.next;

        while (fast != slow) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == null || slow == null) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        boolean b = hasCycle(head);
        System.out.println(b);

    }
}
