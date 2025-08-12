package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code530_MinimumAbstractDifference {

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

    public static int minimumAbsDifference(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(root);
        TreeNode current = root;

        int result = Integer.MAX_VALUE;
        while (true) {

            while (current.left != null) {
                stack.push(current.left);
                current = current.left;
            }

            if (stack.isEmpty()) break;

            do {
                current = stack.pop();

                list.add(current.val);
            } while (!stack.isEmpty() && current.right == null);

            current = current.right;

            if (current == null) break;

            stack.push(current);
        }

        for (int i = 0; i < list.size() - 1; i++) {
            result = Math.min(Math.abs(list.get(i) - list.get(i + 1)), result);
        }

        return result;
    }

    public static int recursive(TreeNode root) {
        if (root == null) return 0;

        return -1;

    }

    private static int process(TreeNode root, int result) {
        if (root == null) return 0;

        int current = Integer.MAX_VALUE;

        if (root.left != null) {
            process(root.left, result);
        }

        if (root.right != null) {
            process(root.right, result);
        }

        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        int i = minimumAbsDifference(root);
        System.out.println(i);
    }
}
