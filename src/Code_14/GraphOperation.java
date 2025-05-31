package Code_14;


import Code_13.UnionInterface;
import tools.NumberOperation;

import java.util.*;

public class GraphOperation {
    public static Graph getRandomGraph(int maxSizeNode, int maxValue, int maxWeight) {
        Graph graph = new Graph();

        int size = NumberOperation.getRandomNumber(maxSizeNode);
        UnionFind unionFind = new UnionFind();

        for (int i = 0; i <= size; i++) {
            int value = NumberOperation.getRandomNumber(maxValue);
            Node node = new Node(value);
            graph.nodes.put(value, node);
        }

        for (Map.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
            unionFind.connected(entry.getValue(), maxWeight, graph);
        }

        Node start = null;
        for (Map.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
            if (entry.getValue().inEdges == 0) {
                start = entry.getValue();
                break;
            }
        }
        graph.start = start == null ? unionFind.currentParent : start;
        return graph;
    }

    public static void printGraph(Graph graph) {
        System.out.println();
        System.out.println("The graph Node is: ");
        for (Node node : graph.nodes.values()) {
            System.out.print(node.value + " ");
        }
        System.out.println();
        System.out.println("The graph edges is: ");
        for (Edge edge : graph.edges) {
            System.out.print(edge.from.value + " -> " + edge.to.value + " ");
        }
        System.out.println();
    }


    static class UnionFind implements UnionInterface {

        HashMap<Node, Node> parents;
        HashMap<Node, Integer> size;
        int sets;
        Node currentParent;
        List<Node> currentAllNodes;

        public UnionFind() {
            parents = new HashMap<>();
            size = new HashMap<>();
            sets = 0;
            currentAllNodes = new ArrayList<>();
        }

        private Node findFather(Node currentNode) {
            Stack<Node> stack = new Stack<>();
            while (parents.get(currentNode) != currentNode) {
                stack.push(currentNode);
                currentNode = parents.get(currentNode);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), currentNode);
            }
            return currentNode;
        }

        @Override
        public boolean isSame(Object a, Object b) {
            Node aNode = (Node) a;
            Node bNode = (Node) b;
            return findFather(aNode) == findFather(bNode);
        }

        @Override
        public void union(Object a, Object b) {
            Node aNode = (Node) a;
            Node bNode = (Node) b;

            Node fatherA = findFather(aNode);
            Node fatherB = findFather(bNode);

            int sizeA = size.get(fatherA);
            int sizeB = size.get(fatherB);

            Node bigSizeNode = sizeA > sizeB ? fatherA : fatherB;
            Node smallSizeNode = bigSizeNode == fatherB ? fatherA : fatherB;

            parents.put(smallSizeNode, bigSizeNode);
            size.put(bigSizeNode, sizeA + sizeB);
            sets--;

        }

        public void connected(Node currentNode, int maxWeight, Graph graph) {
            if (size.containsKey(currentNode)) {
                return;
            }

            parents.put(currentNode, currentNode);
            size.put(currentNode, 1);
            currentAllNodes.add(currentNode);
            sets++;
            Edge edge = null;

            if (parents.size() != 1) {
                union(currentParent, currentNode);

                Node from = currentAllNodes.get(NumberOperation.getRandomNumberTurnTo_1(currentAllNodes.size() - 1));

                edge = new Edge(NumberOperation.getRandomNumber(maxWeight), from, currentNode);


                from.nextNode.add(currentNode);
                from.outEdges++;
                from.nextEdges.add(edge);
                currentNode.inEdges++;
            }

            if (edge != null) {
                graph.edges.add(edge);
            }

            currentParent = findFather(currentNode);
        }
    }
}











