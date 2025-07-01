package Code_14;

import tools.Constants;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static Code_14.Code06_Prim.createEdge;

public class Code07_Dijkstra {

    private static HashMap<Node, Integer> dijkstra2(Node from) {
        return null;
    }

    static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static class NodeHeap {
        private HashMap<Node, Integer> indexMap;

        private Node[] nodes;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            this.size = size;
            nodes = new Node[size];
            distanceMap = new HashMap<>();
            indexMap = new HashMap<>();
        }

        // current node
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // in heap
            if (distanceMap.containsKey(node)) {

                distanceMap.put(node, distanceMap.get(node));
            }

            // hase not in heap
        }

        public NodeRecord pop() {
            if (isEmpty()) return null;


            return null;
        }

        public void heapify(int index, int size) {

        }

        public void insertHeapify(int index) {

        }

        public boolean isEmpty() {
            return size == 0;
        }


        private boolean inHeap(Node node) {
            return indexMap.containsKey(node);
        }

        private void swap(int index1, int index2) {

        }

    }

    private static HashMap<Node, Integer> dijkstra1(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();

        distanceMap.put(from, 0);
        HashSet<Node> selectedNode = new HashSet<>();
        Node mindNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);

        while (mindNode != null) {
            int distance = distanceMap.get(mindNode);
            for (Edge edge : mindNode.nextEdges) {
                Node lastNode = edge.to;

                if (!selectedNode.contains(lastNode)) {
                    distanceMap.put(lastNode, distance + edge.weight);
                } else {
                    distanceMap.put(lastNode, Math.min(distanceMap.get(lastNode), distance + edge.weight));
                }
            }

            selectedNode.add(mindNode);
            mindNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNode);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNode) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (entry.getValue() < minDistance && !selectedNode.contains(entry.getKey())) {
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }

        return minNode;
    }

    // for test
    private static boolean isSame(HashMap<Node, Integer> distanceMap1, HashMap<Node, Integer> distanceMap2) {
        if (distanceMap1 == null && distanceMap2 == null) return true;
        if (distanceMap1 == null || distanceMap2 == null) return false;
        if (distanceMap1.size() != distanceMap2.size()) return false;
        for (Map.Entry<Node, Integer> entry : distanceMap1.entrySet()) {
            if (entry.getValue() != distanceMap2.get(entry.getKey())) return false;
        }
        return true;
    }

    private static void printMap(HashMap<Node, Integer> distanceMap) {
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            System.out.print(" " + entry.getKey().value + " -> " + entry.getValue());
        }
        System.out.println();
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < 10; i++) {
            Graph randomGraph = GraphOperation.getRandomGraphWitCircle(5, 100, 10);
//            randomGraph = GraphOperation.dealWithGraph(randomGraph);


            randomGraph.edges.clear();
            randomGraph.nodes.clear();

            Node node1 = new Node(64);
            Node node2 = new Node(4);
            Node node3 = new Node(38);
            Node node4 = new Node(94);

            Edge edge1 = createEdge(node1, node3, 0);
            Edge edge2 = createEdge(node1, node2, 1);
            Edge edge3 = createEdge(node2, node3, 5);
            Edge edge4 = createEdge(node2, node4, 7);

            randomGraph.nodes.put(64, node1);
            randomGraph.nodes.put(4, node2);
            randomGraph.nodes.put(38, node3);
            randomGraph.nodes.put(94, node4);

            randomGraph.edges.add(edge1);
            randomGraph.edges.add(edge2);
            randomGraph.edges.add(edge3);
            randomGraph.edges.add(edge4);

            randomGraph.start = node1;


            HashMap<Node, Integer> nodeIntegerHashMap1 = dijkstra1(randomGraph.start);
            HashMap<Node, Integer> nodeIntegerHashMap2 = dijkstra2(randomGraph.start);

            if (!isSame(nodeIntegerHashMap1, nodeIntegerHashMap2)) {
                GraphOperation.printGraph(randomGraph);
                printMap(nodeIntegerHashMap1);
                printMap(nodeIntegerHashMap2);
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

















