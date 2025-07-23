package LeetCode;

import java.util.*;

public class Code637_AverageLevelBinaryTree {
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

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        int currentLevel = 1;
        double nodeNum = 0;
        int count = 0;
        int statisticLeve = currentLevel;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        levelMap.put(root, 1);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            currentLevel = levelMap.get(poll);

            if (statisticLeve == currentLevel) {
                nodeNum += poll.val;
                count++;

            } else {
                result.add(nodeNum / count);
                statisticLeve = currentLevel;
                nodeNum = poll.val;
                count = 1;
            }

            if (poll.left != null) {
                queue.add(poll.left);
                levelMap.put(poll.left, currentLevel + 1);
            }
            if (poll.right != null) {
                queue.add(poll.right);
                levelMap.put(poll.right, currentLevel + 1);
            }
        }
        result.add((double) nodeNum / count);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<Double> doubles = averageOfLevels(root);

        for (Double d : doubles) {
            System.out.println(d);
        }


    }
}
