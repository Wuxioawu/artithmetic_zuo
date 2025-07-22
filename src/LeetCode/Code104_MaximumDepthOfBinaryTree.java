package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code104_MaximumDepthOfBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int maxLevel = 0;

        HashMap<TreeNode, Integer> currentMap = new HashMap<>();
        currentMap.put(root, 1);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            int currentLevel = currentMap.get(poll);

            maxLevel = Math.max(maxLevel, currentLevel);

            if (poll.left != null) {
                queue.add(poll.left);
                currentMap.put(poll.left, currentLevel + 1);
            }

            if (poll.right != null) {
                queue.add(poll.right);
                currentMap.put(poll.right, currentLevel + 1);
            }
        }
        return maxLevel;
    }


    private static int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return process(0, root);
    }

    private static int process(int currentDepth, TreeNode root) {
        if (root == null) return currentDepth;

        int left = process(currentDepth + 1, root.left);
        int right = process(currentDepth + 1, root.right);

        return Math.max(left, right);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(maxDepth(root));
    }
}
