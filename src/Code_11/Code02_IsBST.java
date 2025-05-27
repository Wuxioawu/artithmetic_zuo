package Code_11;

import Code_10.BinaryTreeNode;
import Code_10.BinaryTreeNodeOperation;
import tools.Constants;
import tools.NumberOperation;

import java.util.*;

public class Code02_IsBST {

    static class Info {
        boolean isBST;
        int max;
        int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static boolean isBST(BinaryTreeNode root) {
        if (root == null) return true;
        return process(root).isBST;
    }

    private static Info process(BinaryTreeNode root) {
        if (root == null) return null;

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int max = root.value;
        int min = root.value;

        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }

        boolean isBST = false;

        if (leftInfo != null && rightInfo != null) {
            isBST = leftInfo.isBST && rightInfo.isBST &&
                    leftInfo.max < root.value && rightInfo.min > root.value;
        }
        if (leftInfo == null && rightInfo != null) {
            isBST = rightInfo.isBST && rightInfo.min > root.value;
        }
        if (leftInfo != null && rightInfo == null) {
            isBST = leftInfo.isBST && leftInfo.max < root.value;
        }
        if (leftInfo == null && rightInfo == null) {
            isBST = true;
        }

        return new Info(isBST, max, min);
    }

    public static boolean isBSTByInOrder(BinaryTreeNode root) {
        if (root == null) return true;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode current = root;
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {

            while (current.left != null) {
                stack.push(current.left);
                current = current.left;
            }

            BinaryTreeNode pop = stack.pop();
            list.add(pop.value);
            if (pop.right != null) {
                current = pop.right;
                stack.push(current);
            }
        }
        return isUpOrder(list);
    }

    public static boolean isUpOrder(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) return true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) return false;
        }
        return true;
    }


    // for test, every element is not repeated
    public static BinaryTreeNode getRandomBST(int maxDepth, int maxValue) {
        int depth = NumberOperation.getRandomNumber(maxDepth);
        ArrayList<Integer> valueList = getRandomOrderArrWithoutRepeated(depth);
        return processCreatBST(1, depth, valueList, 0, valueList.size() - 1);
    }

    private static BinaryTreeNode processCreatBST(int currentDepth, int depth, ArrayList<Integer> valueList, int leftIndex, int rightIndex) {
        if (currentDepth > depth || leftIndex >= rightIndex) {
            return null;
        }
        int currentIndex = leftIndex + (rightIndex - leftIndex) / 2;
        Integer currentValue = valueList.get(currentIndex);
        BinaryTreeNode root = new BinaryTreeNode(currentValue);

        root.left = NumberOperation.isRandomGreaterThanValue(0.3d) ?
                processCreatBST(currentDepth + 1, depth, valueList, leftIndex, currentIndex - 1) : null;
        root.right = NumberOperation.isRandomGreaterThanValue(0.3d) ?
                processCreatBST(currentDepth + 1, depth, valueList, currentIndex + 1, rightIndex) : null;
        return root;
    }

    public static boolean isCertificated(BinaryTreeNode root) {
        if (root == null) return true;
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode current = root;
        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            while (current.left != null) {
                stack.push(current.left);
                current = current.left;
            }

            BinaryTreeNode pop = stack.pop();
            if (list.contains(pop.value)) {
                return false;
            }

            list.add(pop.value);
            if (pop.right != null) {
                current = pop.right;
                stack.push(current);
            }
        }
        return true;
    }

    //the tree of the max depth is 10
    private static ArrayList<Integer> getRandomOrderArrWithoutRepeated(int depth) {
        int size = (int) (Math.pow(2, depth)) - 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int value;
            do {
                value = NumberOperation.getRandomNumber(10000);
            } while (list.contains(value));
            list.add(value);
        }
        Collections.sort(list);
        return list;
    }


    public static void test() {
        System.out.println(Constants.START_TEST);
        int index = 0;
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = NumberOperation.isRandomGreaterThanValue(0.3d) ? getRandomBST(7, 100) :
                    BinaryTreeNodeOperation.generateRandomBinaryTree(7, 100);

            if (!isCertificated(root)) {
                index++;
                continue;
            }

            boolean isBST = isBST(root);
            boolean isBSTByInOrder = isBSTByInOrder(root);
            if (isBST != isBSTByInOrder) {
                System.out.println(Constants.CODE_ERROR);
                System.out.println("isBST: " + isBST + " isBSTByInOrder: " + isBSTByInOrder);
                return;
            }
        }

        System.out.println("the binary tree is not certified: " + index);
        System.out.println("the binary tree not certificated rate is: " + (double)index / Constants.TEST_TIMES);

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();

    }
}
