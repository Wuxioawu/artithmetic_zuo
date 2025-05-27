package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Code03_IsBalanced {

    static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(BinaryTreeNode root) {
        if (root == null) return true;
        return processIsBalanced(root).isBalanced;
    }

    private static Info processIsBalanced(BinaryTreeNode root) {
        if (root == null) return new Info(true, 0);

        Info left = processIsBalanced(root.left);
        Info right = processIsBalanced(root.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced = left.isBalanced && right.isBalanced &&
                Math.abs(left.height - right.height) < 2;

        return new Info(isBalanced, height);
    }

    public static boolean isBalancedByRuteForce(BinaryTreeNode root) {
        if (root == null) return true;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<BinaryTreeNode> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            list.add(node);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        for (BinaryTreeNode node : list) {
            int leftHeight = BinaryTreeNodeOperation.getHeight(node.left);
            int rightHeight = BinaryTreeNodeOperation.getHeight(node.right);
            if (Math.abs(leftHeight - rightHeight) > 1) return false;
        }
        return true;
    }

    // for test

    public static BinaryTreeNode getRandomBST(int maxDepth, int maxValue) {
        int depth = NumberOperation.getRandomNumber(maxDepth);
        return processCreatBalanceTree(1, depth, maxValue);
    }

    public static BinaryTreeNode processCreatBalanceTree(int currentDepth, int maxDepth, int maxValue) {
        if (currentDepth > maxDepth) {
            return null;
        }
        int value = NumberOperation.getRandomNumber(maxValue);

        BinaryTreeNode root = new BinaryTreeNode(value);
        root.left = NumberOperation.isRandomGreaterThanValue(0.3) ?
                processCreatBalanceTree(currentDepth + 1, maxDepth, maxValue) : null;
        root.right = NumberOperation.isRandomGreaterThanValue(0.3) ?
                processCreatBalanceTree(currentDepth + 1, maxDepth, maxValue) : null;

        int leftHeight = BinaryTreeNodeOperation.getHeight(root.left);
        int rightHeight = BinaryTreeNodeOperation.getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return null;
        }
        return root;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = NumberOperation.isRandomGreaterThanValue(0.5) ?
                    getRandomBST(10, 100) : BinaryTreeNodeOperation.generateRandomBinaryTree(10, 100);

            boolean isBalanced = isBalanced(root);
            boolean isBalancedByRuteForce = isBalancedByRuteForce(root);

            if (isBalanced != isBalancedByRuteForce) {
                System.out.println("isBalanced = " + isBalanced + ", isBalancedByRuteForce = " + isBalancedByRuteForce);
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
