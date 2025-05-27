package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Code05_MaxSubBSTSize {
    static class Info {
        boolean isBST;
        int size;
        int max;
        int min;
        // the head of the most sub BST
        int maxSubSize;

        public Info(boolean isBST, int size, int max, int min, int maxSubSize) {
            this.isBST = isBST;
            this.size = size;
            this.max = max;
            this.min = min;
            this.maxSubSize = maxSubSize;
        }
    }

    public static int getMaxSubBSTSize(BinaryTreeNode root) {
        if (root == null) return 0;
        return process(root).maxSubSize;
    }

    private static Info process(BinaryTreeNode root) {
        if (root == null) return null;

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);


        int size = 1;
        int max = root.value;
        int min = root.value;

        int maxSubBSTSize = 1;
        boolean isBST = false;

        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            size += leftInfo.size;

            isBST = leftInfo.isBST && leftInfo.max < root.value;
            maxSubBSTSize = isBST ? leftInfo.size + 1 : leftInfo.maxSubSize;
        }

        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            size += rightInfo.size;

            isBST = rightInfo.isBST && rightInfo.min > root.value;
            maxSubBSTSize = isBST ? rightInfo.size + 1 : rightInfo.maxSubSize;
        }

        if (leftInfo != null && rightInfo != null) {
            isBST = leftInfo.isBST && rightInfo.isBST &&
                    leftInfo.max < root.value && rightInfo.min > root.value;
            if (isBST) {
                maxSubBSTSize = size;
            } else {
                int left = leftInfo.isBST && leftInfo.max < root.value ? leftInfo.size + 1 : leftInfo.maxSubSize;
                int right = rightInfo.isBST && rightInfo.min > root.value ? rightInfo.size + 1 : rightInfo.maxSubSize;
                maxSubBSTSize = Math.max(left, right);
            }
        }

        if (leftInfo == null && rightInfo == null) {
            isBST = true;
        }

        return new Info(isBST, size, max, min, maxSubBSTSize);
    }

    public static int getMaxSubBSTSizeByBruteForce(BinaryTreeNode root) {
        if (root == null) return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxSize = 0;
        while (!queue.isEmpty()) {
            BinaryTreeNode cur = queue.poll();

            int currentSize = isBST(cur);
            int noLeftNode = 0;
            int noRightNode = 0;

            if (cur.left != null) {
                BinaryTreeNode left = cur.left;
                cur.left = null;
                noLeftNode = isBST(cur);
                cur.left = left;
            }

            if (cur.right != null) {
                BinaryTreeNode right = cur.right;
                cur.right = null;
                noRightNode = isBST(cur);
                cur.right = right;
            }

            currentSize = Math.max(currentSize, Math.max(noLeftNode, noRightNode));
            maxSize = Math.max(maxSize, currentSize);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return maxSize;
    }

    // if this tree is BST, return the size, otherwise return 0
    private static int isBST(BinaryTreeNode root) {
        if (root == null) return 0;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        ArrayList<Integer> list = new ArrayList<>();
        BinaryTreeNode current = root;

        while (!stack.isEmpty()) {
            while (current.left != null) {
                stack.push(current.left);
                current = current.left;
            }

            BinaryTreeNode pop = stack.pop();
            list.add(pop.value);
            if (pop.right != null) {
                stack.push(pop.right);
                current = pop.right;
            }
        }
        // get the sub up order size in a array
        return isUpOrder(list) ? list.size() : 0;
    }

    private static boolean isUpOrder(ArrayList<Integer> lists) {
        if (lists == null || lists.isEmpty()) return true;
        for (int i = 1; i < lists.size(); i++) {
            if (lists.get(i) <= lists.get(i - 1)) return false;
        }
        return true;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = BinaryTreeNodeOperation.generateRandomBinaryTree(7, 100);

            int maxSubBSTSize = getMaxSubBSTSize(root);
            int maxSubBSTSizeByBruteForce = getMaxSubBSTSizeByBruteForce(root);
            if (maxSubBSTSize != maxSubBSTSizeByBruteForce) {
                System.out.println("maxSubBSTSize: " + maxSubBSTSize + " maxSubBSTSizeByBruteForce: " + maxSubBSTSizeByBruteForce);
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
