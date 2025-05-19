package Code_09;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;

public class Code04_CopyListWithRandom {

    public static class Node {
        int value;
        Node next;
        Node rand;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        if (head.next == null) return new Node(head.value);

        Node current = head;
        while (current != null) {
            Node next = current.next;
            Node node = new Node(current.value);
            current.next = node;
            node.next = next;
            current = next;
        }

        current = head;
        Node current_new = head.next;

        while (current_new != null) {
            if (current.rand != null) {
                current_new.rand = current.rand.next;
            }
            current = current_new.next;
            current_new = current != null ? current.next : null;
        }

        current = head;
        current_new = head.next;
        Node newHead = current_new;

        while (current_new != null) {
            current.next = current_new.next;
            current = current.next;

            current_new.next = current != null ? current.next : null;
            current_new = current_new.next;
        }
        return newHead;
    }

    public static Node copyRandomListByCollection(Node head) {
        if (head == null) return null;
        if (head.next == null) return new Node(head.value);

        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        ArrayList<Node> newList = new ArrayList<>();
        newList.add(new Node(list.getFirst().value));
        for (int i = 1; i < list.size(); i++) {
            newList.add(new Node(list.get(i).value));
            newList.get(i - 1).next = newList.get(i);
        }

        for (int i = 0; i < newList.size(); i++) {
            if (list.get(i).rand != null) {
                int index = list.indexOf(list.get(i).rand);
                newList.get(i).rand = newList.get(index);
            }
        }

        return newList.getFirst();
    }

    //for test
    public static Node getRandomList(int maxSize, int maxValue) {
        int size = NumberOperation.getRandomNumber(maxSize);
        int value = NumberOperation.getRandomNumber(maxValue);

        Node head = new Node(NumberOperation.getRandomNumber(maxValue));
        Node node = new Node(NumberOperation.getRandomNumber(maxValue));

        head.next = node;

        for (int i = 0; i < size; i++) {
            node.next = new Node(NumberOperation.getRandomNumber(value));
            node = node.next;
        }

        int modifyTime = NumberOperation.getRandomNumber(size);

        ArrayList<Node> nodes = new ArrayList<>();
        Node current = head;
        while (current != null) {
            nodes.add(current);
            current = current.next;
        }

        for (int i = 0; i < modifyTime; i++) {
            int left, right;
            do {
                left = NumberOperation.getRandomNumber(nodes.size());
                right = NumberOperation.getRandomNumber(nodes.size());
            } while (left == right);

            nodes.get(left).rand = nodes.get(right);
        }

        return head;
    }

    public static boolean isEqual(Node head1, Node head2) {
        if (head1 == null && head2 == null) return true;

        ArrayList<Node> nodes1 = new ArrayList<>();
        ArrayList<Node> nodes2 = new ArrayList<>();

        Node current = head1;
        while (current != null) {
            nodes1.add(current);
            current = current.next;
        }

        current = head2;
        while (current != null) {
            nodes2.add(current);
            current = current.next;
        }

        if (nodes1.size() != nodes2.size()) return false;

        for (int i = 0; i < nodes1.size(); i++) {
            if (nodes1.get(i).value != nodes2.get(i).value) return false;
            if (nodes1.get(i).rand == null && nodes2.get(i).rand == null) continue;
            if (nodes1.get(i).rand == null || nodes2.get(i).rand == null) return false;
            if (nodes1.get(i).rand.value != nodes2.get(i).rand.value) return false;
        }
        return true;
    }

    public static void printList(Node head) {
        if (head == null) return;
        Node current = head;
        while (current != null) {
            String str = current.rand != null ? " ->rand:" + current.rand.value : " ";
            System.out.print(" {" + current.value + str + "} -> ");
            current = current.next;
        }
        System.out.println();
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            Node head = getRandomList(10, 100);
            Node copy = copyRandomList(head);

            if (!isEqual(head, copy)) {
                printList(head);
                printList(copy);
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
