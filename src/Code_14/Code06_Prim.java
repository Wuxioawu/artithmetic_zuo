package Code_14;

import tools.Constants;

import java.util.*;

// according node
public class Code06_Prim {

    public static Set<Edge> primMST(Graph graph) {

        ArrayList<Node> nodes = new ArrayList<>(graph.nodes.values());
        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        Set<Edge> resultEdges = new HashSet<>();
        Set<Node> hasVisited = new HashSet<>();

        Node currentNode;
        while (!nodes.isEmpty()) {
            currentNode = nodes.removeFirst();
            hasVisited.add(currentNode);

            for (Edge edge : currentNode.edges) {
                if (!edges.contains(edge)) {
                    edges.add(edge);
                }
            }

            while (!edges.isEmpty()) {
                Edge poll = edges.poll();

                if (!hasVisited.contains(poll.to) || !hasVisited.contains(poll.from)) {
                    Node nextNode = hasVisited.contains(poll.to) ? poll.from : poll.to;

                    hasVisited.add(nextNode);

                    for (Edge edge : nextNode.edges) {
                        if (!edges.contains(edge)) {
                            edges.add(edge);
                        }
                    }

                    resultEdges.add(poll);
                    nodes.remove(nextNode);
                }
            }
        }

        return resultEdges;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            Graph randomGraph = GraphOperation.getRandomGraphWitCircle(5, 100, 10);

            Set<Edge> edges = primMST(randomGraph);
            Set<Edge> resultEdges = Code05_Kruskal.kruskal(randomGraph);

            if (getWeight(edges) != getWeight(resultEdges)) {
                GraphOperation.printGraph(randomGraph);
                printSet(edges);
                printSet(resultEdges);
                return;
            }

        }

        System.out.println(Constants.TEST_FINISH);
    }

    private static int getWeight(Set<Edge> edges) {
        int weight = 0;
        for (Edge edge : edges) {
            weight += edge.weight;
        }
        return weight;
    }

    public static Edge createEdge(Node node1, Node node2, int weight) {
        Edge edge = new Edge(weight, node1, node2);
        edge.from = node1;
        edge.to = node2;

        node1.edges.add(edge);
        node1.nextNode.add(node2);
        node1.outEdges++;
        node1.nextEdges.add(edge);

        node2.edges.add(edge);
        node2.inEdges++;

        return edge;
    }

    private static void printSet(Set<Edge> edges) {
        for (Edge edge : edges) {
            System.out.println(edge.from.value + " --------" + edge.weight + "-------->" + edge.to.value);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        test();
    }

}
