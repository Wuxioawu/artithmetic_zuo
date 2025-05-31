package Code_14;

import tools.Constants;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Code02_DFS {

    private static List<Node> DFSOther(Node start) {
        var resultList = new LinkedList<Node>();
        if (start == null) return resultList;

        Stack<Node> stack = new Stack<>();
        HashSet<Node> visited = new HashSet<>();

        stack.push(start);
        visited.add(start);
        resultList.add(start);

        Node current;
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            current = peek;

            while (!current.nextNode.isEmpty()) {
                for (Node node : current.nextNode) {
                    if (!visited.contains(node)) {
                        stack.push(node);
                        visited.add(node);
                        resultList.add(node);
                        current = node;
                        break;
                    }
                }

                if (current == peek)
                    break;
            }
            stack.pop();
        }
        return resultList;
    }

    private static List<Node> DFS(Node start) {
        var resultList = new LinkedList<Node>();
        if (start == null) return resultList;

        Stack<Node> stack = new Stack<>();
        HashSet<Node> visited = new HashSet<>();
        stack.push(start);
        visited.add(start);
        resultList.add(start);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nextNode) {
                if (!visited.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    visited.add(next);
                    resultList.add(next);
                    break;
                }
            }

        }
        return resultList;
    }

    //for test
    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            Graph graph = GraphOperation.getRandomGraph(10, 100, 10);
            List<Node> result = DFSOther(graph.start);
            List<Node> result2 = DFS(graph.start);

            if (!checkDFSOrder(result, result2)) {
                System.out.print(Constants.CODE_ERROR);

                printResult(result);
                printResult(result2);

                GraphOperation.printGraph(graph);
                return;
            }

        }
        System.out.println(Constants.TEST_FINISH);
    }

    private static boolean checkDFSOrder(List<Node> list1, List<Node> list2) {
        if (list1 == null && list2 == null) return true;
        if (list1 == null || list2 == null) return false;
        if (list1.size() != list2.size()) return false;

        for (Node n1 : list1) {
            if (!list2.contains(n1)) return false;
        }
        return true;
    }

    private static void printResult(List<Node> resultBFS) {
        for (Node node : resultBFS) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test();
    }
}
