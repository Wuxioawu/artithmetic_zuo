package Code_03;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;

public class Code02_DeleteGivenValue {
    public static SingleNode deleteGivenValue(SingleNode head, int value) {
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }

        SingleNode pre = head;
        SingleNode cur = head;

        while (cur!= null) {
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static SingleNode deleteGivenValueByCollection(SingleNode head, int value) {
        ArrayList<SingleNode> list = new ArrayList<>();

        while (head != null) {
            if (head.value != value) {
                list.add(head);
            }
            head = head.next;
        }

        if (list.isEmpty()) return null;

        SingleNode pre = list.removeFirst();
        SingleNode newHead = pre;

        while (!list.isEmpty()) {
            SingleNode cur = list.removeFirst();
            pre.next = cur;
            pre = cur;
        }
        pre.next = null;
        return newHead;
   }

    public static SingleNode getRandomElementOnValue(int givenNum) {
        ArrayList<SingleNode> list = new ArrayList<>();
        int size = NumberOperation.getRandomNumber(Constants.TEST_MAX_SIZE);
        for (int i = 0; i < size; i++) {
            int value = NumberOperation.isRandomGreaterThanValue(0.7) ?
                    givenNum:
                    NumberOperation.getRandomNumber(Constants.TEST_MAX_VALUE) ;

            list.add(new SingleNode(value));
        }

        if(list.isEmpty()) return null;

        SingleNode head = list.removeFirst();
        SingleNode current = head;

        while (!list.isEmpty()) {
            current.next = list.removeFirst();
            current = current.next;
        }
        return head;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            int randomOddNumber = NumberOperation.getRandomOddNumber(Constants.TEST_MAX_VALUE);
            SingleNode randomList = getRandomElementOnValue(randomOddNumber);
            SingleNode copyList = SingleListOperation.copySingleLinkedList(randomList);


            SingleNode deleteGivenValue = deleteGivenValue(copyList, randomOddNumber);
            SingleNode deleteGivenValueByCollection = deleteGivenValueByCollection(randomList, randomOddNumber);

            if (!SingleListOperation.isEqual(deleteGivenValue, deleteGivenValueByCollection)) {
                System.out.println("randomOddNumber: " + randomOddNumber);

                SingleListOperation.printSingleList(randomList);
                SingleListOperation.printSingleList(copyList);

                SingleListOperation.printSingleList(deleteGivenValue);
                SingleListOperation.printSingleList(deleteGivenValueByCollection);

                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }

        System.out.println(Constants.NICE);
    }



    public static void main(String[] args) {
        test();
    }
}
