package Code_10;

import tools.Constants;
import java.util.*;

public class Code05_TreeMaxWidth {

    public static int getMaxWidthByMap(BinaryTreeNode root) {
        if (root == null) return 0;

        HashMap<BinaryTreeNode, Integer> map = new HashMap<>();
        map.put(root, 1);
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        int maxWidth = 0;
        int level = 1;
        int currentLayNode = 0;

        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            Integer currentLevel = map.get(node);

            if (node.left != null) {
                map.put(node.left, currentLevel + 1);
                queue.add(node.left);
            }

            if (node.right != null) {
                map.put(node.right, currentLevel + 1);
                queue.add(node.right);
            }

            if (level == currentLevel) {
                currentLayNode++;
            } else {
                maxWidth = Math.max(maxWidth, currentLayNode);
                currentLayNode = 1;
                level++;
            }
        }
        return Math.max(maxWidth, currentLayNode);
    }

    public static int getMaxWidthByStatistic(BinaryTreeNode root) {
        if (root == null) return 0;
        int height = BinaryTreeNodeOperation.getHeight(root);
        int[] nodeNumEveyLay = new int[height];

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode pop = stack.pop();
            nodeNumEveyLay[BinaryTreeNodeOperation.getTheDepth(root, pop) - 1]++;
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        Arrays.sort(nodeNumEveyLay);
        return nodeNumEveyLay[nodeNumEveyLay.length - 1];
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode binaryTreeNode = BinaryTreeNodeOperation.generateRandomBinaryTree(5, 100);
            int maxWidthByMap = getMaxWidthByMap(binaryTreeNode);
            int maxWidth = BinaryTreeNodeOperation.getMaxWidth(binaryTreeNode);
            if (maxWidth != maxWidthByMap) {
                System.out.println(maxWidth);
                System.out.println(maxWidthByMap);
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
