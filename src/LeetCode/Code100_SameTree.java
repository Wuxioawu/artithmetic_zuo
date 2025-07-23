package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Code100_SameTree {

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

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        Queue<TreeNode> queue_p = new LinkedList<>();
        Queue<TreeNode> queue_q = new LinkedList<>();
        queue_p.add(p);
        queue_q.add(q);
        while (!queue_p.isEmpty() && !queue_q.isEmpty()) {
            TreeNode poll_q = queue_q.poll();
            TreeNode poll_p = queue_p.poll();

            if (poll_q.val != poll_p.val) {
                return false;
            }

            if (poll_q.left != null && poll_p.left != null) {
                queue_q.add(poll_q.left);
                queue_p.add(poll_p.left);
            }

            if (poll_q.left == null && poll_p.left != null || poll_q.left != null && poll_p.left == null) {
                return false;
            }

            if (poll_q.right != null && poll_p.right != null) {
                queue_q.add(poll_q.right);
                queue_p.add(poll_p.right);
            }

            if (poll_q.right == null && poll_p.right != null || poll_q.right != null && poll_p.right == null) {
                return false;
            }
        }
        return queue_p.isEmpty() == queue_q.isEmpty();
    }

    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        return process(p, q);
    }

    private static boolean process(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return process(p.left, q.left) && process(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);

        TreeNode treeNode2 = new TreeNode(1);
        treeNode2.right = new TreeNode(2);

        boolean sameTree = isSameTree2(treeNode1, treeNode2);
        System.out.println(sameTree);


    }

}
