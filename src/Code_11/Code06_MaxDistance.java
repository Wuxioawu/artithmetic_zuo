package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;

import java.util.*;

public class Code06_MaxDistance {

    static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static int getMaxDistance(BinaryTreeNode root) {
        if (root == null) return 0;
        return processMaxDistance(root).maxDistance;
    }

    private static Info processMaxDistance(BinaryTreeNode root) {
        if (root == null) return new Info(0, 0);

        Info leftInfo = processMaxDistance(root.left);
        Info rightInfo = processMaxDistance(root.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height);

        return new Info(maxDistance, height);
    }

    // 1.find the sub acent, 2.the height of the binary tree
    public static int getMaxDistanceByBruteForce(BinaryTreeNode root) {
        if (root == null) return 0;

        // height
        int binaryTreeHeight = BinaryTreeNodeOperation.getHeight(root);

        // get the leaf nodes
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        LinkedList<BinaryTreeNode> leafList = new LinkedList<>();
        HashMap<BinaryTreeNode, BinaryTreeNode> parents = new HashMap<>();
        parents.put(root, null);

        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();

            if (node.left == null && node.right == null) {
                leafList.add(node);
            }

            if (node.left != null) {
                queue.add(node.left);
                parents.put(node.left, node);
            }

            if (node.right != null) {
                queue.add(node.right);
                parents.put(node.right, node);
            }
        }
        int maxDistanceTwoLeafNode = 0;

        // public ancestor node
        for (int i = 0; i < leafList.size(); i++) {
            for (int j = i + 1; j < leafList.size(); j++) {
                maxDistanceTwoLeafNode =
                        Math.max(getTheDistanceBetween(parents, leafList.get(i), leafList.get(j)),
                                maxDistanceTwoLeafNode);
            }
        }
        return Math.max(maxDistanceTwoLeafNode, binaryTreeHeight - 1);
    }

    private static int getTheDistanceBetween(HashMap<BinaryTreeNode, BinaryTreeNode> parents, BinaryTreeNode n1, BinaryTreeNode n2) {
        ArrayList<BinaryTreeNode> n1ParentList = new ArrayList<>();
        int n2Step = 0;

        while (n1 != null) {
            n1ParentList.add(n1);
            n1 = parents.get(n1);
        }

        while (n2 != null) {
            if (n1ParentList.contains(n2)) {
                break;
            }
            n2 = parents.get(n2);
            n2Step++;
        }

        return n1ParentList.indexOf(n2) + n2Step;
    }

    // for test
    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root;
            do {
                root = BinaryTreeNodeOperation.generateRandomBinaryTree(5, 100);
            } while (root == null || root.left == null && root.right == null);

            int maxDistance = getMaxDistance(root);
            int maxDistanceByBruteForce = getMaxDistanceByBruteForce(root);

            if (maxDistance != maxDistanceByBruteForce) {
                System.out.println(Constants.CODE_ERROR);
                System.out.println(maxDistance);
                System.out.println(maxDistanceByBruteForce);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}


