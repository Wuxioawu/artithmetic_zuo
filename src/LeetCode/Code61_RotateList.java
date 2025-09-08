package LeetCode;

import java.util.HashMap;


public class Code61_RotateList {

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

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        HashMap<ListNode, ListNode> lastNodeMap = new HashMap<>();
        ListNode current = head;
        ListNode finalNode = null;
        lastNodeMap.put(head, null);
        int length = 1;

        // build the map to reflect the node with the last node
        while (current.next != null) {
            lastNodeMap.put(current.next, current);
            current = current.next;
            finalNode = current;
            length++;
        }

        k = k % length;

        while (k-- > 0) {
            ListNode newfinalNode = lastNodeMap.get(finalNode);
            newfinalNode.next = null;
            finalNode.next = head;
            lastNodeMap.put(head, finalNode);
            lastNodeMap.put(finalNode, null);
            head = finalNode;
            finalNode = newfinalNode;
        }
        return head;
    }

    public static ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode lastNode = head;
        int countNode = 1;

        while (lastNode.next != null) {
            lastNode = lastNode.next;
            countNode++;
        }

        lastNode.next = head;
        k = k % countNode;
        int moveStep = countNode - k;
        while (moveStep-- > 0) {
            head = head.next;
            lastNode = lastNode.next;
        }

        lastNode.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        rotateRight(node1, 2);
    }
}
