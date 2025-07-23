package LeetCode.todo;

import java.util.LinkedList;
import java.util.Stack;

public class Code101_SymmetricTree {

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

    public static boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        var result = new LinkedList<TreeNode>();
        TreeNode cur = root;
        stack.push(cur);
        while (true) {
            while (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            }
            TreeNode pop;
            do {
                pop = stack.pop();
                result.add(pop);
                if (pop.left == null) {
                    break;
                }
            } while (!stack.isEmpty());

            if (pop.right != null) {
                cur = pop.right;
                stack.push(cur);
                continue;
            }

            break;
        }
        for (int i = 0, j = result.size() - 1; i <= j; i++, j--) {
            if (result.get(i).val != result.get(j).val) return false;
        }

        return true;
    }


    private static boolean isSymmetric2(TreeNode root) {
        // convert the left tree with the right tree
        return root == null || isSymmetric(root.left) && isSymmetric(root.right);
    }

    private static TreeNode process(TreeNode root) {
        if (root == null) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        process(root.left);
        process(root.right);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        System.out.println(isSymmetric(root));

    }

}
