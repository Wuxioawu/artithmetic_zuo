package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Code226_InvertBinaryTree {


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

    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) queue.add(left);
            if (right != null) queue.add(right);
            node.left = right;
            node.right = left;
        }

        return root;
    }

    private TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = right;
        root.right = left;
        invertTree2(right);
        invertTree2(left);
        return root;
    }

    public static void main(String[] args) {
    }
}
