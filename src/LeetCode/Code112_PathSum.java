package LeetCode;

public class Code112_PathSum {

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

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        return process(root, targetSum);
    }

    private static boolean process(TreeNode root, int rest) {
        boolean noNull = false;
        boolean leftNull = false;
        boolean rightNull = false;

        if (root.left != null && root.right != null) {
            noNull = process(root.left, rest - root.val) || process(root.right, rest - root.val);
        } else if (root.left != null) {
            leftNull = process(root.left, rest - root.val);
        } else if (root.right != null) {
            rightNull = process(root.right, rest - root.val);
        }

        boolean res = noNull || leftNull || rightNull || rest - root.val == 0 && root.left == null && root.right == null;
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        int target = 22;
        boolean b = hasPathSum(root, target);
        System.out.println(b);
    }
}
