package Code_14;

import java.util.*;
// according edges
public class Code05_Kruskal {

    static class UnionFind {
        HashMap<Node, Node> parent;
        int sets;
        HashMap<Node, Integer> sizeNode;

        public UnionFind(Graph graph) {
            parent = new HashMap<>();
            sizeNode = new HashMap<>();

            for (Map.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
                Node node = entry.getValue();
                parent.put(node, node);
                sizeNode.put(node, 1);
                sets++;
            }
        }

        private Node findFather(Node currentNode) {
            Stack<Node> stack = new Stack<>();
            while (currentNode != parent.get(currentNode)) {
                stack.push(currentNode);
                currentNode = parent.get(currentNode);
            }

            while (!stack.isEmpty()) {
                parent.put(stack.pop(), currentNode);
            }

            return currentNode;
        }

        public boolean isSame(Node node1, Node node2) {
            return findFather(node1) == findFather(node2);
        }

        public void union(Node node1, Node node2) {
            Node fatherA = findFather(node1);
            Node fatherB = findFather(node2);

            int sizeA = sizeNode.get(fatherA);
            int sizeB = sizeNode.get(fatherB);

            Node bigSize = sizeA > sizeB ? fatherA : fatherB;
            Node smallSize = bigSize == fatherA ? fatherB : fatherA;

            parent.put(smallSize, bigSize);
            sizeNode.put(bigSize, sizeA + sizeB);
            sets--;
        }
    }

    public static Set<Edge> kruskal(Graph graph) {
        UnionFind unionFind = new UnionFind(graph);

        Set<Edge> resultEdges = new HashSet<>();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        priorityQueue.addAll(graph.edges);

        while (!priorityQueue.isEmpty()) {
            Edge poll = priorityQueue.poll();
            if (!unionFind.isSame(poll.from, poll.to)) {
                unionFind.union(poll.from, poll.to);
                resultEdges.add(poll);
            }
        }
        return resultEdges;
    }
}
