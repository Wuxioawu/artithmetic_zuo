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


    public static Graph getRandomGraphWitCircle(int maxSizeNode, int maxValue, int maxWeight) {
        Graph randomGraph = getRandomGraph(maxSizeNode, maxValue, maxWeight);

        ArrayList<Node> nodeList = new ArrayList<>(randomGraph.nodes.values());

        for (int i = 0; i < nodeList.size(); i++) {
            Node currentNode = nodeList.get(i);
            Node nextNode = nodeList.get(NumberOperation.getRandomNumber(nodeList.size()));
            if (!currentNode.nextEdges.contains(nextNode) && currentNode != nextNode) {
                Edge edge = new Edge(NumberOperation.getRandomNumberIncludeValue(maxWeight), currentNode, nextNode);

                currentNode.nextEdges.add(edge);
                currentNode.outEdges++;
                currentNode.nextNode.add(nextNode);

                currentNode.edges.add(edge);

                nextNode.inEdges++;
                nextNode.edges.add(edge);

                randomGraph.edges.add(edge);
            }
        }
        return randomGraph;
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
            System.out.print(edge.from.value + " weight = " + edge.weight + "-->" + edge.to.value + " ");
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

                edge = new Edge(NumberOperation.getRandomNumberIncludeValue(maxWeight), from, currentNode);


                from.nextNode.add(currentNode);
                from.outEdges++;
                from.nextEdges.add(edge);
                from.edges.add(edge);

                currentNode.edges.add(edge);
                currentNode.inEdges++;
            }

            if (edge != null) {
                graph.edges.add(edge);
            }

            currentParent = findFather(currentNode);
        }
    }

    public static Graph dealWithGraph(Graph graph) {
        if (graph == null) return null;

        int[][] visited = new int[graph.nodes.size()][graph.nodes.size()];

        HashMap<Node, Integer> indexMap = new HashMap<>();
        int index = 0;
        for (Map.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
            indexMap.put(entry.getValue(), index++);
        }
        HashSet<Edge> edgeHashSet = graph.edges;

        for (Edge edge : edgeHashSet) {
            int fromIndex = indexMap.get(edge.from);
            int toIndex = indexMap.get(edge.to);

            if (visited[fromIndex][toIndex] != 1) {
                visited[fromIndex][toIndex] = 1;
                visited[toIndex][fromIndex] = 1;
            } else {

                edgeHashSet.remove(edge);
                Node from = edge.from;
                Node to = edge.to;

                from.nextNode.remove(to);
                from.nextEdges.remove(edge);
                from.outEdges--;
                from.edges.remove(edge);

                to.inEdges--;
                to.edges.remove(edge);
            }
        }
        return graph;
    }
}











