package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Code04_IsFull {

    static class Info {
        boolean isFull;
        int height;
        int nodes;

        public Info(boolean isFull, int height, int nodes) {
            this.isFull = isFull;
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFull(BinaryTreeNode root) {
        if (root == null) return true;
        return processIsFull(root).isFull;
    }

    private static Info processIsFull(BinaryTreeNode root) {
        if (root == null) return new Info(true, 0, 0);

        Info leftInfo = processIsFull(root.left);
        Info rightInfo = processIsFull(root.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull &&
                leftInfo.height == rightInfo.height;

        return new Info(isFull, height, nodes);

    }

    public static boolean isFullByBruteForce(BinaryTreeNode root) {
        if (root == null) return true;

        int height = BinaryTreeNodeOperation.getHeight(root);

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        ArrayList<BinaryTreeNode> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            list.add(node);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        int totalNodes = list.size();
        return (int) (Math.pow(2, height) - 1) == totalNodes;
    }

    //for test
    public static BinaryTreeNode getRandomFullBinaryTree(int maxDepth, int maxValue) {
        int depth = NumberOperation.getRandomNumber(maxDepth);
        return processCreatBinaryTree(1, depth, maxValue);
    }

    private static BinaryTreeNode processCreatBinaryTree(int currDepth, int maxDepth, int maxValue) {
        if (currDepth > maxDepth) {
            return null;
        }
        BinaryTreeNode root = new BinaryTreeNode(NumberOperation.getRandomNumber(maxValue));
        root.left = processCreatBinaryTree(currDepth + 1, maxDepth, maxValue);
        root.right = processCreatBinaryTree(currDepth + 1, maxDepth, maxValue);
        return root;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = NumberOperation.isRandomGreaterThanValue(0.5) ?
                    getRandomFullBinaryTree(8, 100) : BinaryTreeNodeOperation.generateRandomBinaryTree(8, 100);

            boolean isFull = isFull(root);
            boolean isFullByBruteForce = isFullByBruteForce(root);

            if (isFull != isFullByBruteForce) {
                System.out.println("isFill: " + isFull + ", isFullByBruteForce: " + isFullByBruteForce);
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
