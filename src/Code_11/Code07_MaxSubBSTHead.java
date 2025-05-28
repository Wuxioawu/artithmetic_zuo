package Code_11;

import Code_10.BinaryTreeNode;
import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code07_MaxSubBSTHead {

    static class MaxSubBSInfo {
        int maxValue;
        int minValue;
        int maxSubBSTSize;
        boolean isBST;
        BinaryTreeNode maxSubBSTNode;

        public MaxSubBSInfo(int maxValue, int minValue, int maxSubBSTSize, boolean isBST, BinaryTreeNode maxSubBSTNode) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.maxSubBSTSize = maxSubBSTSize;
            this.isBST = isBST;
            this.maxSubBSTNode = maxSubBSTNode;
        }
    }

    private static BinaryTreeNode findMaxSubBSTHead(BinaryTreeNode root) {
        if (root == null) return null;
        return processFinMaxSubBSTHead(root).maxSubBSTNode;
    }


    private static MaxSubBSInfo processFinMaxSubBSTHead(BinaryTreeNode root) {
        if (root == null) return null;

        MaxSubBSInfo leftInfo = processFinMaxSubBSTHead(root.left);
        MaxSubBSInfo rightInfo = processFinMaxSubBSTHead(root.right);

        int maxValue = root.value;
        int minValue = root.value;
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            minValue = Math.min(minValue, leftInfo.minValue);
        }

        if (rightInfo != null) {
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            minValue = Math.min(minValue, rightInfo.minValue);
        }

        boolean isBST = false;
        BinaryTreeNode maxSubBSTNode = null;

        if (leftInfo != null && rightInfo != null) {
            isBST = leftInfo.isBST && rightInfo.isBST && leftInfo.maxValue < root.value && rightInfo.minValue > root.value;
            MaxSubBSInfo lastMaxSubBSTInfo = leftInfo.maxSubBSTSize > rightInfo.maxSubBSTSize ? leftInfo : rightInfo;

            maxSubBSTNode = isBST ? root : lastMaxSubBSTInfo.maxSubBSTNode;
            maxSubBSTSize = isBST ? leftInfo.maxSubBSTSize + rightInfo.maxSubBSTSize + 1 : lastMaxSubBSTInfo.maxSubBSTSize;
        }

        if (leftInfo != null && rightInfo == null) {
            isBST = leftInfo.isBST && leftInfo.maxValue < root.value;
            maxSubBSTNode = isBST ? root : leftInfo.maxSubBSTNode;
            maxSubBSTSize = isBST ? leftInfo.maxSubBSTSize + 1 : leftInfo.maxSubBSTSize;
        }

        if (leftInfo == null && rightInfo != null) {
            isBST = rightInfo.isBST && rightInfo.minValue > root.value;
            maxSubBSTNode = isBST ? root : rightInfo.maxSubBSTNode;
            maxSubBSTSize = isBST ? rightInfo.maxSubBSTSize + 1 : rightInfo.maxSubBSTSize;
        }

        if (leftInfo == null && rightInfo == null) {
            isBST = true;
            maxSubBSTNode = root;
            maxSubBSTSize = 1;
        }

        return new MaxSubBSInfo(maxValue, minValue, maxSubBSTSize, isBST, maxSubBSTNode);
    }

    private static BinaryTreeNode findMaxSubBSTByBruteForce(BinaryTreeNode root) {
        if (root == null) return null;
        // get all nodes
        ArrayList<BinaryTreeNode> list = getAllNodesByLayOrder(root);
        // adjust a tree is Binary Search tree
        BinaryTreeNode maxSizeHead = root;
        int maxSize = 0;
        for (BinaryTreeNode node : list) {
            if (isBST(node)) {
                int sizeBinaryTree = getSizeBinaryTree(node);
                if (sizeBinaryTree > maxSize) {
                    maxSize = sizeBinaryTree;
                    maxSizeHead = node;
                }
            }
        }
        return maxSizeHead;
    }

    // get all nodes
    public static ArrayList<BinaryTreeNode> getAllNodesByLayOrder(BinaryTreeNode root) {
        ArrayList<BinaryTreeNode> result = new ArrayList<>();
        if (root == null) return result;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode cur = queue.poll();
            result.add(cur);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return result;
    }

    // get the size of the binary tree
    private static int getSizeBinaryTree(BinaryTreeNode root) {
        if (root == null) return 0;
        return processGetSize(root);
    }

    private static int processGetSize(BinaryTreeNode root) {
        if (root == null) return 0;
        int size = 1;
        size += processGetSize(root.left) + processGetSize(root.right);
        return size;
    }

    static class IsBSTInfo {
        int max;
        int min;
        boolean isBST;

        public IsBSTInfo(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    private static boolean isBST(BinaryTreeNode root) {
        if (root == null) return true;
        return processIsBST(root).isBST;
    }

    private static IsBSTInfo processIsBST(BinaryTreeNode root) {
        if (root == null) return null;

        IsBSTInfo leftInfo = processIsBST(root.left);
        IsBSTInfo rightInfo = processIsBST(root.right);

        int max = root.value;
        int min = root.value;
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        boolean isBST = false;
        if (leftInfo != null && rightInfo != null) {
            if (leftInfo.isBST && rightInfo.isBST) {
                isBST = leftInfo.max < root.value && rightInfo.min > root.value;
            }
        } else if (leftInfo != null) {
            isBST = leftInfo.isBST && leftInfo.max < root.value;
        } else if (rightInfo != null) {
            isBST = rightInfo.isBST && rightInfo.min > root.value;
        } else {
            isBST = true;
        }


        return new IsBSTInfo(isBST, max, min);
    }

    // for test
    private static BinaryTreeNode insert(BinaryTreeNode root, int value) {
        if (root == null) return new BinaryTreeNode(value);
        if (root.value > value) root.left = insert(root.left, value);
        else root.right = insert(root.right, value);
        return root;
    }

    private static BinaryTreeNode getRandomBST(int maxSize, int maxValue) {
        BinaryTreeNode root = null;
        int size = NumberOperation.getRandomNumber(maxSize);

        HashSet<Integer> haseBeUsed = new HashSet<>();

        while (haseBeUsed.size() < size) {
            int value = NumberOperation.getRandomNumber(maxValue);
            if (haseBeUsed.add(value)) {
                root = insert(root, value);
            }
        }
        return root;
    }


    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = getRandomBST(10, 100);

            BinaryTreeNode maxSubBSTHead = findMaxSubBSTHead(root);
            BinaryTreeNode maxSubBSTByBruteForce = findMaxSubBSTByBruteForce(root);

            if (maxSubBSTByBruteForce != maxSubBSTHead) {
                System.out.println("maxSubBSTByBruteForce.value: " + maxSubBSTByBruteForce.value);
                System.out.println("maxSubBSTHead.value: " + maxSubBSTHead.value);
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
