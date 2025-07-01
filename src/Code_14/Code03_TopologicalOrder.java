package Code_14;

import tools.Constants;

import java.util.*;

public class Code03_TopologicalOrder {

    // deal with the inEdge
    private static ArrayList<DirectedGraphNode> topologicalSortBFS(ArrayList<DirectedGraphNode> graph) {
        if (graph.isEmpty()) return null;

        HashMap<DirectedGraphNode, Integer> inEdgeMap = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            inEdgeMap.put(node, 0);
        }

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbors : node.neighbors) {
                inEdgeMap.put(neighbors, inEdgeMap.get(neighbors) + 1);
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList<>();
        ArrayList<DirectedGraphNode> topologicalOrder = new ArrayList<>();

        for (Map.Entry<DirectedGraphNode, Integer> entry : inEdgeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            DirectedGraphNode poll = queue.poll();
            topologicalOrder.add(poll);

            for (DirectedGraphNode neighbor : poll.neighbors) {
                inEdgeMap.put(neighbor, inEdgeMap.get(neighbor) - 1);
                if (inEdgeMap.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return topologicalOrder;
    }

    static class Record {
        public DirectedGraphNode node;
        public int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    private static ArrayList<DirectedGraphNode> topologicalSortDFS(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();

        for (DirectedGraphNode node : graph) {
            f(node, order);
        }

        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        List<Record> records = new ArrayList<>();

        records.addAll(order.values());
        records.sort((record1, record2) -> record2.deep - record1.deep);

        for (Record record : records) {
            result.add(record.node);
        }

        return result;
    }

    private static Record f(DirectedGraphNode currentNode, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(currentNode)) {
            return order.get(currentNode);
        }

        int flow = 0;
        for (DirectedGraphNode neighbor : currentNode.neighbors) {
            flow = Math.max(flow, f(neighbor, order).deep);
        }
        Record record = new Record(currentNode, flow + 1);
        order.put(currentNode, record);
        return record;
    }

    private static List<Node> topologicalSort2(Graph graph) {
        List<Node> result = new ArrayList<>();
        if (graph.nodes.isEmpty()) return result;

        // the queue is the in edge
        Queue<Node> zeroQueue = new LinkedList<>();
        HashMap<Node, Integer> inCount = new HashMap<>();

        for (Node node : graph.nodes.values()) {
            inCount.put(node, node.inEdges);
            if (inCount.get(node) == 0)
                zeroQueue.add(node);
        }


        while (!zeroQueue.isEmpty()) {
            Node poll = zeroQueue.poll();
            result.add(poll);

            for (Node next : poll.nextNode) {
                inCount.put(next, inCount.get(next) - 1);
                if (inCount.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return result;
    }

    // use the inEdges to deal
    private static List<Node> topologicalSort1(Graph graph) {
        List<Node> result = new ArrayList<>();

        if (graph.nodes.isEmpty()) return result;

        LinkedList<Node> currentNodes = new LinkedList<>();
        HashMap<Node, Integer> hashMap = new HashMap<>();

        for (HashMap.Entry<Integer, Node> entry : graph.nodes.entrySet()) {
            Node node = entry.getValue();
            currentNodes.add(node);
            hashMap.put(node, node.inEdges);
        }

        currentNodes.sort(Comparator.comparingInt(o -> o.inEdges));

        // this graph must be have a circle
        if (currentNodes.getFirst().inEdges != 0) return result;

        while (!currentNodes.isEmpty()) {
            Node node = currentNodes.removeFirst();

            if (hashMap.get(node) == 0) {
                result.add(node);
                hashMap.remove(node);
                currentNodes.remove(node);

                for (Node next : node.nextNode) {
                    if (!hashMap.containsKey(next)) {
                        hashMap.put(next, node.inEdges - 1);
                    } else {
                        hashMap.put(next, hashMap.get(next) - 1);
                    }
                }
            }
        }
        return result;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);

        for (int i = 0; i < 1; i++) {
            Graph graph = GraphOperation.getRandomGraph(10, 100, 10);

            GraphOperation.printGraph(graph);

            List<Node> topologicalSort1 = topologicalSort1(graph);
            List<Node> topologicalSort2 = topologicalSort2(graph);

            System.out.println("The topological sort1 is:" + i);

            for (Node node : topologicalSort1) {
                System.out.print(node.value + " ");
            }
            System.out.println();
            System.out.println("The topological sort2 is:" + i);

            for (Node node : topologicalSort2) {
                System.out.print(node.value + " ");
            }
            System.out.println();
        }

        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
