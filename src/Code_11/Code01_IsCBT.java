package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Code01_IsCBT {

    static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static boolean isCBT(BinaryTreeNode root) {
        return process(root).isCBT;
    }

    private static Info process(BinaryTreeNode root) {
        if (root == null) return new Info(true, true, 0);

        Info left = process(root.left);
        Info right = process(root.right);

        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        int height = Math.max(left.height, right.height) + 1;

        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else {
            int heightDiff = Math.abs(left.height - right.height);
            if (heightDiff == 1 && right.height < left.height) {
                isCBT = left.isCBT && right.isFull;
            }
            if (heightDiff == 0) {
                isCBT = left.isFull && right.isCBT;
            }
        }
        return new Info(isFull, isCBT, height);
    }

    public static boolean isCBTByLayOrder(BinaryTreeNode root) {
        if (root == null) return true;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        BinaryTreeNode currentEnd = root;
        BinaryTreeNode nextEnd = null;

        ArrayList<BinaryTreeNode> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();
            list.add(poll);

            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }

            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }

            if (poll == currentEnd) {
                currentEnd = nextEnd;
            }
        }

        return isCBTNodesByLayer(list);
    }

    public static boolean isCBTNodesByLayer(ArrayList<BinaryTreeNode> layNodes) {
        if (layNodes == null || layNodes.isEmpty()) return true;
        boolean startAdjust = false;
        for (BinaryTreeNode node : layNodes) {
            if (node.left == null && node.right != null) {
                return false;
            }
            if (startAdjust && (node.left != null || node.right != null)) {
                return false;
            }
            if (!startAdjust && (node.left == null || node.right == null)) {
                startAdjust = true;
            }
        }
        return true;
    }

    // for test
    public static BinaryTreeNode getRandomCBT(int maxDepth, int maxValue) {
        int depth = NumberOperation.getRandomNumber(maxDepth);
        return process(1, depth, maxValue);
    }

    private static BinaryTreeNode process(int currentDepth, int depth, int maxValue) {
        if (currentDepth > depth) {
            return null;
        }
        int value = NumberOperation.getRandomNumber(maxValue);
        BinaryTreeNode root = new BinaryTreeNode(value);
        root.left = process(currentDepth + 1, depth, maxValue);
        root.right = NumberOperation.isRandomGreaterThanValue(0.5d) ?
                process(currentDepth + 1, depth, maxValue) : null;
        return root;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = NumberOperation.isRandomGreaterThanValue(0.5d) ?
                    BinaryTreeNodeOperation.generateRandomBinaryTree(7, 100) :
                    getRandomCBT(5, 100);
            boolean cbt = isCBT(root);
            boolean cbtByLayOrder = isCBTByLayOrder(root);
            if (cbt != cbtByLayOrder) {
                System.out.println("cbt :" + cbt + " cbtByLayOrder: " + cbtByLayOrder);
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
