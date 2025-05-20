package Code_09;

import Code_03.SingleListOperation;
import Code_03.SingleNode;
import tools.Constants;
import tools.NumberOperation;

public class Code05_FindFirstIntersectNode {

    public static SingleNode findIntersectionNode(SingleNode headA, SingleNode headB) {
        SingleNode firstNode = getFirstNodeOfCircle(headA);
        SingleNode secondNode = getFirstNodeOfCircle(headB);

        // no circle
        if (firstNode == null && secondNode == null) {
            return noLoop(headA, headB);
        }
        // have circle
        if (firstNode != null && secondNode != null) {
            // the same node in circle
            if (firstNode == secondNode) {
                SingleNode temp = firstNode.next;
                firstNode.next = null;
                SingleNode result = noLoop(headA, headB);
                firstNode.next = temp;
                return result;
            }
            return bothLoop(headA, headB);
        }
        return null;
    }

    public static SingleNode noLoop(SingleNode headA, SingleNode headB) {
        SingleNode curFirst = headA;
        int sizeA = 0;
        int sizeB = 0;
        while (curFirst.next != null) {
            curFirst = curFirst.next;
            sizeA++;
        }
        SingleNode curSecond = headB;
        while (curSecond.next != null) {
            curSecond = curSecond.next;
            sizeB++;
        }
        if (curFirst != curSecond) {
            System.out.println("the tail of headA and headB are not the same");
            return null;
        }

        // ensure interaction
        int step = Math.abs(sizeA - sizeB);
        curFirst = sizeA > sizeB ? headA : headB;
        curSecond = curFirst == headA ? headB : headA;

        while (step != 0 && curFirst != null) {
            curFirst = curFirst.next;
            step--;
        }
        while (curFirst != curSecond && curSecond != null && curFirst != null) {
            curFirst = curFirst.next;
            curSecond = curSecond.next;
        }

        return curFirst;
    }

    public static SingleNode bothLoop(SingleNode firstNode, SingleNode secondNode) {
        SingleNode tempFirst = firstNode;
        while (firstNode != null) {
            firstNode = firstNode.next;
            if (firstNode == secondNode) {
                return firstNode;
            }
            if (firstNode == tempFirst) {
                return null;
            }
        }
        return null;
    }

    public static SingleNode getFirstNodeOfCircle(SingleNode head) {
        if (head == null || head.next == null) return null;

        SingleNode slow = head;
        SingleNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //for test
    public static SingleNode[] getNoLoopInteraction(int maxSize, int maxValue) {
        SingleNode headA = SingleListOperation.getRandomLinkedList(maxSize, maxValue);
        SingleNode headB = SingleListOperation.getRandomLinkedList(maxSize, maxValue);

        SingleNode current = headA;
        while (current.next != null) {
            current = current.next;
        }
        current.next = SingleListOperation.getRandomNode(headB);
        return new SingleNode[]{headA, headB};
    }

    public static SingleNode[] getLoopInteraction(int maxSize, int maxValue) {
        SingleNode noLoop = SingleListOperation.getRandomLinkedList(maxSize, maxValue);
        SingleNode loopLinkedList = getLoopLinkedList(maxSize, maxValue);

        SingleNode current = noLoop;
        while (current.next != null) {
            current = current.next;
        }
        current.next = getRandomNodeInLoop(loopLinkedList);
        return new SingleNode[]{noLoop, loopLinkedList};
    }

    public static SingleNode getRandomNodeInLoop(SingleNode head) {
        if (head == null || head.next == null) return head;

        SingleNode firstNodeOfCircle = getFirstNodeOfCircle(head);
        SingleNode tempFirst = firstNodeOfCircle.next;
        firstNodeOfCircle.next = null;

        SingleNode randNodeOnLine = SingleListOperation.getRandomNode(head);

        firstNodeOfCircle.next = tempFirst;
        tempFirst = firstNodeOfCircle;

        int sizeCircle = 1;
        do {
            firstNodeOfCircle = firstNodeOfCircle.next;
            sizeCircle++;
        } while (firstNodeOfCircle != tempFirst);

        int step = NumberOperation.getRandomNumber(sizeCircle);

        while (step-- >= 0) {
            firstNodeOfCircle = firstNodeOfCircle.next;
        }
        return NumberOperation.isRandomGreaterThanValue(0.5d) ? firstNodeOfCircle : randNodeOnLine;
    }

    public static SingleNode getLoopLinkedList(int maxSize, int maxValue) {
        SingleNode head = SingleListOperation.getRandomLinkedList(maxSize, maxValue);
        SingleNode random = SingleListOperation.getRandomNode(head);
        SingleNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = random;
        return head;
    }

    public static SingleNode[] getTheRightInput(int maxSize, int maxValue) {
        SingleNode[] result1 = new SingleNode[]{
                NumberOperation.isRandomGreaterThanValue(0.5d) ?
                        SingleListOperation.getRandomLinkedList(maxSize, maxValue) :
                        getLoopLinkedList(maxSize, maxValue),

                NumberOperation.isRandomGreaterThanValue(0.5d) ?
                        SingleListOperation.getRandomLinkedList(maxSize, maxValue) :
                        getLoopLinkedList(maxSize, maxValue)
        };

        SingleNode[] result2 = NumberOperation.isRandomGreaterThanValue(0.5d) ? getLoopInteraction(maxSize, maxValue) :
                getNoLoopInteraction(maxSize, maxValue);

        return NumberOperation.isRandomGreaterThanValue(0.5d) ? result1 : result2;

    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 100; i++) {
            SingleNode[] head = getTheRightInput(10, 10);
            SingleNode t = findIntersectionNode(head[0], head[1]);
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
