package Code_10;

import tools.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Code02_LevelOutPutIBT {

    public static LinkedList<BinaryTreeNode> levelOrderByQueue(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> resultList = new LinkedList<>();
        if (root == null) return resultList;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();
            resultList.add(poll);

            if (poll != null && poll.left != null) queue.add(poll.left);
            if (poll != null && poll.right != null) queue.add(poll.right);
        }
        return resultList;
    }

    public static LinkedList<BinaryTreeNode> levelOrderByFlag(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> resultList = new LinkedList<>();
        if (root == null) return resultList;

        int height = BinaryTreeNodeOperation.getHeight(root);
        int index = 1;
        while (index <= height) {
            BinaryTreeNodeOperation.collectLevel(root, index++, resultList);
        }
        return resultList;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = BinaryTreeNodeOperation.generateRandomBinaryTree(5, 100);

            LinkedList<BinaryTreeNode> list1 = levelOrderByQueue(root);
            LinkedList<BinaryTreeNode> list2 = levelOrderByFlag(root);

            if (!Code01_OutPutIBT.isEqual(list1, list2)) {
                System.out.println(Constants.CODE_ERROR);
                Code01_OutPutIBT.printTreeArrayList(list1);
                Code01_OutPutIBT.printTreeArrayList(list2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }


    public static void main(String[] args) {
        test();
    }
}
