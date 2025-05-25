package Code_10;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.Stack;

public class Code06_SuccessorNode {

    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    // according the in-order to get the comfortable node
    public static Node findSuccessorByBruteForce(Node root, Node target) {
        if (root == null || target == null) return null;
        ArrayList<Node> successors = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        stack.add(root);
        Node current = root;

        while (!stack.isEmpty()) {

            while (current.left != null) {
                stack.push(current.left);
                current = current.left;
            }

            Node pop = stack.pop();
            successors.add(pop);

            if (pop.right != null) {
                stack.push(pop.right);
                current = pop.right;
            }
        }

        int index = successors.indexOf(target);
        return index == successors.size() - 1 ? null : successors.get(index + 1);
    }

    public static Node findSuccessorByParent(Node root, Node target) {
        if (root == null || target == null) return null;
        // have right children tree
        if (target.right != null) {
            target = target.right;
            while (target.left != null) {
                target = target.left;
            }
            return target;
        }

        // no have right children tree
        while (target.parent != null && target.parent.right == target) {
            target = target.parent;
        }
        return target.parent;
    }

    // for test
    public static Node getRandom(int maxDepth, int maxValue) {
        int depth = NumberOperation.getRandomNumber(maxDepth);
        return process(1, depth, maxValue);
    }

    public static Node getRandomNode(Node root) {
        if (root == null) return null;
        ArrayList<Node> nodes = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            nodes.add(pop);
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
        return nodes.get(NumberOperation.getRandomNumber(nodes.size()));
    }

    public static Node process(int currentDepth, int depth, int maxValue) {
        if (currentDepth >= depth) {
            return null;
        }

        Node root = new Node(NumberOperation.getRandomNumber(maxValue));
        root.left = NumberOperation.isRandomGreaterThanValue(0.3d) ?
                process(currentDepth + 1, depth, maxValue) : null;
        if (root.left != null) {
            root.left.parent = root;
        }

        root.right = NumberOperation.isRandomGreaterThanValue(0.3d) ?
                process(currentDepth + 1, depth, maxValue) : null;

        if (root.right != null) {
            root.right.parent = root;
        }
        return root;
    }

    public static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            Node randomTree = getRandom(10, 10);
            Node randomNode = getRandomNode(randomTree);

            Node successorByBruteForce = findSuccessorByBruteForce(randomTree, randomNode);
            Node successorByParent = findSuccessorByParent(randomTree, randomNode);

            if (successorByBruteForce != successorByParent) {
                System.out.println(Constants.CODE_ERROR);
                System.out.println(successorByBruteForce.value);
                System.out.println(successorByParent.value);
                return;
            }

        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
