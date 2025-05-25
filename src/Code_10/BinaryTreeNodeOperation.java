package Code_10;

import tools.NumberOperation;

import java.util.LinkedList;
import java.util.Queue;


public class BinaryTreeNodeOperation {

    public static BinaryTreeNode generateRandomBinaryTree(int maxDepth, int maxValue) {

        return generate(1, NumberOperation.getRandomNumber(maxDepth), maxValue);
    }

    private static BinaryTreeNode generate(int currentDepth, int maxDepth, int maxValue) {
        if (currentDepth > maxDepth || NumberOperation.isRandomGreaterThanValue(0.7)) {
            return null;
        }
        BinaryTreeNode node = new BinaryTreeNode(NumberOperation.getRandomNumber(maxValue));
        node.left = generate(currentDepth + 1, maxDepth, maxValue);
        node.right = generate(currentDepth + 1, maxDepth, maxValue);
        return node;
    }

    public static int getHeight(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static void collectLevel(BinaryTreeNode root, int level, LinkedList<BinaryTreeNode> list) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            list.add(root);
        } else {
            collectLevel(root.left, level - 1, list);
            collectLevel(root.right, level - 1, list);
        }
    }

    //todo:finish print BinaryTree
    public static void printBinaryTree(BinaryTreeNode root) {

    }

    public static boolean isEqual(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        int heightRoot1 = getHeight(root1);
        if (heightRoot1 != getHeight(root2)) return false;

        for (int i = 1; i <= heightRoot1; i++) {
            LinkedList<BinaryTreeNode> list1 = new LinkedList<>();
            LinkedList<BinaryTreeNode> list2 = new LinkedList<>();
            collectLevel(root1, i, list1);
            collectLevel(root2, i, list2);

            if (list1.size() != list2.size()) return false;
            while (!list1.isEmpty()) {
                BinaryTreeNode node1 = list1.pop();
                BinaryTreeNode node2 = list2.pop();
                if (node1.value != node2.value) return false;
                if (node1.left != null && node2.left == null) return false;
                if (node1.right != null && node2.right == null) return false;
                if (node1.left == null && node2.left != null) return false;
                if (node1.right == null && node2.right != null) return false;
            }
        }

        return true;
    }

    public static int getMaxWidth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        BinaryTreeNode currentEnd = root;
        BinaryTreeNode nextEnd = null;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int max = 0;
        int currentWidth = 0;

        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();

            if (poll.left != null) {
                nextEnd = poll.left;
                queue.add(nextEnd);
            }

            if (poll.right != null) {
                nextEnd = poll.right;
                queue.add(nextEnd);
            }

            currentWidth++;

            if (poll == currentEnd) {
                max = Math.max(max, currentWidth);
                currentWidth = 0;
                currentEnd = nextEnd;
            }
        }
        return max;
    }

    public static int getTheDepth(BinaryTreeNode root, BinaryTreeNode targetNode) {
        if (root == null || targetNode == null) {
            return 0;
        }

        return processTheDepth(root, targetNode, 1);
    }

    private static int processTheDepth(BinaryTreeNode root, BinaryTreeNode targetNode, int depth) {
        if (root == null) return -1;

        if (targetNode == root) {
            return depth;
        }

        int left = processTheDepth(root.left, targetNode, depth + 1);

        if (left != -1) {
            return left;
        }

        return processTheDepth(root.right, targetNode, depth + 1);
    }
}
















