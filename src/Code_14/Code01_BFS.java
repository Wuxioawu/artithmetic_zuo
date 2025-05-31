package Code_14;

import java.util.*;

//Breadth-First Search
public class Code01_BFS {
    private static List<Node> BFS(Node start) {
        List<Node> resultBFS = new ArrayList<>();
        if (start == null) return resultBFS;

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            resultBFS.add(current);
            for (Node next : current.nextNode) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        return resultBFS;
    }

    public static void test() {
        var graph = GraphOperation.getRandomGraph(10, 100, 10);
        System.out.println("the start is: " + graph.start.value);
        var result = BFS(graph.start);
        printResult(result);
        GraphOperation.printGraph(graph);
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
