package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.*;

public class Code08_lowestAncestor {
    static class Info {
        boolean findA;
        boolean findB;
        BinaryTreeNode lowestAncestor;

        public Info(boolean findA, boolean findB, BinaryTreeNode lowestAncestor) {
            this.findA = findA;
            this.findB = findB;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static BinaryTreeNode getLowestAncestor(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {
        if (root == null) return null;
        return processLowestAncestor(root, n1, n2).lowestAncestor;
    }

    private static Info processLowestAncestor(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {
        if (root == null) return new Info(false, false, null);

        Info leftInfo = processLowestAncestor(root.left, n1, n2);
        Info rightInfo = processLowestAncestor(root.right, n1, n2);

        BinaryTreeNode lowestAncestor = null;
        boolean findA = leftInfo.findA || rightInfo.findA || root == n1;
        boolean findB = rightInfo.findB || leftInfo.findB || root == n2;

        if (leftInfo.findA) {
            if (rightInfo.findB) {
                lowestAncestor = root;
            }
            if (leftInfo.findB) {
                lowestAncestor = leftInfo.lowestAncestor;
            }
            if (root == n1 || root == n2) {
                lowestAncestor = root;
            }
        }

        if (rightInfo.findA) {
            if (leftInfo.findB) {
                lowestAncestor = root;
            }

            if (rightInfo.findB) {
                lowestAncestor = rightInfo.lowestAncestor;
            }
            if (root == n1 || root == n2) {
                lowestAncestor = root;
            }
        }

        if (root == n1) {
            lowestAncestor = root;
        }

        return new Info(findA, findB, lowestAncestor);
    }

    public static BinaryTreeNode getLowestAncestorByBruteForce(BinaryTreeNode root, BinaryTreeNode n1, BinaryTreeNode n2) {
        if (root == null) return null;

        // get the map of the parent and child
        HashMap<BinaryTreeNode, BinaryTreeNode> parents = getParents(root);
        HashSet<BinaryTreeNode> visited = new HashSet<>();

        while (n1 != null) {
            visited.add(n1);
            n1 = parents.get(n1);
        }

        while (n2 != null) {
            if (visited.contains(n2)) {
                return n2;
            }
            n2 = parents.get(n2);
        }
        return null;
    }

    private static HashMap<BinaryTreeNode, BinaryTreeNode> getParents(BinaryTreeNode root) {
        if (root == null) return null;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        HashMap<BinaryTreeNode, BinaryTreeNode> parents = new HashMap<>();
        parents.put(root, null);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                parents.put(node.left, node);
            }
            if (node.right != null) {
                queue.add(node.right);
                parents.put(node.right, node);
            }
        }
        return parents;
    }

    // for test
    public static BinaryTreeNode getRandomNode(BinaryTreeNode root) {
        if (root == null) return null;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        ArrayList<BinaryTreeNode> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            BinaryTreeNode temp = queue.poll();
            list.add(temp);
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        return list.get(NumberOperation.getRandomNumber(list.size()));
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode binaryTreeNode = BinaryTreeNodeOperation.generateRandomBinaryTree(10, 100);

            do {
                binaryTreeNode = BinaryTreeNodeOperation.generateRandomBinaryTree(10, 100);
            } while (BinaryTreeNodeOperation.getHeight(binaryTreeNode) < 2);

            BinaryTreeNode n1 = getRandomNode(binaryTreeNode);
            BinaryTreeNode n2;
            do {
                n2 = getRandomNode(binaryTreeNode);
            } while (n1 == n2);

            BinaryTreeNode maxSubBST = getLowestAncestor(binaryTreeNode, n1, n2);
            BinaryTreeNode maxSubBSTByBruteForce = getLowestAncestorByBruteForce(binaryTreeNode, n1, n2);

            if (maxSubBST != maxSubBSTByBruteForce) {
                System.out.println(maxSubBST != null ? maxSubBST.value : null);
                System.out.println(maxSubBSTByBruteForce != null ? maxSubBSTByBruteForce.value : null);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
