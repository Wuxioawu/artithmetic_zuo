package Code_10;

import tools.Constants;
import tools.NumberOperation;

import java.util.LinkedList;
import java.util.List;

public class Code04_EncodeNaryTreeBinaryTree {

    public static class MultiTreeNode {
        int val;
        LinkedList<MultiTreeNode> children;

        public MultiTreeNode(int val, LinkedList<MultiTreeNode> children) {
            this.val = val;
            this.children = children;
        }
    }

    // converts an N-ary tree to a binary tree
    public static BinaryTreeNode encode(MultiTreeNode root) {
        if (root == null) return null;
        BinaryTreeNode head = new BinaryTreeNode(root.val);
        head.left = en(root.children);
        return head;

    }

    private static BinaryTreeNode en(List<MultiTreeNode> children) {
        BinaryTreeNode head = null;
        BinaryTreeNode current = null;

        for (MultiTreeNode child : children) {
            BinaryTreeNode newChild = new BinaryTreeNode(child.val);
            if (head == null) {
                head = newChild;
            } else {
                current.right = newChild;
            }
            current = newChild;
            current.left = en(child.children);
        }
        return head;
    }

    public static MultiTreeNode decode(BinaryTreeNode root) {
        if (root == null) return null;
        return new MultiTreeNode(root.value, de(root.left));
    }

    private static LinkedList<MultiTreeNode> de(BinaryTreeNode root) {
        LinkedList<MultiTreeNode> res = new LinkedList<>();

        while (root != null) {
            res.add(new MultiTreeNode(root.value, de(root.left)));
            root = root.right;
        }
        return res;
    }

    //for test
    public static MultiTreeNode getRandomMultiTree(int maxDepth, int maxChild, int maxValue) {
        return process(1, maxDepth, maxChild, maxValue);
    }

    private static MultiTreeNode process(int currentDepth, int maxDepth, int maxChild, int maxValue) {
        if (currentDepth > maxDepth) {
            return null;
        }
        int value = NumberOperation.getRandomNumber(maxValue);
        LinkedList<MultiTreeNode> children = new LinkedList<>();

        for (int i = 0; i < NumberOperation.getRandomNumber(maxChild); i++) {
            MultiTreeNode child = process(currentDepth + 1, maxDepth, maxChild, value);
            if (child != null) {
                children.add(child);
            }
        }
        return new MultiTreeNode(value, children);
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            MultiTreeNode randomMultiTree = getRandomMultiTree(5, 5, 100);

            BinaryTreeNode encode = encode(randomMultiTree);
            MultiTreeNode decode = decode(encode);

            BinaryTreeNode encode1 = encode(decode);

            if (!BinaryTreeNodeOperation.isEqual(encode, encode1)) {
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
