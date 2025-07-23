package LeetCode;

public class Code222_CountCompileTreeNode {

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

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return process(root, 1);
    }

    private int process(TreeNode root, int sum) {
        if (root == null) return sum;
        int left = 0;
        int right = 0;
        if (root.left != null)
            left = process(root.left, sum + 1);
        if (root.right != null) {
            right = process(root.right, sum + 1);
        }
        return left + right + 1;
    }


    public static void main(String[] args) {

    }
}
