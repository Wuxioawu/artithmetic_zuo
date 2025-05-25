package Code_10;

import tools.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Code01_OutPutIBT {

    public static LinkedList<BinaryTreeNode> preOrderRecursive(BinaryTreeNode root, LinkedList<BinaryTreeNode> list) {
        if (root == null) return list;
        list.add(root);
        preOrderRecursive(root.left, list);
        preOrderRecursive(root.right, list);
        return list;
    }

    public static LinkedList<BinaryTreeNode> inOrderRecursive(BinaryTreeNode root, LinkedList<BinaryTreeNode> list) {
        if (root == null) return list;
        inOrderRecursive(root.left, list);
        list.add(root);
        inOrderRecursive(root.right, list);
        return list;
    }

    public static ArrayList<BinaryTreeNode> postOrderRecursive(BinaryTreeNode root, ArrayList<BinaryTreeNode> list) {
        if (root == null) return list;
        postOrderRecursive(root.left, list);
        postOrderRecursive(root.right, list);
        list.add(root);
        return list;
    }

    public static ArrayList<BinaryTreeNode> unRecursivePreOrder(BinaryTreeNode root) {
        ArrayList<BinaryTreeNode> list = new ArrayList<>();
        if (root == null) return list;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            list.add(node);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    public static LinkedList<BinaryTreeNode> unRecursiveInOrder(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> list = new LinkedList<>();
        if (root == null) return list;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode current = root;
        while (!stack.isEmpty()) {

            while (current.left != null) {
                current = current.left;
                stack.push(current);
            }

            BinaryTreeNode node = stack.pop();
            list.add(node);
            if (node.right != null) {
                stack.push(node.right);
                current = node.right;
            }
        }
        return list;
    }

    public static ArrayList<BinaryTreeNode> unRecursivePostOrder(BinaryTreeNode root) {
        ArrayList<BinaryTreeNode> list = new ArrayList<>();
        if (root == null) return list;

        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            list.add(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(list);
        return list;
    }

    //for test
    public static boolean isEqual(LinkedList<BinaryTreeNode> list1, LinkedList<BinaryTreeNode> list2) {
        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).value != list2.get(i).value) return false;
        }
        return true;
    }

    public static void printTreeArrayList(LinkedList<BinaryTreeNode> list) {
        if (list == null || list.isEmpty()) return;
        for (BinaryTreeNode node : list) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            BinaryTreeNode binaryTreeNode = BinaryTreeNodeOperation.generateRandomBinaryTree(5, 10);

            LinkedList<BinaryTreeNode> list1 = new LinkedList<>();
            inOrderRecursive(binaryTreeNode, list1);
            LinkedList<BinaryTreeNode> list2 = unRecursiveInOrder(binaryTreeNode);

            if (!isEqual(list1, list2)) {
                System.out.println(Constants.CODE_ERROR);
                printTreeArrayList(list1);
                printTreeArrayList(list2);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
