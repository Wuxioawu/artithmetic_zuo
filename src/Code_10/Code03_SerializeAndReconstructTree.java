package Code_10;

import tools.Constants;

import java.util.*;

public class Code03_SerializeAndReconstructTree {

    public static ArrayList<Integer> preOrderSerialize(BinaryTreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            list.add(node.value);
            if (node.right != null) {
                stack.push(node.right);
            } else {
                list.add(null);
            }

            if (node.left != null) {
                stack.push(node.left);
            } else {
                list.add(null);
            }
        }
        return list;
    }

    public static ArrayList<Integer> postOrderSerialize(BinaryTreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            list.add(node.value);

            if (node.left != null) {
                stack.push(node.left);
            } else {
                list.add(null);
            }

            if (node.right != null) {
                stack.push(node.right);
            } else {
                list.add(null);
            }
        }
        Collections.reverse(list);
        return list;
    }

    public static ArrayList<Integer> levelOrderSerialize(BinaryTreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return new ArrayList<>();

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();
            result.add(poll.value);
            if (poll.left != null) {
                queue.add(poll.left);
                result.add(poll.left.value);
            } else {
                result.add(null);
            }

            if (poll.right != null) {
                queue.add(poll.right);
                result.add(poll.right.value);
            } else {
                result.add(null);
            }
        }

        return result;
    }

    public static BinaryTreeNode preOrderDeserialize(ArrayList<Integer> preOrder) {
        if (preOrder == null || preOrder.isEmpty()) return null;
        return preProcess(preOrder);
    }

    private static BinaryTreeNode preProcess(ArrayList<Integer> preOrder) {
        Integer value = preOrder.removeFirst();
        if (value == null) return null;
        BinaryTreeNode root = new BinaryTreeNode(value);
        root.left = preProcess(preOrder);
        root.right = preProcess(preOrder);
        return root;
    }

    public static BinaryTreeNode postOrderDeserialize(ArrayList<Integer> postOrder) {
        if (postOrder == null || postOrder.isEmpty()) return null;
        Collections.reverse(postOrder);
        return postProcess(postOrder);
    }

    public static BinaryTreeNode postProcess(ArrayList<Integer> postOrder) {
        Integer value = postOrder.removeFirst();
        if (value == null) return null;
        BinaryTreeNode root = new BinaryTreeNode(value);
        root.right = postProcess(postOrder);
        root.left = postProcess(postOrder);
        return root;
    }

    public static BinaryTreeNode levelOrderDeserialize(ArrayList<Integer> levelOrder) {
        if (levelOrder == null || levelOrder.isEmpty()) return null;

        BinaryTreeNode root = new BinaryTreeNode(levelOrder.removeFirst());
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();

            if (levelOrder.isEmpty()) break;

            poll.left = generateNode(levelOrder.getFirst() == null ? null : levelOrder.removeFirst());
            poll.right = generateNode(levelOrder.getFirst() == null ? null : levelOrder.removeFirst());

            if (poll.left != null) queue.add(poll.left);
            if (poll.right != null) queue.add(poll.right);
        }
        return root;
    }

    public static BinaryTreeNode generateNode(Integer value) {
        if (value == null) return null;
        return new BinaryTreeNode(value);
    }


    //for test
    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode root = BinaryTreeNodeOperation.generateRandomBinaryTree(10, 100);
            ArrayList<Integer> integers = levelOrderSerialize(root);
            BinaryTreeNode deserializeRoot = levelOrderDeserialize(integers);

            if (!BinaryTreeNodeOperation.isEqual(deserializeRoot, root)) {

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
