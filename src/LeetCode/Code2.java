package LeetCode;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Code2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode();
        ListNode current = newHead;

        int nextValAdd = 0;

        while(l1 != null && l2 != null) {
            int currentVal = (l1.val + l2.val) + nextValAdd;

            if(currentVal >= 10) {
                nextValAdd = currentVal / 10;
                currentVal = currentVal % 10;
            }

            current.next = new ListNode(currentVal);
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode notNull = l1 != null ? l1 : l2;

        while(notNull != null) {
            int currentVal = notNull.val  + nextValAdd;
            if(currentVal >= 10) {
                nextValAdd = currentVal / 10;
                currentVal = currentVal % 10;
            }

            current.next = new ListNode(currentVal);
            current = current.next;
            notNull = notNull.next;
        }
        if(nextValAdd >0 ) {
            current.next = new ListNode(nextValAdd);
        }
        return newHead.next;
    }


    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);

        l1.next = l2;l2.next = l3;
        l3.next = l4;l4.next = l5;
        l5.next = l6;l6.next = l7;


        ListNode l8 = new ListNode(9);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(9);
        ListNode l11 = new ListNode(9);
        l8.next = l9;l9.next = l10;
        l10.next = l11;

        ListNode listNode = addTwoNumbers(l1, l8);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
